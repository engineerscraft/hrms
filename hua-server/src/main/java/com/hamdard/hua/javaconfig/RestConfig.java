package com.hamdard.hua.javaconfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.hamdard.hua.filter.AuthenticationFilter;
import com.hamdard.hua.filter.AuthorizationFilter;
import com.hamdard.hua.rest.AccountResource;
import com.hamdard.hua.rest.AuthenticationEndpoint;
import com.hamdard.hua.rest.ChangePasswordEndpoint;
import com.hamdard.hua.rest.CollegeResource;
import com.hamdard.hua.rest.DepartmentResource;
import com.hamdard.hua.rest.DesignationResource;
import com.hamdard.hua.rest.EmployeeResource;
import com.hamdard.hua.rest.HealthCheckEndpoint;
import com.hamdard.hua.rest.PermissionEndpoint;
import com.hamdard.hua.rest.UserManagementEndpoint;

@ApplicationPath("/resources")
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        registerClasses(AccountResource.class, 
        		AuthenticationEndpoint.class,
        		CollegeResource.class,
        		AuthenticationFilter.class, 
        		AuthorizationFilter.class, 
        		HealthCheckEndpoint.class, 
        		PermissionEndpoint.class,
        		EmployeeResource.class,
        		DepartmentResource.class,
        		DesignationResource.class,
        		UserManagementEndpoint.class,
        		ChangePasswordEndpoint.class);
    }
}