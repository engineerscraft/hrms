package com.hamdard.hua.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.repository.EmployeeRepository;

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
	public List<Employee> getEmployees() {
        return employeeRepository.getAllEmployees();
    }
    
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee getEmployee(@PathParam("id") Long id) {
        return employeeRepository.getEmployeeById(id);
    }
    
}
