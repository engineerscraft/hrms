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

import com.hamdard.hua.model.Country;
import com.hamdard.hua.model.Department;
import com.hamdard.hua.rowmapper.CountryRowMapper;
import com.hamdard.hua.rowmapper.DepartmentRowMapper;

/**
 * @author Biswajit
 *
 */
public class CountryRepository {
	

	
	private static final Logger logger = LogManager.getLogger(CountryRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.country.list}")
    private String countryListSql;

	public List<Country> getAllCountries() {
		try {
            logger.info(sqlMarker, countryListSql);
            List<Country> countries = (List<Country>) jdbcTemplate.query(countryListSql, new CountryRowMapper());
            logger.debug("Retrieved countries: {}", () -> countries);
            return countries;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }
	}


}
