package com.hamdard.hua.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.EmployeeRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Jyotirmoy Banerjee
 * Employee related service's endpoint!
 */

@Path("/v1/employee")
public class EmployeeEndpoint {
    
    private static final Logger logger = LogManager.getLogger(EmployeeEndpoint.class);

    @Autowired
    private EmployeeRepository  employeeRepository;
    
    @POST
    @Path("/")
    @Secured(Privilege.CREATE_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crate(Employee newEmployee){
        try{
            employeeRepository.createEmployee( newEmployee );
            return Response.status(200).build();
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            return Response.status(500).entity(new Message(e.getMessage())).build();
        }
    }
}


