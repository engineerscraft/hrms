package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.State;
import com.hamdard.hua.privileges.Privilege;

import com.hamdard.hua.repository.StateRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Biswajit
 *
 */
@Component
@Path("v1/state")
public class StateResource {
	@Autowired
	StateRepository stateRepository;

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	// @Secured(Privilege.STATE_ALL_READ_CMD)
	public List<State> getState(@QueryParam("countryId") int countryId) {
		try {

			return stateRepository.getStateByCountryId(countryId);
		} catch (Exception e) {
			throw e;
		}

	}

}
