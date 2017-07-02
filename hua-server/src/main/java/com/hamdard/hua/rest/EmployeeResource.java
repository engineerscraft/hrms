package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Employee.EmployeeAddlDetails;
import com.hamdard.hua.model.Employee.EmployeeAddress;
import com.hamdard.hua.model.Employee.EmployeeOptionalBenefit;
import com.hamdard.hua.model.Employee.EmployeeProfile;
import com.hamdard.hua.model.Employee.EmployeeSalary;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.EmployeeRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Jyotirmoy Banerjee
 * Employee related service's endpoint!
 */

@Path("/v1/employee/management")
public class EmployeeResource {

    private static final Logger logger = LogManager.getLogger(EmployeeResource.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Context
    SecurityContext securityContext;

    @POST
    @Path("/")
    @Secured(Privilege.CREATE_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crate(Employee newEmployee){
        try{
            String message  = employeeRepository.createEmployee( newEmployee );
            return Response.status(200).entity(new Message(message)).build();
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            return Response.status(500).entity(new Message(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/salary")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEmployeeSalary(@PathParam("id") String employeeId, List<EmployeeSalary> updEmployeeSalary) {
        try {
            String entryBy = securityContext.getUserPrincipal().getName();
            employeeRepository.updateEmpSalaryComponents(employeeId, entryBy, updEmployeeSalary);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error("The employee salary could not be updated", ex);
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/profile")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEmployeeProfile(@PathParam("id") String employeeId, EmployeeProfile updEmployeeProfile) {
        try {
            employeeRepository.updateEmployeeProfile(employeeId, updEmployeeProfile);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error("The employee profile could not be updated", ex);
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }

    @POST
    @Path("/{id}/optionalbenefits")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertEmployeeOptionalBenefits(@PathParam("id") String employeeId, List<EmployeeOptionalBenefit> insEmployeeOptBenefits) {
        try {
            String entryBy = securityContext.getUserPrincipal().getName();
            employeeRepository.insertEmpOptionalBenefits(employeeId, entryBy, insEmployeeOptBenefits);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error("The employee optional benefits could not be updated", ex);
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/optionalbenefits/{oid}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertEmployeeOptionalBenefits(@PathParam("id") String employeeId, @PathParam("oid") int optCompId, EmployeeOptionalBenefit updEmployeeOptBenefit) {
        try {
            String entryBy = securityContext.getUserPrincipal().getName();
            employeeRepository.updateEmpOptionalBenefits(employeeId, optCompId, entryBy, updEmployeeOptBenefit);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error("The employee optional benefits could not be updated", ex);
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }

    /* Needs to rework on this 
     * Update Query have to be dynamic
     * EmployeeAddress.pinno has to be changed to int from String
     */
    @PUT
    @Path("/{id}/address")
    //@Secured(Privilege.UPDATE_EMP_ADDRESS_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatedEmployeeAddlDetails(@PathParam("id") String employeeId, EmployeeAddress employeeAddress) {
        try {
            //String modifiedBy = securityContext.getUserPrincipal().getName();
            
            employeeRepository.updatedEmployeeAddress(employeeId, employeeAddress);
            logger.info("Employee address details updated in successfully: {}", () -> employeeAddress);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error("The employee additional details could not be updated", ex);
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }
    
    
    

}
