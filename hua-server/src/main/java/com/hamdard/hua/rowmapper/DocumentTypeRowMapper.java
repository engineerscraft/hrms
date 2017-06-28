package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.DocumentType;

public class DocumentTypeRowMapper implements RowMapper<DocumentType>{
	public DocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentType documentType = new DocumentType();
		documentType.setDocTypeId(rs.getLong("DOC_TYPE_ID"));
		documentType.setDocTypeName(rs.getString("DOC_TYPE_NAME"));
		documentType.setIdentitydoc(rs.getString("IDENTITY_DOC"));
		return documentType;
	}
}

