package com.hamdard.hua.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.PasswordChangeDetails;
import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.repository.UserManagementRepository;
import com.hamdard.hua.security.Secured;

@Component
@Secured
@Path("/changepassword")
public class ChangePasswordEndpoint {
    private static final Logger logger          = LogManager.getLogger(AuthenticationRepository.class);

    @Autowired
    private UserManagementRepository            userMgmtRepo;

    @Context
    SecurityContext                             securityContext;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Map<String,String> changePassword(PasswordChangeDetails pwdDetails) {
        String userName                         = securityContext.getUserPrincipal().getName();
        return userMgmtRepo.changePassword(userName, pwdDetails);

    }
}
