package com.hamdard.hua.rest;

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
import com.hamdard.hua.model.User;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.repository.UserManagementRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Jyotirmoy Banerjee
 * 
 */

@Component
@Path("/userManagement")
public class UserManagementEndpoint {
    private static final Logger logger = LogManager.getLogger(AuthenticationRepository.class);

    @Autowired
    private UserManagementRepository userMgmtRepo;

    @Context
    private SecurityContext securityContext;

    @POST
    @Secured(Privilege.CHANGE_PASSWORD_OF_A_USER)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/changePassword")
    public void changePassword(PasswordChangeDetails pwdDetails) {
        String userName = securityContext.getUserPrincipal().getName();
        try {
            userMgmtRepo.changePassword(userName, pwdDetails);
        } catch (Exception ex) {
            logger.error("Errow while changing password:", ex);
        }
    }

    @POST
    @Secured(Privilege.CREATE_A_USER)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/createUser")
    public String createUser(User newUser) {
        try {
            userMgmtRepo.createUser(newUser);
        } catch (Exception ex) {
            logger.error("Exception while creating user", ex);
            return "ERROR:" + ex.getMessage();
        }
        return "SUCCESS";
    }
}
