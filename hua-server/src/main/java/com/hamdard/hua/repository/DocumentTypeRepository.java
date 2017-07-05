package com.hamdard.hua.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.DocType;
import com.hamdard.hua.rowmapper.DocumentTypeRowMapper;

@Component
public class DocumentTypeRepository {

    private static final Logger logger = LogManager.getLogger(DocumentTypeRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.documenttype.list}")
    private String docTypeListSql;

    @Value("${sql.identitydocumenttype.list}")
    private String identityDocTypeListSql;

    public List<DocType> getAllDocumentTypes() {
        logger.info(sqlMarker, docTypeListSql);
        List<DocType> docTypeList = (List<DocType>) jdbcTemplate.query(docTypeListSql, new DocumentTypeRowMapper());
        logger.debug("Retrieved DocumentTypes: {}", () -> docTypeList);
        return docTypeList;
    }

    public List<DocType> getAllIdentityDocumentTypes() {
        logger.info(sqlMarker, identityDocTypeListSql);
        List<DocType> docTypeList = (List<DocType>) jdbcTemplate.query(identityDocTypeListSql, new DocumentTypeRowMapper());
        logger.debug("Retrieved DocumentTypes: {}", () -> docTypeList);
        return docTypeList;
    }
}
