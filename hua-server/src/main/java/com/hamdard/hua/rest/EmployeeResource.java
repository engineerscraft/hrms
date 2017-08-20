package com.hamdard.hua.rest;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Employee.EmployeeAddlDetails;
import com.hamdard.hua.model.Employee.EmployeeAddress;
import com.hamdard.hua.model.Employee.EmployeeBasicInfo;
import com.hamdard.hua.model.Employee.EmployeeDocument;
import com.hamdard.hua.model.Employee.EmployeeHierarchy;
import com.hamdard.hua.model.Employee.EmployeeOptionalBenefit;
import com.hamdard.hua.model.Employee.EmployeeProfile;
import com.hamdard.hua.model.Employee.EmployeeSalary;
import com.hamdard.hua.model.EmployeePayslip;
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
    private SecurityContext securityContext;
    
    @POST
    @Path("/")
    @Secured(Privilege.CREATE_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crate(Employee newEmployee) {
        try {
            String message = employeeRepository.createEmployee(newEmployee);
            return Response.status(Response.Status.OK).entity(new Message(message)).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/salary")
    @Secured(Privilege.MODIFY_SAL_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEmployeeSalary(@PathParam("id") @Size(min=1) String employeeId, List<EmployeeSalary> updEmployeeSalary) {
        try {
            String entryBy = securityContext.getUserPrincipal().getName();
            employeeRepository.updateEmpSalaryComponents(employeeId, entryBy, updEmployeeSalary);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            logger.error("The employee salary could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/profile")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.MODIFY_PROFILE_OF_AN_EMP)
    public Response updateEmployeeProfile(@PathParam("id") @Size(min=1) String employeeId, EmployeeProfile updEmployeeProfile) {
        try {
            String modifiedBy = securityContext.getUserPrincipal().getName();
            String message = employeeRepository.updateEmployeeProfile(employeeId, modifiedBy, updEmployeeProfile);
            if(message.startsWith("Success"))
                return Response.status(Response.Status.OK).entity(new Message(message)).build();
            else
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(message)).build();
        } catch (Exception ex) {
            logger.error("The employee profile could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @POST
    @Path("/{id}/optionalbenefits")
    @Secured(Privilege.CREATE_OPT_BENEFIT_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertEmployeeOptionalBenefits(@PathParam("id") @Size(min=1) String employeeId, List<EmployeeOptionalBenefit> insEmployeeOptBenefits) {
        try {
            String entryBy = securityContext.getUserPrincipal().getName();
            employeeRepository.insertEmpOptionalBenefits(employeeId, entryBy, insEmployeeOptBenefits);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            logger.error("The employee optional benefits could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/optionalbenefits/{oid}")
    @Secured(Privilege.UPDATE_OPT_BENEFIT_FOR_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertEmployeeOptionalBenefits(@PathParam("id") @Size(min=1) String employeeId, @PathParam("oid") @Min(1) int optCompId, EmployeeOptionalBenefit updEmployeeOptBenefit) {
        try {
            String entryBy = securityContext.getUserPrincipal().getName();
            employeeRepository.updateEmpOptionalBenefits(employeeId, optCompId, entryBy, updEmployeeOptBenefit);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            logger.error("The employee optional benefits could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.GET_EMP_SEARCH_IN_HIERARCHY)
    public Response searchEmployee(@QueryParam("firstName") String firstName, @QueryParam("middleName") String middleName, @QueryParam("lastName") String lastName, @QueryParam("employeeId") String employeeId, @QueryParam("empType") String employmentType,
            @QueryParam("emailId") String emailId, @QueryParam("orgId") Integer orgId, @QueryParam("unitId") Integer unitId, @QueryParam("departmentId") Integer departmentId, @QueryParam("jobRoleId") Integer jobRoleId,
            @QueryParam("designationId") Integer designationId, @QueryParam("supervisorFlag") String supervisorFlag, @QueryParam("hrFlag") String hrFlag, @QueryParam("supervisorEmailId") String supervisorEmailId, @QueryParam("hrEmailId") String hrEmailId,
            @QueryParam("sex") String sex, @QueryParam("maritalStatus") String maritalStatus, @QueryParam("identityDocTypeId") Integer identityDocTypeId, @QueryParam("identityNumber") String identityNumber) {
        try {
            if (!employeeRepository.isPrivilegedForHierarchySearch(securityContext.getUserPrincipal().getName()))
                return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("The user has no privilege to view the personal information of the employees")).build();

            List<Employee.EmployeeSearchResult> employeeSearchResult = employeeRepository.searchHierarchyEmployee(firstName, middleName, lastName, employeeId, employmentType, emailId, orgId, unitId, departmentId, jobRoleId, designationId, supervisorFlag,
                    hrFlag, supervisorEmailId, hrEmailId, sex, identityDocTypeId, identityNumber, securityContext.getUserPrincipal().getName());
            return Response.status(Response.Status.OK).entity(employeeSearchResult).build();
        } catch (Exception e) {
            logger.error("The hierarchy search could not be done", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build();
        }
    }

    @GET
    @Path("/autocomplete")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.READ_EMP_ATTR_AUTOCOMP)
    public Response autoCompleteEmployeeDetail(@QueryParam("attributeName") String attributeName, @QueryParam("attributeValuePrefix") String attributeValuePrefix, @QueryParam("numberOfItems") Integer numberOfItems) {
        try {            
            if(attributeName == null || attributeName.isEmpty() || !("empFirstName".equals(attributeName) || "empMiddleName".equals(attributeName) || "empLastName".equals(attributeName) || "supervisorEmailId".equals(attributeName) || "hrEmailId".equals(attributeName) || "empEmailId".equals(attributeName))) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Message("Invalid input")).build();
            }
            
            if (!employeeRepository.isPrivilegedForHierarchySearch(securityContext.getUserPrincipal().getName())) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(new Message("The user has no privilege to view the personal information of the employees")).build();
            }

            List<Employee.EmployeeSearchResult> employeeAutoCompleteResult = employeeRepository.autoCompleteEmployee(attributeName, attributeValuePrefix, numberOfItems, securityContext.getUserPrincipal().getName());
            return Response.status(Response.Status.OK).entity(employeeAutoCompleteResult).build();
        } catch (Exception e) {
            logger.error("The autocomplete result could not be retrieved", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/additionaldetails")
    @Secured(Privilege.MODIFY_ADDL_DTLS_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatedEmployeeAddlDetails(@PathParam("id") String employeeId, EmployeeAddlDetails employeeAddlDetails) {
        try {
            String modifiedBy = securityContext.getUserPrincipal().getName();
            // String modifiedBy = "dummy name";

            employeeRepository.updatedEmployeeAddlDetails(employeeId, modifiedBy, employeeAddlDetails);
            return Response.status(Response.Status.OK).entity(new Message("Employee additional details successfully updated")).build();
        } catch (Exception ex) {
            logger.error("The employee additional details could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    /* To Do:
     * Needs to rework on this 
     * Update Query have to be dynamic
     * EmployeeAddress.pinno has to be changed to int from String
     */
    @PUT
    @Path("/{id}/address")
    @Secured(Privilege.MODIFY_ADDR_DTLS_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatedEmployeeAddlDetails(@PathParam("id") String employeeId, List<EmployeeAddress> employeeAddress) {
        try {
            // String modifiedBy = securityContext.getUserPrincipal().getName();
        	
            employeeRepository.updatedEmployeeAddress(employeeId, employeeAddress);
            logger.info("Employee address details updated in successfully: {}", () -> employeeAddress);
            return Response.status(Response.Status.OK).entity(new Message("Employee address details successfully updated")).build();
        } catch (Exception ex) {
            logger.error("The employee address details could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }
    
    @PUT
    @Path("/{id}/hierarchystatus")
    @Secured(Privilege.UPDATE_EMP_HIER_STAT_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatedEmployeeHierarchyStatus(@PathParam("id") String employeeId, EmployeeHierarchy employeeHierarchy) {
        try {
            String modifiedBy = securityContext.getUserPrincipal().getName();
            
            employeeRepository.updatedEmployeeHierarchyStatus(employeeId, modifiedBy, employeeHierarchy);
            return Response.status(Response.Status.OK).entity(new Message("Employee hierarchy others details successfully updated")).build();
        } catch (Exception ex) {
            logger.error("The employee hierarchy status could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }
    
    @PUT
    @Path("/{id}/basicinfo")
    @Secured(Privilege.MODIFY_BASIC_DTLS_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatedEmployeeBasicInfo(@PathParam("id") String employeeId, EmployeeBasicInfo employeeBasicInfo) {
        try {
            String modifiedBy = securityContext.getUserPrincipal().getName();

            employeeRepository.updatedEmployeeBasicInfo(employeeId, modifiedBy, employeeBasicInfo);
            return Response.status(Response.Status.OK).entity(new Message("Employee basic info successfully updated")).build();
        } catch (Exception ex) {
            logger.error("The employee basic info could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("{id}/image")
    @Secured(Privilege.UPDATE_IMAGE_OF_EN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response uploadProfilePicture(@PathParam("id") @Size(min = 1) String employeeId, EmployeeBasicInfo img) {
        // String employeeName = securityContext.getUserPrincipal().getName();
        try {
            employeeRepository.insertEmployeeImage(img.getProfileImage(), employeeId);
            return Response.status(200).entity(new Message("Profile image uploaded successfully")).build();
        } catch (Exception ex) {
            logger.error("The Employee_Image could not be updated.", ex);
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Secured(Privilege.READ_ALL_EMP_DETAILS)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getEmployeeDetails(@PathParam("id") @Size(min=1) String employeeId) {
        try {
            Employee empInfo = employeeRepository.getEmployeeDetailsByEmpId(employeeId);
            empInfo.setDocumentList(employeeRepository.getAllDocuments(employeeId));
            for(EmployeeDocument doc : empInfo.getDocumentList()) {
                doc.setDocument(null);
            }
            empInfo.setEmployeeOptionalBenefit(employeeRepository.getemployeeOptionalBenefitList(employeeId));
            empInfo.setEmployeeSalary(employeeRepository.getEmployeeSalaryList(employeeId));
            empInfo.setEmployeeTaxList(employeeRepository.getEmployeeTaxList(employeeId));
            return Response.status(Response.Status.OK).entity(empInfo).build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("The employee details not found", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Message(e.getMessage())).build();            
        } catch (Exception ex) {
            logger.error("The employee details could not be fetched", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @POST
    @Path("{id}/document")
    @Secured(Privilege.UPDATE_DOC_FOR_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response createEmployeeDocument(@PathParam("id") @Size(min=1) String employeeId, Employee.EmployeeDocument empDoc) {
        try {
            employeeRepository.createDocument(employeeId, empDoc);
            return Response.status(Response.Status.OK).entity(new Message("Document successfully saved")).build();
        } catch (Exception ex) {
            logger.error("The employee document could not be saved", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }
    
    @GET
    @Path("{id}/document/{docId}")
    @Secured(Privilege.READ_A_DOC_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getDocument(@PathParam("id") String empId, @PathParam("docId") Integer docId) {
        try {
            EmployeeDocument empDoc = employeeRepository.getDocument(docId);
            return Response.status(Response.Status.OK).entity(empDoc).build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("The document not found", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Message(e.getMessage())).build();            
        } catch (Exception ex) {
            logger.error("The document could not be fetched", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @GET
    @Path("{id}/document")
    @Secured(Privilege.READ_ALL_DOC_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllDocumentDetails(@PathParam("id") @Size(min=1) String empId) {
        try {
            List<EmployeeDocument> empDocs = employeeRepository.getAllDocuments(empId);
            return Response.status(Response.Status.OK).entity(empDocs).build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("The documents not found", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Message(e.getMessage())).build();            
        } catch (Exception ex) {
            logger.error("The documents could not be fetched", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("{id}/document/{docId}")
    @Secured(Privilege.UPDATE_A_DOC_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateDetails(@PathParam("id") String employeeId, @PathParam("docId") Integer docId, EmployeeDocument empDoc) {
        try {
            employeeRepository.updateDocument(empDoc);;
            return Response.status(Response.Status.OK).entity(new Message("Document is successfully updated")).build();
        } catch (Exception ex) {
            logger.error("The document could not be updated", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }
    
    @GET
    @Path("{id}/payslip")
    @Secured(Privilege.READ_PAYSLIP_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPayslip(@PathParam("id") @Size(min=1) String employeeId,
    		 @QueryParam("month") String month, @QueryParam("year") int year) {
        try {
            EmployeePayslip employeePayslip = employeeRepository.getPayslip(employeeId, month, year);
            return Response.status(Response.Status.OK).entity(employeePayslip).build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("No payslip info found", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Message(e.getMessage())).build();            
        } catch (Exception ex) {
            logger.error("The payslip could not be fetched", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }
    
    @POST
    @Path("/{id}/payslip")
    @Secured(Privilege.INSERT_PAYSLIP_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertPayslip(@PathParam("id") @Size(min=1) String employeeId, EmployeePayslip employeePayslip) {
        try {
            employeeRepository.insertPayslip(employeeId, employeePayslip);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            logger.error("The employee payslip info could not be stored", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }
    
    @GET
    @Path("{id}/salarystack")
    @Secured(Privilege.READ_SALARYSTACK_OF_AN_EMP)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getSalaryStack(@PathParam("id") @Size(min=1) String employeeId) {
        try {
        	Employee employee = employeeRepository.getSalaryStack(employeeId);
            return Response.status(Response.Status.OK).entity(employee).build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("No salary stack info found", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Message(e.getMessage())).build();            
        } catch (Exception ex) {
            logger.error("The salary stack could not be fetched", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(ex.getMessage())).build();
        }
    }

}
