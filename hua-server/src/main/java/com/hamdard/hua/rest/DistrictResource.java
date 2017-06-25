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

import com.hamdard.hua.model.District;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DistrictRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author Biswajit
 *
 */
@Component
@Path("v1/district")
public class DistrictResource {
	
	@Autowired DistrictRepository districtRepository;
	
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//@Secured(Privilege.DISTRICT_ALL_READ_CMD)
	public District getDistrict(@QueryParam("stateId") int stateId){
		
			return districtRepository.getDistrictById(stateId);
		
	}

}
