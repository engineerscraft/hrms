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


import com.hamdard.hua.model.District;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DistrictRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Biswajit
 *
 */

@Path("v1/district")
public class DistrictResource {

    private static final Logger logger = LogManager.getLogger(DistrictResource.class);
    
    @Autowired
    DistrictRepository districtRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    // @Secured(Privilege.DISTRICT_ALL_READ_CMD)
    public Response getDistrict(@QueryParam("stateId") @Min(1) int stateId) {
        List<District> districts;
        try {
            districts = districtRepository.getDistrictsByStateId(stateId);
            if (districts.isEmpty()) {
                logger.error("No district is found for the given state.: {}", () -> stateId);
                return Response.status(404).entity(new Message("No district is found for the given state.")).build();
            } else
                return Response.status(200).entity(districts).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }

    }

}
