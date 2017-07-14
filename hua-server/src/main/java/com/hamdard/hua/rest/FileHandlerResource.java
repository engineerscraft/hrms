package com.hamdard.hua.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hamdard.hua.repository.FileHandlerRepository;

@RestController
public class FileHandlerResource {
	private static final Logger logger = LogManager.getLogger(FileHandlerResource.class);

	@Autowired
	private FileHandlerRepository fileHandlerRepository;

	@Context
	private SecurityContext securityContext;

	@RequestMapping(value = "{id}/upload", method = RequestMethod.POST)
	@ResponseBody()
	public Response uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") String employeeId) {
		// String employeeName = securityContext.getUserPrincipal().getName();
		if (file.isEmpty()) {
			logger.info("No file found");
			return Response.status(500).entity("No file found").build();
		} else {
			try {
				byte[] employeeImage = file.getBytes();
				fileHandlerRepository.insertEmployeeImage(employeeImage, employeeId);
				return Response.status(200).build();
			} catch (Exception ex) {
				logger.error("The Employee_Image could not be updated.", ex);
				return Response.status(500).entity(ex.getMessage()).build();
			}
		}
	}
}
