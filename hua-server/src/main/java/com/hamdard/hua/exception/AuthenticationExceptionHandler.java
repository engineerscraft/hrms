package com.hamdard.hua.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionHandler implements ExceptionMapper<AuthenticationFailure> {
    public Response toResponse(AuthenticationFailure ex) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
