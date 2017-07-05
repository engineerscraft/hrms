package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Permission;

public class PermissionRowMapper implements RowMapper<Permission> {
    public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
        Permission permission = new Permission();
        permission.setPermissionId(rs.getInt("PERMISSION_ID"));
        permission.setPermissionName(rs.getString("PERMISSION_NAME"));
        permission.setPermissionType(rs.getString("PERMISSION_TYPE"));
        permission.setResourceName(rs.getString("RESOURCE_NAME"));
        return permission;
    }

}
