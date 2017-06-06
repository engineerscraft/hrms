package com.hamdard.hua.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommonRepository {
    private static final Logger logger = LogManager.getLogger(CommonRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Value("${sql.saveActivityHistory}")
    private String activityHistorySavingSql;

    /* It is expected to be called from other repositories and hence exceptions are not handled here. 
     * The main repositories should handle exceptions*/
    public void saveActivityHistory(String message, String username) {
        logger.info(sqlMarker, activityHistorySavingSql);
        logger.info(sqlMarker, "Params: {}, {}", ()->username, ()->message);
        jdbcTemplate.update(activityHistorySavingSql, username, message);
    }
}
