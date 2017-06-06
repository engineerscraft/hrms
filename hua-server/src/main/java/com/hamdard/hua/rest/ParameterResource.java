package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hamdard.hua.model.Parameter;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.ParameterRepository;
import com.hamdard.hua.security.Secured;

@Path("/parameter")
public class ParameterResource {

    @Autowired
    private ParameterRepository parameterRepository;
    
    @Context
    SecurityContext securityContext;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.PARAMETER_ALL_READ_CMD)
    public List<Parameter> getAllEmployees() {
        return parameterRepository.getAllParameters();
    }

    @GET
    @Path("/{key}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.PARAMETER_READ_CMD)
    public Parameter getEmployee(@PathParam("key") String key) {
        return parameterRepository.getParameterDetails(key);
    }

    @PUT
    @Path("/{key}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.PARAMETER_UPDATE_CMD)
    @Transactional
    public Response updateParameter(Parameter parameter, @PathParam("key") String key) {
        parameterRepository.updateParameterValue(parameter, key, securityContext.getUserPrincipal().getName());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
