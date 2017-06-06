package com.hamdard.hua.javaconfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.hamdard.hua.exception.AuthenticationExceptionHandler;
import com.hamdard.hua.exception.ResourceNotFoundExceptionHandler;
import com.hamdard.hua.filter.AuthenticationFilter;
import com.hamdard.hua.filter.AuthorizationFilter;
import com.hamdard.hua.rest.AuthenticationEndpoint;
import com.hamdard.hua.rest.HealthCheckEndpoint;
import com.hamdard.hua.rest.ParameterResource;
import com.hamdard.hua.rest.PermissionEndpoint;

@ApplicationPath("/resources")
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        registerClasses(ParameterResource.class, AuthenticationEndpoint.class, ResourceNotFoundExceptionHandler.class, AuthenticationExceptionHandler.class, AuthenticationFilter.class, AuthorizationFilter.class, HealthCheckEndpoint.class, PermissionEndpoint.class);
    }
}