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

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Country;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.CountryRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Biswajit
 *
 */

@Path("v1/country")
public class CountryResource {
	@Autowired CountryRepository countryRepository;
	
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//@Secured(Privilege.COUNTRY_ALL_READ_CMD)
	public List<Country> getAllCountries(){
		return countryRepository.getAllCountries();
	}

}
