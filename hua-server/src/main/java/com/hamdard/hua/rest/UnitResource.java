/**
 * 
 */
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

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.Unit;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.UnitRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Somdeb
 *
 */
@Path("v1/unit")
public class UnitResource {

    private static final Logger logger = LogManager.getLogger(UnitResource.class);

    @Autowired
    UnitRepository unitRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.UNIT_ALL_READ_CMD)
    public Response getUnits(@QueryParam("organizationId") @Min(1) int orgId) {
        List<Unit> units;
        try {
            units = unitRepository.getUnitsByOrganizationId(orgId);
            /* If passed orgId doesn't return any values */
            if (units.isEmpty()) {
                logger.error("No units are found for orgId: {}", () -> orgId);
                return Response.status(404).entity(new Message("No units found for the given organization")).build();
            }
            /* If orgId return associated values */
            else
                return Response.status(200).entity(units).build();
        }
        /* Any other kind of exception */
        catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }
    }

}