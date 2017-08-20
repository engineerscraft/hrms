package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee.EmployeeSalary;
import com.hamdard.hua.model.SalaryComponent;

public class EmployeeSalaryRowMapper implements RowMapper <EmployeeSalary>{

	public EmployeeSalary mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeSalary employeeSalary=new EmployeeSalary();
		SalaryComponent salaryComponent=new SalaryComponent();
		salaryComponent.setCompId(rs.getInt("comp_id"));
		salaryComponent.setCompName(rs.getString("component_name"));
		salaryComponent.setDescription(rs.getString("description"));
		salaryComponent.setSalComponent(rs.getString("sal_component"));
		employeeSalary.setSalaryComponent(salaryComponent);
		employeeSalary.setSalaryValue(rs.getDouble("sal_value"));
		return employeeSalary;
	}

}
