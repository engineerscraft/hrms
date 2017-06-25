/**
 * 
 */
package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Unit;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.UnitRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Somdeb
 *
 */
@Component
@Path("v1/unit")
public class UnitResource {
	
	@Autowired UnitRepository unitRepository;
	
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Secured(Privilege.UNIT_ALL_READ_CMD)
	public List<Unit> getUnits(@QueryParam("organizationId") int orgId){
		return unitRepository.getUnits(orgId);
	}

}
