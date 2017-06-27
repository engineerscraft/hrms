package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.Organization;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.OrganizationRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Somdeb
 *
 */
@Path("v1/organization")
public class OrganizationResource {

    private static final Logger logger = LogManager.getLogger(OrganizationResource.class);

    @Autowired
    OrganizationRepository organizationRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.GET_ALL_ORGS)
    public Response getOrgs() {
        List<Organization> orgs;
        try {
            orgs = organizationRepository.getAllOrganizations();
            /* If it doesn't return any values */
            if (orgs.isEmpty()) {
                logger.error("No organizations are found");
                return Response.status(404).entity(new Message("No organizations found")).build();
            }
            /* If it return associated values */
            else
                return Response.status(200).entity(orgs).build();
        }
        /* Any other kind of exception */
        catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }

}