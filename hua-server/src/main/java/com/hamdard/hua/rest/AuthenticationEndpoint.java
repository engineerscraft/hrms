package com.hamdard.hua.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.ErrorBody;
import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.model.Token;
import com.hamdard.hua.repository.AuthenticationRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;

@Component
@Path("/authentication")
public class AuthenticationEndpoint {

	private static final Logger logger = LogManager.getLogger(AuthenticationEndpoint.class);

	@Autowired
	AuthenticationRepository authenticationRepository;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response authenticateUser(LoginDetails loginDetails) {
		Token token = null;
		try {
			if (loginDetails.getUsername() != null) {
				String userDisplayName = authenticationRepository.authenticate(loginDetails.getUsername(),
						loginDetails.getPassword());
				token = authenticationRepository.issueToken(loginDetails.getUsername(), userDisplayName, 0L);
			} else if (loginDetails.getToken() != null) {
				Jws<Claims> jws = authenticationRepository.validateToken(loginDetails.getToken());
				token = authenticationRepository.issueToken(jws.getBody().getSubject(),
						(String) jws.getBody().get("DISPLAY_NAME"),
						Long.valueOf(jws.getBody().get("TOKEN_ID").toString()));
			} else {
				logger.error("Username/Password or token is mandatory");
				return Response.status(401).entity(new ErrorBody("Username/password or refresh token is mandatory"))
						.build();
			}
			return Response.status(200).entity(token).build();
		} catch (AuthenticationException e) {
			return Response.status(401).entity(new ErrorBody("Invalid username and password")).build();
		} catch (ExpiredJwtException e) {
			return Response.status(401).entity(new ErrorBody("Refresh token expired")).build();
		} catch(Exception e) {
			return Response.status(500).entity(new ErrorBody(e.getMessage())).build();
		}

	}

}