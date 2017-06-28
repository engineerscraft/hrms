package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

	Logger logger = LogManager.getLogger(DesignationResource.class);

	@Autowired
	private DesignationRepository designationRepository;

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Secured(Privilege.DESIGNATION_ALL_READ_CMD)
	public Response getAllDesignation() {
		List<Designation> designation;
		CacheControl cc = new CacheControl();
		cc.setMaxAge(300);
		cc.setPrivate(true);
		cc.setNoStore(true);
		ResponseBuilder builder = null;

		try {
			designation = designationRepository.getAllDesignations();

			if (designation.isEmpty()) {
				logger.error("No Designation is found.");
				builder = Response.status(404).entity(
						new Message("No Designation is found."));
				builder.cacheControl(cc);
				return builder.build();
			}
			/* If data presents in DB */
			else {
				builder = Response.status(200).entity(designation);
				builder.cacheControl(cc);
				return builder.build();
			}
		} catch (Exception e) {
			builder = Response.status(500).entity(new Message(e.getMessage()));
			builder.cacheControl(cc);
			logger.error(e.getMessage());
			return builder.build();
		}
	}
}
