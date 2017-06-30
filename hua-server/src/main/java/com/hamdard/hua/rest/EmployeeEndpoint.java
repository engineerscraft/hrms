package com.hamdard.hua.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public Response create(Employee newEmployee){
        try{
            employeeRepository.createEmployee( newEmployee );
            return Response.status(200).build();
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            return Response.status(500).entity(new Message(e.getMessage())).build();
        }
    }
    
    @GET
    @Path("/")
    @Secured(Privilege.CREATE_AN_EMP)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response hierarchySearch(@QueryParam("firstName") String firstName, @QueryParam("middleName") String middleName,
			@QueryParam("lastName") String lastName, @QueryParam("empId") String empId,
			@QueryParam("empType") String empType, @QueryParam("emailId") String emailId,
			@QueryParam("orgId") int orgId, @QueryParam("unitId") int unitId,
			@QueryParam("departmentId") int departmentId, @QueryParam("designationId") int dsignationId,
			@QueryParam("departmentId") String supervisorFlag, @QueryParam("hrFlag") String hrFlag,
			@QueryParam("supervisorEmailId") String supervisorEmailId, @QueryParam("hrEmailId") String hrEmailId,
			@QueryParam("sex") String sex, @QueryParam("maritalStatus") String maritalStatus,
			@QueryParam("identityDocId") String identityDocId, @QueryParam("identityDocNo") String identityDocNo
			){
    	
    	
    	return null;
 
    }
    
}


