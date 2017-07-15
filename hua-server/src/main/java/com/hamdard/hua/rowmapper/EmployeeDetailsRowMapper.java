package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee;

public class EmployeeDetailsRowMapper implements RowMapper<Employee.EmployeeBasicInfo> {

    @Override
    public Employee.EmployeeBasicInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee.EmployeeBasicInfo empInfo = new Employee.EmployeeBasicInfo();
        empInfo.setContactNo(rs.getString("CONTACTNO"));
        empInfo.setDob(rs.getDate("DATE_OF_BIRTH"));
        empInfo.setDoj(rs.getDate("DATE_OF_JOINING"));
        empInfo.setEmailId(rs.getString("EMAIL_ID"));
        empInfo.setEmpFirstName(rs.getString("EMPLOYEE_FIRST_NAME"));
        empInfo.setEmpId(rs.getString("EMP_ID"));
        empInfo.setEmpLastName(rs.getString("EMPLOYEE_LAST_NAME"));
        empInfo.setEmpMiddleName(rs.getString("EMPLOYEE_LAST_NAME"));
        empInfo.setEmpType(rs.getString("EMP_TYPE"));
        empInfo.setFatherName(rs.getString("FATHER_NAME"));
        empInfo.setTitle(rs.getString("TITLE"));
        empInfo.setSex(rs.getString("SEX"));
        empInfo.setNationality(rs.getString("NATIONALITY"));
        empInfo.setMaritalStatus(rs.getString("MARITAL_STATUS"));
        return empInfo;
    }

}
