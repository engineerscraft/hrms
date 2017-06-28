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

import com.hamdard.hua.model.DocumentType;
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

	Logger logger = LogManager.getLogger(DocumentTypeResource.class);

	@Autowired
	DocumentTypeRepository docTypeRepository;
	
	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Secured(Privilege.DOCTYPE_ALL_READ_CMD)
	public Response getAllDocumentType() {
		List<DocumentType> docTypeList;
		CacheControl cc = new CacheControl();
		cc.setMaxAge(300);
		cc.setPrivate(true);
		cc.setNoStore(true);
		ResponseBuilder builder = null;

		try {
			docTypeList = docTypeRepository.getAllDocumentType();

			if (docTypeList.isEmpty()) {
				logger.error("No DocumentType is found.");
				builder = Response.status(404).entity(
						new Message("No DocumentType is found."));
				builder.cacheControl(cc);
				return builder.build();
			}
			/* If data presents in DB */
			else {
				builder = Response.status(200).entity(docTypeList);
				builder.cacheControl(cc);
				return builder.build();
			}
		} catch (Exception e) {
			builder = Response.status(500).entity(new Message(e.getMessage()));
			builder.cacheControl(cc);
			logger.error(e.getMessage());
			return builder.build();
		}
	}
}
