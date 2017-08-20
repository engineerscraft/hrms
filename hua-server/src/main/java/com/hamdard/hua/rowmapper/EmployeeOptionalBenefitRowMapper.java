package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.SalaryOptComponent;
import com.hamdard.hua.model.Employee.EmployeeOptionalBenefit;

public class EmployeeOptionalBenefitRowMapper implements RowMapper<EmployeeOptionalBenefit> {

    public EmployeeOptionalBenefit mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	SalaryOptComponent salaryOptComponent= new SalaryOptComponent();
    	salaryOptComponent.setCreditDebitFlag(rs.getString("credit_debit_flag"));
    	salaryOptComponent.setDescription(rs.getString("description"));
    	salaryOptComponent.setEmpOptOutFlag(rs.getString("emp_opt_out_flag"));
    	salaryOptComponent.setEndDate(rs.getDate("end_date"));
    	salaryOptComponent.setFrequency(rs.getInt("frequency"));
    	salaryOptComponent.setOptCompId(rs.getInt("opt_comp_id"));
    	salaryOptComponent.setOptCompName(rs.getString("opt_component_name"));
    	salaryOptComponent.setSalOptComponent(rs.getString("sal_opt_component"));
    	
    	EmployeeOptionalBenefit employeeOptionalBenefit=new EmployeeOptionalBenefit();
    	employeeOptionalBenefit.setOptSalaryComponent(salaryOptComponent);
    	employeeOptionalBenefit.setBenefitValue(rs.getDouble("sal_opt_value"));
    	employeeOptionalBenefit.setFrequency(rs.getInt("sal_opt_frequency"));
    	employeeOptionalBenefit.setIterations(rs.getInt("no_of_iteration"));
    	employeeOptionalBenefit.setNextDueDate(rs.getDate("next_due_date"));
    	employeeOptionalBenefit.setRemarks(rs.getString("remarks"));
    	employeeOptionalBenefit.setStartDate(rs.getDate("sal_opt_end_date"));
    	employeeOptionalBenefit.setStopDate(rs.getDate("sal_opt_end_date"));
    	
    	return employeeOptionalBenefit;
    }
}