package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee;

public class EmployeeSearchResultRowMapper implements RowMapper<Employee.EmployeeSearchResult> {

    @Override
    public Employee.EmployeeSearchResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee.EmployeeSearchResult searchResult = new Employee.EmployeeSearchResult();
        searchResult.setEmpId(rs.getString("EMP_ID"));
        searchResult.setDesignation(rs.getString("DESIGNATION"));
        searchResult.setEmailId(rs.getString("EMAIL_ID"));
        searchResult.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
        searchResult.setContactNo(rs.getString("CONTACTNO"));
        searchResult.setFirstName(rs.getString("EMPLOYEE_FIRST_NAME"));
        searchResult.setMiddleName(rs.getString("EMPLOYEE_MIDDLE_NAME"));
        searchResult.setLastName(rs.getString("EMPLOYEE_LAST_NAME"));
        searchResult.setSupervisorEmailId(rs.getString("SUPERVISOR_EMAIL_ID"));
        searchResult.setHrEmailId(rs.getString("HR_EMAIL_ID"));
        StringBuilder name = new StringBuilder();
        if (rs.getString("EMPLOYEE_FIRST_NAME") != null) {
            name.append(rs.getString("EMPLOYEE_FIRST_NAME"));
        }
        if (rs.getString("EMPLOYEE_MIDDLE_NAME") != null) {
            name.append(" ");
            name.append(rs.getString("EMPLOYEE_MIDDLE_NAME"));
        }
        if (rs.getString("EMPLOYEE_LAST_NAME") != null) {
            name.append(" ");
            name.append(rs.getString("EMPLOYEE_LAST_NAME"));
        }
        searchResult.setName(name.toString());
        return searchResult;
    }

}
