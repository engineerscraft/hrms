package com.hamdard.hua.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<ResourceNotFound> {
    public Response toResponse(ResourceNotFound ex) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
