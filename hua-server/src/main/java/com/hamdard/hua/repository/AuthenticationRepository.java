package com.hamdard.hua.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import com.hamdard.hua.exception.AuthenticationFailure;
import com.hamdard.hua.model.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AuthenticationRepository {

    private static final Logger logger = LogManager.getLogger(AuthenticationRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    private static final long ONE_MINUTE_IN_MILLIS = 60000;

    @Value("${authentication.accessTokenValidityPeriod}")
    private String accessTokenValidityPeriod;

    @Value("${authentication.refreshTokenValidityPeriod}")
    private String refreshTokenValidityPeriod;

    @Value("${authentication.signingKey}")
    private String signingKey;

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.base}")
    private String baseDn;

    @Value("${sql.getAccessId}")
    private String accessIdFetchingSql;

    @Value("${sql.saveNewToken}")
    private String newTokenSavingSql;

    @Value("${sql.existingTokenUpdateSql}")
    private String existingTokenUpdateSql;

    @Value("${sql.getAuthorization}")
    private String authorizationDetectionSql;

    @Autowired
    private LdapContextSource contextSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private LdapTemplate ldapTemplate;

    public void authenticate(String username, String password) {
        try {
            contextSource.getContext("cn=" + username + ",ou=users," + baseDn, password);
        } catch (Exception e) {
            logger.error("User authentication failed", e);
            throw new AuthenticationFailure("User authentication failed");
        }
    }

    public Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            throw new AuthenticationFailure("Token validation failed");
        }
    }

    public Token issueToken(String username, Long id) {
        try {
            Calendar now = Calendar.getInstance();
            long nowInMillis = now.getTimeInMillis();
            String accessToken = null;
            String refreshToken = null;
            Date accessTokenExpiryDate = new Date(nowInMillis + (Integer.parseInt(this.accessTokenValidityPeriod) * ONE_MINUTE_IN_MILLIS));
            Date refreshTokenExpiryDate = new Date(nowInMillis + (Integer.parseInt(this.refreshTokenValidityPeriod) * ONE_MINUTE_IN_MILLIS));
            
            if (id == 0) {
                //Long accessId = jdbcTemplate.queryForObject(accessIdFetchingSql, new Object[] {}, Long.class);
                Long accessId=1L;
                logger.info(sqlMarker, newTokenSavingSql);
                logger.info(sqlMarker, "Params {}, {}, {}", () -> accessId, () -> username, () -> accessTokenExpiryDate);
                //jdbcTemplate.update(newTokenSavingSql, new Object[] { accessId, username, accessTokenExpiryDate });
                accessToken = Jwts.builder().setId(accessId.toString()).setSubject(username).setExpiration(accessTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
                refreshToken = Jwts.builder().setId(accessId.toString()).setSubject(username).setExpiration(refreshTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
            } else {
                logger.info(sqlMarker, existingTokenUpdateSql);
                logger.info(sqlMarker, "Params {}, {}, {}", () -> accessTokenExpiryDate, () -> id, () -> username);
                int rowsUpdated = jdbcTemplate.update(existingTokenUpdateSql, new Object[] { accessTokenExpiryDate, id, username });
                if (rowsUpdated == 0) {
                    throw new AuthenticationFailure("Token revoked");
                }
                accessToken = Jwts.builder().setId(id.toString()).setSubject(username).setExpiration(accessTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
                refreshToken = Jwts.builder().setId(id.toString()).setSubject(username).setExpiration(refreshTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
            }

            return new Token(accessToken, refreshToken);
        } catch (Exception e) {
            logger.error("Token could not be issued", e);
            throw new AuthenticationFailure("Token could not be issued");
        }
    }

    public List<String> retrivePermissions(String username, String filter) {
        return new ArrayList<String>();
        /*try {
            List<String> userRoles = ldapTemplate.search("ou=groups", "uniqueMember=uid=" + username + ",ou=people," + baseDn, (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
            logger.debug("User roles {}", () -> userRoles);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("roleList", userRoles);
            paramMap.put("moduleName", filter);
            List<String> moduleNames = namedParameterJdbcTemplate.queryForList(authorizationDetectionSql, paramMap, String.class);
            logger.debug("Module name {}", () -> moduleNames);
            return moduleNames;
        } catch (Exception e) {
            logger.error("Permissions could not be retrieved", e);
            throw new ResourceNotFound("Permissions could not be retrieved");
        }*/
    }
}
