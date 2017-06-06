package com.hamdard.hua.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/healthCheck")
public class HealthCheckEndpoint {
    
    @GET
    public Boolean healthCheck() {
        return true;
    }

}
