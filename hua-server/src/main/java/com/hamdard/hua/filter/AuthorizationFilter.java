package com.hamdard.hua.filter;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Message;
import com.hamdard.hua.model.Permission;
import com.hamdard.hua.privileges.Privilege;
import com.hamdard.hua.repository.AuthenticationRepository;
import com.hamdard.hua.security.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
@Component
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final Logger logger = LogManager.getLogger(AuthorizationFilter.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        Privilege classPriv = extractPriv(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        Privilege methodPriv = extractPriv(resourceMethod);

        try {

            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations

            String username = requestContext.getSecurityContext().getUserPrincipal().getName();

            List<Permission> permissions = null;

            if (methodPriv.equals(methodPriv.DEFAULT) && classPriv.equals(methodPriv.DEFAULT)) {
                // do nothing and allow the call to happen
            } else {

                if (methodPriv.equals(methodPriv.DEFAULT)) {
                    permissions = authenticationRepository.retrivePermissions(username, classPriv.toString());
                } else {
                    permissions = authenticationRepository.retrivePermissions(username, methodPriv.toString());
                }

                if (permissions == null || permissions.size() == 0) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(new Message("The user does not have permission to execute this command")).build());
                }
            }
        } catch (Exception e) {
            logger.error("Exception", e);
            requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Message(e.getMessage())).build());
        }
    }

    // Extract the roles from the annotated element
    private Privilege extractPriv(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return Privilege.DEFAULT;
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return Privilege.DEFAULT;
            } else {
                Privilege allowedPriv = secured.value();
                return allowedPriv;
            }
        }
    }
}