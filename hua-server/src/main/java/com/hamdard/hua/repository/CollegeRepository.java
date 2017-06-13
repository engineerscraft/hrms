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

import com.hamdard.hua.model.College;
import com.hamdard.hua.rowmapper.CollegeRowMapper;


@Component
public class CollegeRepository {

	private static final Logger logger = LogManager.getLogger(CollegeRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.college.list}")
    private String collegeListSql;

	public List<College> getAllColleges() {
		try {
            logger.info(sqlMarker, collegeListSql);
            List<College> colleges = (List<College>) jdbcTemplate.query(collegeListSql, new CollegeRowMapper());
            logger.debug("Retrieved colleges: {}", () -> colleges);
            return colleges;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }
	}
}
