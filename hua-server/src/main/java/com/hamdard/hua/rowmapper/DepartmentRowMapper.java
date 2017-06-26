package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Department;
import com.hamdard.hua.model.Unit;

/**
 * @author isomdeb
 *
 */
public class DepartmentRowMapper implements RowMapper<Department> {

    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        department.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
        department.setParentDepartmentId(rs.getInt("PARENT_DEPARTMENT_ID"));
        department.setAddress(rs.getString("ADDRESS"));
        department.setUnitId(rs.getInt("UNIT_ID"));
        department.setUnitName(rs.getString("UNIT_NAME"));
        return department;
    }

}
