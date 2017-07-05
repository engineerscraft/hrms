package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Designation;

public class DesignationRowMapper implements RowMapper<Designation> {

    public Designation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Designation designation = new Designation();
        designation.setDesignationId(rs.getLong("DESIGNATION_ID"));
        designation.setDesignationName(rs.getString("DESIGNATION"));
        return designation;
    }

}
