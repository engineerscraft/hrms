package com.hamdard.hua.repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.ws.rs.NotAuthorizedException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Employee.EmployeeBasicInfo;
import com.hamdard.hua.model.PasswordChangeDetails;
import com.hamdard.hua.model.Permission;
import com.hamdard.hua.model.Token;
import com.hamdard.hua.rowmapper.PermissionRowMapper;

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

    @Value("${sql.viewPermission}")
    private String viewPermissionSql;

    @Autowired
    private LdapContextSource contextSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private LdapTemplate ldapTemplate;

    public String authenticate(String username, String password) throws NamingException {
        DirContext dirContext = contextSource.getContext("cn=" + username + ",ou=users," + baseDn, password);
        return dirContext.getAttributes("cn=" + username + ",ou=users").get("displayName").get().toString();
    }

    public void changePassword(PasswordChangeDetails pwdDetails, String username) throws NamingException {
        authenticate(username, pwdDetails.getCurrentPassword());
        DirContextOperations context = ldapTemplate.lookupContext("cn=" + username + ",ou=users");
        context.setAttributeValue("userPassword", digestSHA(pwdDetails.getNewPassword()));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Get the full name to be used in LDAP
     * 
     * @param basicInfo
     * @return
     */
    private String getFullName(EmployeeBasicInfo basicInfo) {
        String fullName = String.format("%s %s %s", basicInfo.getEmpFirstName(), basicInfo.getEmpMiddleName() == null ? "" : basicInfo.getEmpMiddleName(), basicInfo.getEmpLastName());
        return fullName;
    }

    // TODO Once date format is finalized include date into password
    /**
     * Get the default passwprd for a new employee
     * 
     * @param employeeId
     * @param employee
     * @return
     */
    private String getEmpPassword(String employeeId, Employee employee) {
        return employeeId;
    }

    /**
     * Create new user in LDAP
     * 
     * @param employeeId
     * @param employee
     * @throws Exception
     */
    public void createUser(String employeeId, Employee employee) throws Exception {
        try {
            String fullName = this.getFullName(employee.getEmployeeBasicInfo());
            Attribute sn = new BasicAttribute("sn", fullName);
            Attribute password = new BasicAttribute("userPassword", this.getEmpPassword(employeeId, employee));
            Attribute displayName = new BasicAttribute("displayName", fullName);

            Attribute oc = new BasicAttribute("objectClass");
            oc.add("top");
            oc.add("person");
            oc.add("organizationalPerson");
            oc.add("inetOrgPerson");

            BasicAttributes personEntry = new BasicAttributes();
            personEntry.put(sn);
            personEntry.put(password);
            personEntry.put(displayName);

            personEntry.put(oc);
            String relativeDn = String.format("cn=%s,ou=users", employeeId);

            ldapTemplate.bind(relativeDn, null, personEntry);

            // add the required roles to the new entry
            this.addRole("EMPLOYEE", relativeDn);
            if (employee.getEmployeeBasicInfo().isHrFlag())
                this.addRole("HR", relativeDn);
            if (employee.getEmployeeBasicInfo().isSupervisorFlag())
                this.addRole("SUPERVISOR", relativeDn);

        } catch (Exception e) {
            logger.error("User creation failed", e);
            throw e;
        }
    }

    private void addRole(String role, String relativeDn) throws Exception {
        String rolePath = String.format("cn=%s,ou=roleusers", role);
        DirContextOperations dirContext = ldapTemplate.lookupContext(rolePath);
        dirContext.addAttributeValue("uniqueMember", relativeDn + "," + baseDn);
        try {
            ldapTemplate.modifyAttributes(dirContext);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
    }

    public Token issueToken(String username, String userDisplayName, Long id) {

        Calendar now = Calendar.getInstance();
        long nowInMillis = now.getTimeInMillis();
        String accessToken = null;
        String refreshToken = null;
        Date accessTokenExpiryDate = new Date(nowInMillis + (Integer.parseInt(this.accessTokenValidityPeriod) * ONE_MINUTE_IN_MILLIS));
        Date refreshTokenExpiryDate = new Date(nowInMillis + (Integer.parseInt(this.refreshTokenValidityPeriod) * ONE_MINUTE_IN_MILLIS));

        if (id == 0) {
            Long accessId = jdbcTemplate.queryForObject(accessIdFetchingSql, new Object[] {}, Long.class);
            logger.info(sqlMarker, newTokenSavingSql);
            logger.info(sqlMarker, "Params {}, {}, {}", () -> accessId, () -> username, () -> accessTokenExpiryDate);
            jdbcTemplate.update(newTokenSavingSql, new Object[] { accessId, username, accessTokenExpiryDate });
            Map<String, Object> claims = new HashMap();
            claims.put("DISPLAY_NAME", userDisplayName);
            claims.put("TOKEN_ID", accessId.toString());
            accessToken = Jwts.builder().setClaims(claims).setSubject(username).setExpiration(accessTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
            refreshToken = Jwts.builder().setClaims(claims).setSubject(username).setExpiration(refreshTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
        } else {
            logger.info(sqlMarker, existingTokenUpdateSql);
            logger.info(sqlMarker, "Params {}, {}, {}", () -> accessTokenExpiryDate, () -> id, () -> username);
            int rowsUpdated = jdbcTemplate.update(existingTokenUpdateSql, new Object[] { accessTokenExpiryDate, id, username });
            if (rowsUpdated == 0) {
                throw new NotAuthorizedException("Bearer");
            }
            Map<String, Object> claims = new HashMap();
            claims.put("DISPLAY_NAME", userDisplayName);
            claims.put("TOKEN_ID", id.toString());
            accessToken = Jwts.builder().setClaims(claims).setSubject(username).setExpiration(accessTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
            refreshToken = Jwts.builder().setClaims(claims).setSubject(username).setExpiration(refreshTokenExpiryDate).signWith(SignatureAlgorithm.HS256, signingKey).compact();
        }

        return new Token(accessToken, refreshToken, username, userDisplayName);
    }

    public List<String> retrieveRoles(String username) {
        List<String> userRoles = ldapTemplate.search("ou=roleusers", "uniqueMember=cn=" + username + ",ou=users," + baseDn, (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
        logger.debug("User roles {}", () -> userRoles);
        return userRoles;
    }

    public List<Permission> retrivePermissions(String username, String filter) {

        List<String> userRoles = retrieveRoles(username);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleList", userRoles);
        paramMap.put("filter", filter);
        logger.info(sqlMarker, authorizationDetectionSql);
        logger.info(sqlMarker, "Params {}, {}", () -> paramMap.get("roleList"), () -> paramMap.get("filter"));
        List<Permission> permissions = namedParameterJdbcTemplate.query(authorizationDetectionSql, paramMap, new PermissionRowMapper());
        logger.debug("Module name {}", () -> permissions);
        return permissions;
    }

    public List<Permission> retriveViewPermissions(String username) {
        List<String> userRoles = ldapTemplate.search("ou=roleusers", "uniqueMember=cn=" + username + ",ou=users," + baseDn, (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
        logger.debug("User roles {}", () -> userRoles);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleList", userRoles);
        logger.info(sqlMarker, namedParameterJdbcTemplate);
        logger.info(sqlMarker, "Params {}", () -> paramMap.get("roleList"));
        List<Permission> permissions = namedParameterJdbcTemplate.query(viewPermissionSql, paramMap, new PermissionRowMapper());
        logger.debug("Module name {}", () -> permissions);
        return permissions;
    }

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(password.getBytes());
            base64 = Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }
}
