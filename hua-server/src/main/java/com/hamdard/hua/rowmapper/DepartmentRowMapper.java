package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Department;

public class DepartmentRowMapper implements RowMapper<Department>{
	 public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		 	Department department = new Department();
		 	department.setDepartmentId(rs.getLong("ID"));
		 	department.setDepartmentName(rs.getString("NAME"));
	        return department;
	    }

}
