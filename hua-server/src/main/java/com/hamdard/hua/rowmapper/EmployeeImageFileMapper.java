package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeImageFileMapper implements RowMapper<byte[]> {

    @Override
    public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getBytes("EMPLOYEE_IMAGE");
    }

}
