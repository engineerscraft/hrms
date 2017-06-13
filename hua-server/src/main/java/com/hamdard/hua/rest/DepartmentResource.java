package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Department;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DepartmentRepository;
import com.hamdard.hua.security.Secured;

@Path("/department")
public class DepartmentResource {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.DEPARTMENT_ALL_READ_CMD)
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

}
