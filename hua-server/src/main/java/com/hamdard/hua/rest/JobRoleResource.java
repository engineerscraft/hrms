package com.hamdard.hua.rest;

import java.util.List;

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.JobRole;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.JobRoleRepository;
import com.hamdard.hua.security.Secured;

@Path("v1/jobrole")
public class JobRoleResource {

    private static final Logger logger = LogManager.getLogger(JobRoleResource.class);

    @Autowired
    JobRoleRepository jobRoleRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.DISTRICT_ALL_READ_CMD)
    public Response getDistrict(@QueryParam("orgId") @Min(1) int orgId) {
        List<JobRole> jobRoles;
        try {
            jobRoles = jobRoleRepository.getJobRolesByOrgId(orgId);
            if (jobRoles.isEmpty()) {
                logger.error("No job role is found.");
                return Response.status(404).entity(new Message("No job role is found.")).build();
            }
            /* If data presents in DB */
            else
                return Response.status(200).entity(jobRoles).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.status(500).entity(new Message(e.getMessage())).build();
        }
    }
}
