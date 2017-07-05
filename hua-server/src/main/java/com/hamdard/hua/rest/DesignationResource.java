package com.hamdard.hua.rest;

import java.util.List;

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Designation;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DesignationRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author 
 * This class returns all the designations.
 */

@Path("/v1/designation")
public class DesignationResource {

    private static final Logger logger = LogManager.getLogger(DesignationResource.class);

    @Autowired
    private DesignationRepository designationRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.READ_DSGNS_OF_A_JOB_ROLE)
    public Response getAllDesignation(@QueryParam("jobRoleId") @Min(1) int jobRoleId) {
        List<Designation> designation;
        CacheControl cc = new CacheControl();
        cc.setMaxAge(300);
        cc.setPrivate(true);
        cc.setNoStore(true);
        ResponseBuilder builder = null;

        try {
            designation = designationRepository.getDesignationsByJobRoleId(jobRoleId);

            if (designation.isEmpty()) {
                logger.error("No Designation is found.");
                builder = Response.status(Response.Status.NOT_FOUND).entity(new Message("No Designation is found."));
                builder.cacheControl(cc);
                return builder.build();
            }
            /* If data presents in DB */
            else {
                builder = Response.status(Response.Status.OK).entity(designation);
                builder.cacheControl(cc);
                return builder.build();
            }
        } catch (Exception e) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage()));
            logger.error("The designations could not be retrieved", e);
            return builder.build();
        }
    }
}
