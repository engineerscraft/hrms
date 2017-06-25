package com.hamdard.hua.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.PasswordChangeDetails;
import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.security.Secured;

@Component
@Path("/changepassword")
public class ChangePasswordEndpoint {
    private static final Logger logger = LogManager.getLogger(ChangePasswordEndpoint.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;
    
    @Context
    SecurityContext securityContext;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured
    public Response changePassword(PasswordChangeDetails pwdDetails) {
    	try {
    		authenticationRepository.changePassword(pwdDetails, securityContext.getUserPrincipal().getName());
    	}
    	catch(AuthenticationException e) {
    		logger.error("Invalid username and password", e);
    		return Response.status(401).entity(new Message("Invalid username and password")).build();
    	}
    	catch(Exception e) {
    		logger.error(e.getMessage(), e);
    		return Response.status(500).entity(new Message(e.getMessage())).build();
    	}
    	return Response.status(200).build();
    }
}
