package com.hamdard.hua.javaconfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.hamdard.hua.filter.AuthenticationFilter;
import com.hamdard.hua.filter.AuthorizationFilter;
import com.hamdard.hua.rest.AuthenticationEndpoint;
import com.hamdard.hua.rest.ChangePasswordEndpoint;
import com.hamdard.hua.rest.CountryResource;
import com.hamdard.hua.rest.DepartmentResource;
import com.hamdard.hua.rest.DesignationResource;
import com.hamdard.hua.rest.DistrictResource;
import com.hamdard.hua.rest.DocumentTypeResource;
import com.hamdard.hua.rest.EmployeeResource;
import com.hamdard.hua.rest.HealthCheckEndpoint;
import com.hamdard.hua.rest.JobRoleResource;
import com.hamdard.hua.rest.OrganizationResource;
import com.hamdard.hua.rest.PermissionEndpoint;
import com.hamdard.hua.rest.StateResource;
import com.hamdard.hua.rest.UnitResource;
import com.hamdard.hua.rest.UserManagementEndpoint;

@ApplicationPath("/resources")
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        registerClasses(
                AuthenticationEndpoint.class, 
                AuthenticationFilter.class, 
                AuthorizationFilter.class, 
                HealthCheckEndpoint.class, 
                PermissionEndpoint.class, 
                DesignationResource.class, 
                UserManagementEndpoint.class,
                ChangePasswordEndpoint.class, 
                OrganizationResource.class, 
                UnitResource.class, 
                DepartmentResource.class, 
                CountryResource.class, 
                DistrictResource.class, 
                StateResource.class, 
                EmployeeResource.class, 
                DocumentTypeResource.class,
                JobRoleResource.class,
                MultiPartFeature.class);
    }
}