package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Parameter;

public class ParameterRowMapper implements RowMapper<Parameter> {
    public Parameter mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parameter parameter = new Parameter();
        parameter.setKey(rs.getString("KEY"));
        parameter.setValue(rs.getString("VALUE"));
        parameter.setDescription(rs.getString("DESCRIPTION"));
        parameter.setClobValue(rs.getString("CLOB_VALUE"));
        return parameter;
    }

}
