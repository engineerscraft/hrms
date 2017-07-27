package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.Permission;
import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.security.Secured;

@Path("/v1/permission")
public class PermissionEndpoint {

    private static final Logger logger = LogManager.getLogger(AuthenticationRepository.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured
    public Response getPermissions(@QueryParam("permissionLevel") String permissionLevel) {
        try {
            List<Permission> permissions = authenticationRepository.retriveViewPermissions(securityContext.getUserPrincipal().getName());
           
            if (permissions.size() == 0) {
                logger.debug("No permission given to the user: {}", () -> securityContext.getUserPrincipal().getName());
                return Response.status(Response.Status.NOT_FOUND).entity(new Message("No permission given to the user")).build();
            } else
                return Response.status(Response.Status.OK).entity(permissions).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build();
        }
    }
}
