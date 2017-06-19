package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Designation;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DesignationRepository;
import com.hamdard.hua.security.Secured;

@Component
@Path("/designation")
public class DesignationResource {
	
    @Autowired
    private DesignationRepository designationRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.DESIGNATION_ALL_READ_CMD)
    public List<Designation> getAllDesignations() {
        return designationRepository.getAllDesignations();
    }
}
