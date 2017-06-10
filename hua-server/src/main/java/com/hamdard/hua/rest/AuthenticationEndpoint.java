package com.hamdard.hua.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.model.Token;
import com.hamdard.hua.repository.AuthenticationRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
@Path("/authentication")
public class AuthenticationEndpoint {

    private static final Logger logger = LogManager.getLogger(AuthenticationEndpoint.class);

    @Autowired
    AuthenticationRepository authenticationRepository;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Token authenticateUser(LoginDetails loginDetails) {

        if (loginDetails.getUsername() != null) {
            String userDisplayName = authenticationRepository.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
            return authenticationRepository.issueToken(loginDetails.getUsername(), userDisplayName, 0L);
        } else if (loginDetails.getToken() != null) {
            Jws<Claims> jws = authenticationRepository.validateToken(loginDetails.getToken());
            return authenticationRepository.issueToken(jws.getBody().getSubject(), (String)jws.getBody().get("DISPLAY_NAME"), Long.valueOf(jws.getBody().getId()));
        } else {
            logger.error("Username/Password or token is mandatory");
            throw new NotAuthorizedException("Bearer");
        }

    }

}