package com.hamdard.hua.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/v1/healthCheck")
public class HealthCheckEndpoint {
    
    @GET
    public Boolean healthCheck() {
        return true;
    }

}
