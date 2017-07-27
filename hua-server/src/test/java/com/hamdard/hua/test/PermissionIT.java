
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
import com.hamdard.hua.model.Permission;
import com.hamdard.hua.model.Token;
import com.hamdard.hua.model.Unit;

public class PermissionIT {
	
	
    public static final String AUTH_URL = "http://localhost:8081/resources/v1/authentication";
    
    public static final String SERVICE_URL = "http://localhost:8081/resources/v1/permission";
    
    @Test
    public void givenCalledValidResourcePath_whenUnitInfoIsRetrieved_thenResponseCodeSuccess() throws IOException{
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
     
        final HttpGet getListOfPermission = new HttpGet(SERVICE_URL+"?permissionLevel=anyData");
        getListOfPermission.setHeader("Accept", "application/json");
        getListOfPermission.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getListOfPermission);       
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
       
        final HttpGet getListOfPermission = new HttpGet(SERVICE_URL+"?permissionLevel=anyData");
        String jsonMimeType = "application/json";
        getListOfPermission.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getListOfPermission);
        String mimeType = ContentType.getOrDefault(getResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);  
    }
    
    @Test
    public void givenCalledValidResourcePath_whenUnitInfoIsRetrieved_thenRetrievedResourceIsCorrect() throws IOException{
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
       
        final HttpGet getListOfPermission = new HttpGet(SERVICE_URL+"??permissionLevel=anyData");
        getListOfPermission.setHeader("Accept", "application/json");
        getListOfPermission.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getListOfPermission); 
        
        Permission expectedPermission=new Permission();
        expectedPermission.setPermissionId(26);
        expectedPermission.setPermissionName("SearchEmployee");
        expectedPermission.setPermissionType("V");
        expectedPermission.setResourceName("/employeesearch");
        	
        List <Permission> retrievedPermissionList=mapper.readValue(getResponse.getEntity().getContent(), new TypeReference <List<Permission>>(){});
        boolean testDone=false;
        for(Permission actual:retrievedPermissionList){
        	if(actual.getPermissionId()==expectedPermission.getPermissionId()){
        		assertEquals(expectedPermission.toString(), actual.toString());
        		testDone=true;
        		break;
        	}	
        }
        if(!testDone)
        	assertTrue("No Permission found", false);
 
    }
}
