/**
 * 
 */
package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.District;

/**
 * @author Biswajit
 *
 */
public class DistrictRowMapper implements RowMapper<District> {
    public District mapRow(ResultSet rs, int rowNum) throws SQLException {
        District district = new District();
        district.setDistrictId(rs.getInt("DISTRICT_ID"));
        district.setDistrictName(rs.getString("DISTRICT_NAME"));
        district.setStateId(rs.getInt("STATE_ID"));
        district.setStateName(rs.getString("STATE_NAME"));
        return district;
    }
}
