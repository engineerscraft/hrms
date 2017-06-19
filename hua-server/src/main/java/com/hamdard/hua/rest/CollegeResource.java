package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.College;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.CollegeRepository;
import com.hamdard.hua.security.Secured;

@Component
@Path("/college")
public class CollegeResource {

    @Autowired
    private CollegeRepository collegeRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.COLLEGE_ALL_READ_CMD)
    public List<College> getAllColleges() {
        return collegeRepository.getAllColleges();
    }

}
