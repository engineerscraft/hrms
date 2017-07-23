/**
 * This API will handle all kind
 *	of requests associated with Country
 */
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

import com.hamdard.hua.model.Country;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.CountryRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Biswajit
 *
 */

@Path("v1/country")
public class CountryResource {

    private static final Logger logger = LogManager.getLogger(CountryResource.class);

    @Autowired
    private CountryRepository countryRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.READ_ALL_COUNTRIES)
    public Response getAllCountries() {
        List<Country> countries;
        try {
            countries = countryRepository.getAllCountries();
            if (countries.isEmpty()) {
                logger.error("No country is found.");
                return Response.status(Response.Status.NOT_FOUND).entity(new Message("No country is found.")).build();
            }
            /* If data presents in DB */
            else
                return Response.status(Response.Status.OK).entity(countries).build();
        } catch (Exception e) {
            logger.error("The countries could not be retrieved", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build();
        }
    }

}
