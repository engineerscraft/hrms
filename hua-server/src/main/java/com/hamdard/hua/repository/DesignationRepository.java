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

import com.hamdard.hua.model.Designation;
import com.hamdard.hua.rowmapper.DesignationRowMapper;

@Component
public class DesignationRepository {
    private static final Logger logger = LogManager.getLogger(DesignationRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.designation.list.byJobRoleId}")
    private String designationListSql;

    public List<Designation> getDesignationsByJobRoleId(int jobRoleId) {
        Object[] args = { jobRoleId };
        logger.info(sqlMarker, designationListSql);
        logger.info(sqlMarker, "Params {}", () -> jobRoleId);
        List<Designation> designations = (List<Designation>) jdbcTemplate.query(designationListSql, args, new DesignationRowMapper());
        logger.debug("Retrieved designations: {}", () -> designations);
        return designations;
    }
}
