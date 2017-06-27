package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Unit;

/**
 * @author isomdeb
 *
 */
public class UnitRowMapper implements RowMapper<Unit> {

    public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Unit unit = new Unit();
        unit.setUnitId(rs.getInt("UNIT_ID"));
        unit.setUnitName(rs.getString("UNIT_NAME"));
        unit.setOrgName(rs.getString("ORG_NAME"));
        unit.setEmpIdPrefix(rs.getString("EMP_ID_PREFIX"));
        unit.setEmpIdSeqName(rs.getString("EMP_ID_SEQ_NAME"));
        unit.setAddress(rs.getString("ADDRESS"));
        unit.setOrgId(rs.getInt("ORG_ID"));
        return unit;
    }

}
