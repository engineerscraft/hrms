package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Salary;

public class SalaryRowMapper implements RowMapper<Salary> {

    public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
        Salary salary = new Salary();
        salary.setSalaryId(rs.getLong("SALARY_ID"));
        salary.setDescription(rs.getString("DESCRIPTION"));
        salary.setJobRoleId(rs.getLong("JOB_ROLE_ID"));
        salary.setSalCompId(rs.getLong("SAL_COMP_ID"));
        salary.setSalValue(rs.getDouble("SAL_VALUE"));
        salary.setMaxAllowLimit(rs.getDouble("MAX_ALLOW_LIMIT"));
        salary.setSalOptCompFlag(rs.getInt("SAL_OPT_COMP_FLAG"));
        return salary;
    }
}
