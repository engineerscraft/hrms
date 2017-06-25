package com.hamdard.hua.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.EmployeeOld;
import com.hamdard.hua.repository.EmployeeRepository;

@Component
@Path("/employee")
public class EmployeeResource {

	@Autowired private EmployeeRepository employeeRepository;
	
    @GET
    @Path("/test")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<String> test() {
    	List<String> strArr=new ArrayList<String>();
    	strArr.add("1");
    	strArr.add("2");
        return strArr;
    }
    
    
    @GET
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<EmployeeOld> getEmployees() {
        return employeeRepository.getAllEmployees();
    }
    
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public EmployeeOld getEmployee(@PathParam("id") Long id) {
        return employeeRepository.getEmployeeById(id);
    }
    
    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public EmployeeOld createEmployee(EmployeeOld employee) {
        return employeeRepository.createEmployee(employee);
    }
    
    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<String> getEmployeeAutocomplete(@QueryParam("name") String name, @QueryParam("value") String value) {
    	return employeeRepository.getValues(name,value);
    }

    @GET
    @Path("/search")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<EmployeeOld> searchEmployee(@QueryParam("firstName") String firstName, @QueryParam("middleName") String middleName,
			@QueryParam("lastName") String lastName, @QueryParam("designationId") Long designationId,
			@QueryParam("departmentId") Long departmentId, @QueryParam("collegeId") Long collegeId,
			@QueryParam("employeeId") Long employeeId, @QueryParam("emailAddress") String emailAddress,
			@QueryParam("contactNumber") String contactNumber) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	
    	if(firstName!=null &&firstName.trim().length()>0){
    		map.put("FIRST_NAME", firstName.trim());
    	}
    	
    	if(middleName!=null &&middleName.trim().length()>0){
    		map.put("MIDDLE_NAME", middleName.trim());
    	}
    	if(lastName!=null &&lastName.trim().length()>0){
    		map.put("LAST_NAME", lastName.trim());
    	}
    	
    	if(emailAddress!=null &&emailAddress.trim().length()>0){
    		map.put("EMAIL_ADDRESS", emailAddress.trim());
    	}

    	if(contactNumber!=null &&contactNumber.trim().length()>0){
    		map.put("CONTACT_NUMBER", contactNumber.trim());
    	}

    	if(designationId !=null && designationId !=0l){
    		map.put("DESIGNATION_ID", designationId);
    	}
    	
    	if(departmentId !=null && departmentId !=0l){
    		map.put("DEPARTMENT_ID", departmentId);
    	}

    	if(employeeId !=null && employeeId !=0l){
    		map.put("ID", employeeId);
    	}
    	if(collegeId !=null && collegeId !=0l){
    		map.put("COLLEGE_ID", collegeId);
    	}
    	if(map.size()==0){
    		return new ArrayList<EmployeeOld>();
    	}
    	return employeeRepository.searchByColumnMap(map);
    }

    
}
