package com.hamdard.hua.javaconfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.hamdard.hua.filter.AuthenticationFilter;
import com.hamdard.hua.filter.AuthorizationFilter;
import com.hamdard.hua.rest.AccountResource;
import com.hamdard.hua.rest.AuthenticationEndpoint;
import com.hamdard.hua.rest.EmployeeResource;
import com.hamdard.hua.rest.HealthCheckEndpoint;
import com.hamdard.hua.rest.PermissionEndpoint;

@ApplicationPath("/resources")
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        registerClasses(AccountResource.class, 
        		AuthenticationEndpoint.class, 
        		AuthenticationFilter.class, 
        		AuthorizationFilter.class, 
        		HealthCheckEndpoint.class, 
        		PermissionEndpoint.class,
        		EmployeeResource.class);
    }
}