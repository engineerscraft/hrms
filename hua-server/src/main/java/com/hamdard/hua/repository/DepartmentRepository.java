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

import com.hamdard.hua.model.Department;
import com.hamdard.hua.rowmapper.DepartmentRowMapper;

/**
 * @author Somdeb
 *
 */
@Component
public class DepartmentRepository {

    private static final Logger logger = LogManager.getLogger(DepartmentRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.department.list}")
    private String departmentListSql;

    public List<Department> getDepartmentsByUnitId(int unitId) {

        Object[] args = { unitId };
        logger.info(sqlMarker, departmentListSql);
        logger.info(sqlMarker, "Params {}", () -> unitId);
        List<Department> departments = (List<Department>) jdbcTemplate.query(departmentListSql, args, new DepartmentRowMapper());
        logger.debug("Retrieved departments: {}", () -> departments.toString());
        return departments;

    }

}
