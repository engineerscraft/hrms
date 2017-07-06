package com.hamdard.hua.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.model.Token;
import com.hamdard.hua.repository.AuthenticationRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;

@Path("/v1/authentication")
public class AuthenticationEndpoint {

    private static final Logger logger = LogManager.getLogger(AuthenticationEndpoint.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response authenticateUser(@Valid @RequestBody LoginDetails loginDetails) {
        Token token = null;
        try {
            if (loginDetails.getUsername() != null) {
                String userDisplayName = authenticationRepository.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
                token = authenticationRepository.issueToken(loginDetails.getUsername(), userDisplayName, 0L);
            } else if (loginDetails.getToken() != null) {
                Jws<Claims> jws = authenticationRepository.validateToken(loginDetails.getToken());
                token = authenticationRepository.issueToken(jws.getBody().getSubject(), (String) jws.getBody().get("DISPLAY_NAME"), Long.valueOf(jws.getBody().get("TOKEN_ID").toString()));
            } else {
                logger.error("Username/Password or token is mandatory");
                return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Username/password or refresh token is mandatory")).build();
            }
            return Response.status(Response.Status.OK).entity(token).build();
        } catch (AuthenticationException e) {
            logger.error("Invalid username and password", e);
            return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Invalid username and password")).build();
        } catch (ExpiredJwtException e) {
            logger.error("Refresh token expired", e);
            return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("Refresh token expired")).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build();
        }

    }

}