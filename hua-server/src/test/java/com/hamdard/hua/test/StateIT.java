package com.hamdard.hua.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.model.Token;

public class StateIT {

    public static final String AUTH_URL = "http://localhost:8081/resources/v1/authentication";
    
    public static final String SERVICE_URL = "http://localhost:8081/resources/v1/state";
    
    @Test
    public void givenGetStatesOfACountry_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        final HttpPost authRequest = new HttpPost(AUTH_URL);
        authRequest.setHeader("Accept", "application/json");
        LoginDetails loginDetails = new LoginDetails("TEST", "TEST");
        ObjectMapper mapper = new ObjectMapper();
        String loginDetailsJson = mapper.writeValueAsString(loginDetails);
        StringEntity input = new StringEntity(loginDetailsJson);
        input.setContentType("application/json");
        authRequest.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(authRequest);
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
        Token accessToken = mapper.readValue(httpResponse.getEntity().getContent(), Token.class);
        
        final HttpGet searchEmployeeRequest = new HttpGet(SERVICE_URL+"?countryId=1");
        searchEmployeeRequest.setHeader("Accept", "application/json");
        searchEmployeeRequest.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse searchResponse = HttpClientBuilder.create().build().execute(searchEmployeeRequest);
        
        assertEquals(searchResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
    }
}
