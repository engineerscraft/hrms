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
import com.hamdard.hua.model.Salary;
import com.hamdard.hua.rowmapper.JobRoleRowMapper;
import com.hamdard.hua.rowmapper.SalaryRowMapper;

@Component
public class JobRoleRepository {
    private static final Logger logger = LogManager.getLogger(JobRoleRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.jobrole.get.byOrgId}")
    private String jobroleGetByOrgIdSql;

    @Value("${sql.salary.get.byJobRoleId}")
    private String salaryGetByJobRoleIdSql;

    @Value("${sql.optbenefits.get.byJobRoleId}")
    private String OptBenefitsGetByJobRoleIdSql;

    public List<JobRole> getJobRolesByOrgId(int orgId) {

        logger.info(sqlMarker, jobroleGetByOrgIdSql);
        logger.info(sqlMarker, "Params {}", () -> orgId);
        List<JobRole> jobRoles = (List<JobRole>) jdbcTemplate.query(jobroleGetByOrgIdSql, new Object[] { orgId }, new JobRoleRowMapper());
        logger.debug("Retrieved job roles: {}", () -> jobRoles);
        return jobRoles;
    }

    public List<Salary> getSalaryByJobRoleId(long jobRoleId) {

        logger.info(sqlMarker, salaryGetByJobRoleIdSql);
        logger.info(sqlMarker, "Params {}", () -> jobRoleId);
        List<Salary> salary = (List<Salary>) jdbcTemplate.query(salaryGetByJobRoleIdSql, new Object[] { jobRoleId }, new SalaryRowMapper());
        logger.debug("Retrieved salary : {}", () -> salary);
        return salary;
    }

    public List<Salary> getOptionalBenefitsByJobRoleId(long jobRoleId) {

        logger.info(sqlMarker, OptBenefitsGetByJobRoleIdSql);
        logger.info(sqlMarker, "Params {}", () -> jobRoleId);
        List<Salary> optBenefits = (List<Salary>) jdbcTemplate.query(OptBenefitsGetByJobRoleIdSql, new Object[] { jobRoleId }, new SalaryRowMapper());
        logger.debug("Retrieved OptionalBenefits : {}", () -> optBenefits);
        return optBenefits;
    }
}
