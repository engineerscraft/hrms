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

import com.hamdard.hua.model.JobRole;
import com.hamdard.hua.rowmapper.JobRoleRowMapper;

@Component
public class JobRoleRepository {
    private static final Logger logger = LogManager.getLogger(JobRoleRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.jobrole.get.byOrgId}")
    private String jobroleGetByOrgIdSql;

    public List<JobRole> getJobRolesByOrgId(int orgId) {

        logger.info(sqlMarker, jobroleGetByOrgIdSql);
        logger.info(sqlMarker, "Params {}", () -> orgId);
        List<JobRole> jobRoles = (List<JobRole>) jdbcTemplate.query(jobroleGetByOrgIdSql, new Object[] { orgId }, new JobRoleRowMapper());
        logger.debug("Retrieved job roles: {}", () -> jobRoles);
        return jobRoles;
    }
}
