package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee;

public class DocumentRowMapper  implements RowMapper<Employee.EmployeeDocument> {

    @Override
    public Employee.EmployeeDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee.EmployeeDocument document = new Employee.EmployeeDocument();
        document.setDocId(rs.getInt("DOC_ID"));
        document.setRemarks(rs.getString("REMARKS"));
        document.setDocTypeId(rs.getInt("DOC_TYPE_ID"));
        document.setDocTypeName(rs.getString("DOC_TYPE_NAME"));
        document.setDocument(rs.getString("DOCUMENT"));
        return document;

    }

}
