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

import com.hamdard.hua.model.Organization;
import com.hamdard.hua.rowmapper.OrganizationRowMapper;

/**
 * @author Somdeb
 *
 */
@Component
public class OrganizationRepository {

    private static final Logger logger = LogManager.getLogger(OrganizationRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.organization.list}")
    private String orgListSql;

    public List<Organization> getAllOrganizations() {

        logger.info(sqlMarker, orgListSql);
        List<Organization> orgs = (List<Organization>) jdbcTemplate.query(orgListSql, new OrganizationRowMapper());
        logger.debug("Retrieved organizations: {}", () -> orgs.toString());
        return orgs;

    }

}
