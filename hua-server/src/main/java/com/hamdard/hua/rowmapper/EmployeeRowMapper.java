package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.EmployeeOld;

public class EmployeeRowMapper implements RowMapper<EmployeeOld> {

    public EmployeeOld mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeOld employee = new EmployeeOld();
        employee.setId(rs.getLong("ID"));
        employee.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
        employee.setMiddleName(rs.getString("MIDDLE_NAME"));
        employee.setCollegeId(rs.getLong("COLLEGE_ID"));
        employee.setStreetAddress(rs.getString("STREET_ADDRESS"));
        employee.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
        employee.setDesignationId(rs.getLong("DESIGNATION_ID"));
        employee.setQualification(rs.getString("QUALIFICATION"));
        employee.setCityId(rs.getLong("CITY_ID"));
        employee.setEmergencyContact(rs.getString("EMERGENCY_CONTACT_NAME"));
        employee.setEmergencyContactNumber(rs.getString("EMERGENCY_CONTACT_NUMBER"));
        employee.setContactNumber(rs.getString("CONTACT_NUMBER"));
        employee.setPostalCode(rs.getString("POSTAL_CODE"));
        employee.setDepartmentId(rs.getLong("DEPARTMENT_ID"));
        return employee;
    }
}
