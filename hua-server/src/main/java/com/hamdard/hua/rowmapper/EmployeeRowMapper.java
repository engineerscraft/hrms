package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

	 public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Employee employee = new Employee();
	        employee.setId(rs.getLong("ID"));
	        employee.setFirstName(rs.getString("FIRST_NAME"));
	        employee.setLastName(rs.getString("LAST_NAME"));
	        employee.setMiddleName(rs.getString("MIDDLE_NAME"));
	        employee.setCollegeName(rs.getString("COLLEGE_NAME"));
	        employee.setStreetAddress(rs.getString("STREET_ADDRESS"));
	        employee.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
	        employee.setDesignation(rs.getString("DESIGNATION"));
	        employee.setQualification(rs.getString("QUALIFICATION"));
	        employee.setCity(rs.getString("CITY"));
	        employee.setEmergencyContact(rs.getString("EMERGENCY_CONTACT_NAME"));
	        employee.setEmergencyContactNumber(rs.getLong("EMERGENCY_CONTACT_NUMBER"));
	        employee.setContactNumber(rs.getLong("CONTACT_NUMBER"));
	        return employee;
	    }
}
