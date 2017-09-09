package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.SalaryOptComponent;

public class SalaryOptComponentRowMapper implements RowMapper<SalaryOptComponent> {

	public SalaryOptComponent mapRow(ResultSet rs, int rowNum) throws SQLException {

		SalaryOptComponent salaryOptComponent = new SalaryOptComponent();
		salaryOptComponent.setCreditDebitFlag(rs.getString("credit_debit_flag"));
		salaryOptComponent.setDescription(rs.getString("description"));
		salaryOptComponent.setEmpOptOutFlag(rs.getString("emp_opt_out_flag"));
		salaryOptComponent.setEndDate(rs.getDate("end_date"));
		salaryOptComponent.setFrequency(rs.getInt("frequency"));
		salaryOptComponent.setOptCompId(rs.getInt("opt_comp_id"));
		salaryOptComponent.setOptCompName(rs.getString("opt_component_name"));
		salaryOptComponent.setSalOptComponent(rs.getString("sal_opt_component"));

		return salaryOptComponent;
	}
}
