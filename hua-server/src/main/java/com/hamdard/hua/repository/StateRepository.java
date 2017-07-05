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

import com.hamdard.hua.model.State;
import com.hamdard.hua.rowmapper.StateRowMapper;

/**
 * @author Biswajit
 *
 */
@Component
public class StateRepository {
    private static final Logger logger = LogManager.getLogger(StateRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.state.get.byCountryId}")
    private String stateGetByCountryIdSql;

    public List<State> getStateByCountryId(int countryId) {

        Object[] args = { countryId };
        logger.info(sqlMarker, stateGetByCountryIdSql);
        logger.info(sqlMarker, "Params {}", () -> countryId);
        List<State> states = (List<State>) jdbcTemplate.query(stateGetByCountryIdSql, args, new StateRowMapper());
        logger.debug("Retrieved states: {}", () -> states);
        return states;

    }

}
