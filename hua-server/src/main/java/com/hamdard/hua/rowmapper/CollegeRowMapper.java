package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.College;

public class CollegeRowMapper implements RowMapper<College>{
	 public College mapRow(ResultSet rs, int rowNum) throws SQLException {
	        College college = new College();
	        college.setCollegeId(rs.getLong("ID"));
	        college.setCollegeName(rs.getString("NAME"));
	        return college;
	    }

}
