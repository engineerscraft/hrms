package com.hamdard.hua.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hamdard.hua.model.DocType;
import com.hamdard.hua.model.Message;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.DocumentTypeRepository;
import com.hamdard.hua.security.Secured;

/**
 * @author USER
 * This class returns all the documentType.
 */

@Path("/v1/doctype")
public class DocumentTypeResource {

    private static final Logger logger = LogManager.getLogger(DocumentTypeResource.class);

    @Autowired
    private DocumentTypeRepository docTypeRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.READ_ALL_DOC_TYPES)
    public Response getAllDocumentTypes() {
        List<DocType> docTypeList;
        CacheControl cc = new CacheControl();
        cc.setMaxAge(300);
        cc.setPrivate(true);
        cc.setNoStore(true);
        ResponseBuilder builder = null;

        try {
            docTypeList = docTypeRepository.getAllDocumentTypes();

            if (docTypeList.isEmpty()) {
                logger.error("No DocumentType is found.");
                builder = Response.status(Response.Status.NOT_FOUND).entity(new Message("No DocumentType is found."));
                return builder.build();
            }
            /* If data presents in DB */
            else {
                builder = Response.status(Response.Status.OK).entity(docTypeList);
                builder.cacheControl(cc);
                return builder.build();
            }
        } catch (Exception e) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage()));
            logger.error(e.getMessage());
            return builder.build();
        }
    }

    @GET
    @Path("/identitydoctype")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Secured(Privilege.READ_ID_DOC_TYPES)
    public Response getAllIdentityDocumentTypes() {
        List<DocType> docTypeList;
        CacheControl cc = new CacheControl();
        cc.setMaxAge(300);
        cc.setPrivate(true);
        cc.setNoStore(true);
        ResponseBuilder builder = null;

        try {
            docTypeList = docTypeRepository.getAllIdentityDocumentTypes();

            if (docTypeList.isEmpty()) {
                logger.error("No DocumentType is found.");
                builder = Response.status(Response.Status.NOT_FOUND).entity(new Message("No DocumentType is found."));
                return builder.build();
            }
            /* If data presents in DB */
            else {
                builder = Response.status(Response.Status.OK).entity(docTypeList);
                builder.cacheControl(cc);
                return builder.build();
            }
        } catch (Exception e) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage()));
            logger.error(e.getMessage());
            return builder.build();
        }
    }
}
