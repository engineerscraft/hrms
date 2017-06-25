package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.ErrorBody;
import com.hamdard.hua.model.Permission;
import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.security.Secured;

@Component
@Path("/permission")
public class PermissionEndpoint {

	private static final Logger logger = LogManager.getLogger(AuthenticationRepository.class);

	@Autowired
	AuthenticationRepository authenticationRepository;

	@Context
	SecurityContext securityContext;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Secured
	public Response getPermissions(@QueryParam("permissionLevel") String permissionLevel) {
		try {
			List<Permission> permissions = authenticationRepository
					.retrivePermissions(securityContext.getUserPrincipal().getName(), permissionLevel);
			if (permissions.size() == 0) {
				logger.debug("No permission given to the user: {}", () -> securityContext.getUserPrincipal().getName());
				return Response.status(404).entity(new ErrorBody("No permission given to the user")).build();
			} else
				return Response.status(200).entity(permissions).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(500).entity(new ErrorBody(e.getMessage())).build();
		}
	}
}
