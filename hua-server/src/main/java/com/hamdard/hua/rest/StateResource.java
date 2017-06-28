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
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.State;
import com.hamdard.hua.repository.StateRepository;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.security.Secured;

/**
 * @author Biswajit
 *
 */
@Component
@Path("v1/state")
public class StateResource {

    private static final Logger logger = LogManager.getLogger(StateResource.class);

    @Autowired
    StateRepository stateRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.STATE_ALL_READ_CMD)
    public Response getState(@QueryParam("countryId") @Min(1) int countryId) throws Exception {
        List<State> states;
        try {
            states = stateRepository.getStateByCountryId(countryId);

            /* If countryId doesn't return any values */
            if (states.isEmpty()) {
                logger.error("No state is found for countryId: {}", () -> countryId);
                return Response.status(404).entity(new Message("No state found for the given country.")).build();
            }

            /* If countryId return associated values */
            else
                return Response.status(200).entity(states).build();
        }

        /* Any other kind of exception */
        catch (Exception ex) {
        	logger.error("The states could not be retrieved", ex);
            return Response.status(500).entity(new Message(ex.getMessage())).build();
        }

    }

}
