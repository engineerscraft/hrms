/**
 * 
 */
package com.hamdard.hua.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Country;
import com.hamdard.hua.rowmapper.CountryRowMapper;

/**
 * @author Biswajit
 *
 */
@Component
public class CountryRepository {

    private static final Logger logger = LogManager.getLogger(CountryRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.country.list}")
    private String countryListSql;

    public List<Country> getAllCountries() {

        logger.info(sqlMarker, countryListSql);
        List<Country> countries = (List<Country>) jdbcTemplate.query(countryListSql, new CountryRowMapper());
        logger.debug("Retrieved countries: {}", () -> countries);
        return countries;

    }

}
