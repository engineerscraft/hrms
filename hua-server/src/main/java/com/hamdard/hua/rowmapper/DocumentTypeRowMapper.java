package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.DocType;

public class DocumentTypeRowMapper implements RowMapper<DocType> {
    public DocType mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocType documentType = new DocType();
        documentType.setDocTypeId(rs.getInt("DOC_TYPE_ID"));
        documentType.setDocTypeName(rs.getString("DOC_TYPE_NAME"));
        documentType.setIdentityDoc("X".equals(rs.getString("IDENTITY_DOC")));
        return documentType;
    }
}
