
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
import com.hamdard.hua.model.Grade;
import com.hamdard.hua.model.JobRole;
import com.hamdard.hua.model.LoginDetails;
import com.hamdard.hua.model.Token;
import com.hamdard.hua.model.Unit;

/*ITs for below API 
 * /{id}/salary
 * /{id}/optbenefit
 * 
 * are yet to be developed as there is no data present in table:
 * salary_component_master
 * salary_master
 */
public class JobRoleIT {
	
	
    public static final String AUTH_URL = "http://localhost:8081/resources/v1/authentication";
    
    public static final String SERVICE_URL = "http://localhost:8081/resources/v1/jobrole";
    
    /* *
     * Test URI: resources/v1/jobrole?orgId=sampleOrgId
     * Fetch list of JobRoles for a given orgId
     * @result expected status code 200
     */
    @Test
    public void givenValidOrganizationId_whenUnitInfoIsRetrieved_thenResponseCodeSuccess() throws IOException{
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
        final HttpGet getJobRoleListOfAnOrg = new HttpGet(SERVICE_URL+"?orgId=1");
        getJobRoleListOfAnOrg.setHeader("Accept", "application/json");
        getJobRoleListOfAnOrg.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getJobRoleListOfAnOrg);      
        assertEquals(getResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);    
    }
  
    /* *
     * Test URI: resources/v1/jobrole?orgId=wrongOrgId
     * List of JobRoles when wrong OrgId is given
     * @result expected status code 404
     */
    @Test
    public void givenInvalidOrgId_whenJobRoleInfoIsRetrieved_then404IsReceived() throws IOException{
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
        final HttpGet getJobRoleListOfAnOrg = new HttpGet(SERVICE_URL+"?orgId=99999");
        getJobRoleListOfAnOrg.setHeader("Accept", "application/json");
        getJobRoleListOfAnOrg.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getJobRoleListOfAnOrg);
        assertEquals(getResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
 
        
    }
    
    /* *
     * Test URI: resources/v1/jobrole?orgId=sampleOrgId
     * check default response header as JSON
     * @result expected response header as JSON
     */
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
       
        final HttpGet getJobRoleListOfAnOrg = new HttpGet(SERVICE_URL+"?orgId=1");
        String jsonMimeType = "application/json";
        getJobRoleListOfAnOrg.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getJobRoleListOfAnOrg);
        String mimeType = ContentType.getOrDefault(getResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
 
        
    }
    
    /* *
     * Test URI: resources/v1/jobrole?orgId=sampleOrgId
     * List of JobRoles when correct OrgId is given
     * @result expected correct <JobRole>
     */    
    @Test
    public void givenValidOrganizationId_whenJobRoleListIsRetrieved_thenRetrievedResourceIsCorrect() throws IOException{
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
       
        final HttpGet getUnitOfAnOrganization = new HttpGet(SERVICE_URL+"?orgId=1");
        getUnitOfAnOrganization.setHeader("Accept", "application/json");
        getUnitOfAnOrganization.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse getResponse = HttpClientBuilder.create().build().execute(getUnitOfAnOrganization);      
        
        Grade grade=new Grade();
        grade.setGradeId(1);
        grade.setGradeName("T1");
        JobRole expectedJobRole=new JobRole();
        expectedJobRole.setJobRoleId(1);
        expectedJobRole.setOrgId(1);
        expectedJobRole.setGrade(grade);
        expectedJobRole.setNoticeperiod(3);
        expectedJobRole.setProbationNoticePeriod(1);
        
        List <JobRole> retrievedJobRoleList=mapper.readValue(getResponse.getEntity().getContent(), new TypeReference <List<JobRole>>(){});
        boolean testDone=false;
        for(JobRole actual:retrievedJobRoleList){
        	if(actual.getJobRoleId()==expectedJobRole.getJobRoleId()){
        		assertEquals(expectedJobRole.toString(), actual.toString());
        		testDone=true;
        		break;
        	}	
        }
        if(!testDone)
        	assertTrue("No JobRole found", false);
 
    }
}
