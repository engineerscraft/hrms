/**
 * 
 */
package com.hamdard.hua.repository;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.District;
import com.hamdard.hua.rowmapper.DistrictRowMapper;
/**
 * @author Biswajit
 *
 */
@Component
public class DistrictRepository {
	
	private static final Logger logger = LogManager.getLogger(DistrictRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Value("${sql.district.get.byStateId}")
    private String districtGetByStateIdSql;
    
    public List<District> getDistrictsByStateId(int districtId){
    	try{
    		Object[] args={districtId};
    		logger.info(sqlMarker, districtGetByStateIdSql);
    		List<District> districts=(List<District>)jdbcTemplate.query(districtGetByStateIdSql, args, new DistrictRowMapper());
    		logger.debug("Retrieved district: {}", () -> districts);
    		return districts;
    	}
    	catch(Exception e){
    		logger.error("No parameter found", e);
            throw new InternalServerErrorException();
    	}
    }

}
