package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.security.Secured;

@Component
@Path("/permission")
public class PermissionEndpoint {
    
    @Autowired
    AuthenticationRepository authenticationRepository;
    
    @Context
    SecurityContext securityContext;

    @GET
    @Path("/view")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured
    public List<String> getViewPermissions() {
        return authenticationRepository.retrivePermissions(securityContext.getUserPrincipal().getName(), "SCX%VIEW");
    }

}
