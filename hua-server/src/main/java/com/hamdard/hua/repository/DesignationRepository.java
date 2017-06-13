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

import com.hamdard.hua.model.Designation;
import com.hamdard.hua.rowmapper.DesignationRowMapper;

@Component
public class DesignationRepository {
	private static final Logger logger = LogManager.getLogger(DesignationRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.designation.list}")
    private String designationListSql;

	public List<Designation> getAllDesignations() {
		try {
            logger.info(sqlMarker, designationListSql);
            List<Designation> designations = (List<Designation>) jdbcTemplate.query(designationListSql, new DesignationRowMapper());
            logger.debug("Retrieved designations: {}", () -> designations);
            return designations;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }

	}
}
