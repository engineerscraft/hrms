package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Organization;

/**
 * @author isomdeb
 *
 */
public class OrganizationRowMapper implements RowMapper<Organization> {

    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organization org = new Organization();
        org.setOrgId(rs.getInt("ORG_ID"));
        org.setOrgName(rs.getString("ORG_NAME"));
        org.setAddress(rs.getString("ADDRESS"));
        org.setOrgTypeId(rs.getInt("ORG_TYPE_ID"));
        org.setOrgTypeName(rs.getString("ORG_TYPE_NAME"));
        org.setDescription(rs.getString("DESCRIPTION"));
        return org;
    }

}
