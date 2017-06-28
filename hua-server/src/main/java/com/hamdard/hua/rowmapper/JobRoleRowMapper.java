package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.JobRole;

public class JobRoleRowMapper implements RowMapper<JobRole>{
	public JobRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobRole jobRole=new JobRole();
		jobRole.setOrgId(rs.getInt("ORG_ID"));
		jobRole.setJobRoleId(rs.getInt("JOB_ROLE_ID"));
		jobRole.setJobRoleId(rs.getInt("GRADE_ID"));
		jobRole.setJobRoleId(rs.getInt("PROBATION_NOTICE_PERIOD"));
		jobRole.setJobRoleId(rs.getInt("NOTICE_PERIOD"));
		return jobRole;
	}
}
