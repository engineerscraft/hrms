
package com.hamdard.hua.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.model.Organization;
import com.hamdard.hua.model.Token;

public class OrganizationIT {

    public static final String AUTH_URL = "http://localhost:8081/resources/v1/authentication";
    
    public static final String SERVICE_URL = "http://localhost:8081/resources/v1/organization";
    
    @Test
    public void givenCalledTheResourceCorrectly_whenUnitInfoIsRetrieved_thenResponseCodeSuccess() throws IOException{
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
       
        final HttpGet getListOfOrganization = new HttpGet(SERVICE_URL);
        getListOfOrganization.setHeader("Accept", "application/json");
        getListOfOrganization.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getListOfOrganization);
        assertEquals(getResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);   
    }
    
    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws IOException{
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
        
        final HttpGet getListOfOrganization = new HttpGet(SERVICE_URL);
        String jsonMimeType = "application/json";
        getListOfOrganization.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getListOfOrganization);
        String mimeType = ContentType.getOrDefault(getResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);    
    }
    
    @Test
    public void givenValidOrganizationId_whenUnitInfoIsRetrieved_thenRetrievedResourceIsCorrect() throws IOException{
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
       
        final HttpGet getListOfOrganization = new HttpGet(SERVICE_URL);
        getListOfOrganization.setHeader("Accept", "application/json");
        getListOfOrganization.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getListOfOrganization);
        
        Organization expectedOrganization=new Organization();
        expectedOrganization.setAddress("Bangladesh");
        expectedOrganization.setDescription("University");
        expectedOrganization.setOrgId(1);
        expectedOrganization.setOrgName("Hamdard University");
        expectedOrganization.setOrgTypeId(1);
        expectedOrganization.setOrgTypeName("University");
        List <Organization> retrievedOrgList=mapper.readValue(getResponse.getEntity().getContent(), new TypeReference <List<Organization>>(){});
        boolean testDone=false;
        for(Organization actual:retrievedOrgList){
        	if(actual.getOrgId()==expectedOrganization.getOrgId()){
        		assertEquals(expectedOrganization.toString(), actual.toString());
        		testDone=true;
        		break;
        	}	
        }
        if(!testDone)
        	assertTrue("No Unit found", false);
 
    }
}
