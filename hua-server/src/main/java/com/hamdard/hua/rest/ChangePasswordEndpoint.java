package com.hamdard.hua.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.repository.AuthenticationRepository;

@Component
@Path("/changepassword")
public class ChangePasswordEndpoint {
    private static final Logger logger = LogManager.getLogger(AuthenticationEndpoint.class);

    @Autowired
    AuthenticationRepository authenticationRepository;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void changePassword(LoginDetails loginDetails) {
        
    }
}
