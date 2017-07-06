package com.hamdard.hua.test;

public class JerseyApiIT {

    public static final String AUTH_URL = "http://localhost:9090/resources/authentication";
    
    public static final String SERVICE_URL = "http://localhost:9090/resources/employees";

/*    @Test
    public void givenSearchAllEmployees_whenCorrectRequest_thenResponseCodeSuccess() throws IOException {
        final HttpPost authRequest = new HttpPost(AUTH_URL);
        authRequest.setHeader("Accept", "application/json");
        LoginDetails loginDetails = new LoginDetails("TEST02", "TEST02");
        ObjectMapper mapper = new ObjectMapper();
        String loginDetailsJson = mapper.writeValueAsString(loginDetails);
        StringEntity input = new StringEntity(loginDetailsJson);
        input.setContentType("application/json");
        authRequest.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(authRequest);
        
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
        Token accessToken = mapper.readValue(httpResponse.getEntity().getContent(), Token.class);
        
        final HttpGet searchEmployeeRequest = new HttpGet(SERVICE_URL+"?firstName=Jane&lastName=Doe");
        searchEmployeeRequest.setHeader("Accept", "application/json");
        searchEmployeeRequest.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse searchResponse = HttpClientBuilder.create().build().execute(searchEmployeeRequest);
        
        assertEquals(searchResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
        List<Employee> empList = mapper.readValue(searchResponse.getEntity().getContent(), new TypeReference<List<Employee>>(){});
        
        assertEquals(empList.get(0).getFirstName(), "Jane");
        
    }

    @Test
    public void givenGetEmployee_whenEmployeeExists_thenResponseCodeSuccess() throws IOException {
        final HttpPost authRequest = new HttpPost(AUTH_URL);
        authRequest.setHeader("Accept", "application/json");
        LoginDetails loginDetails = new LoginDetails("TEST02", "TEST02");
        ObjectMapper mapper = new ObjectMapper();
        String loginDetailsJson = mapper.writeValueAsString(loginDetails);
        StringEntity input = new StringEntity(loginDetailsJson);
        input.setContentType("application/json");
        authRequest.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(authRequest);
        
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
        Token accessToken = mapper.readValue(httpResponse.getEntity().getContent(), Token.class);
        
        final HttpGet searchEmployeeRequest = new HttpGet(SERVICE_URL+"/1");
        searchEmployeeRequest.setHeader("Accept", "application/json");
        searchEmployeeRequest.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse searchResponse = HttpClientBuilder.create().build().execute(searchEmployeeRequest);
        
        assertEquals(searchResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
        Employee emp = mapper.readValue(searchResponse.getEntity().getContent(), new TypeReference<Employee>(){});
        
        assertEquals(emp.getFirstName(), "Jane");
    }
    
    @Test
    public void givenGetEmployee_whenEmployeeDoesNotExist_thenResponseCodeNotFound() throws IOException {
        final HttpPost authRequest = new HttpPost(AUTH_URL);
        authRequest.setHeader("Accept", "application/json");
        LoginDetails loginDetails = new LoginDetails("TEST02", "TEST02");
        ObjectMapper mapper = new ObjectMapper();
        String loginDetailsJson = mapper.writeValueAsString(loginDetails);
        StringEntity input = new StringEntity(loginDetailsJson);
        input.setContentType("application/json");
        authRequest.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(authRequest);
        
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        
        Token accessToken = mapper.readValue(httpResponse.getEntity().getContent(), Token.class);
        
        final HttpGet searchEmployeeRequest = new HttpGet(SERVICE_URL+"/1000");
        searchEmployeeRequest.setHeader("Accept", "application/json");
        searchEmployeeRequest.setHeader("Authorization", "Bearer " + accessToken.getAccessToken());
        final HttpResponse searchResponse = HttpClientBuilder.create().build().execute(searchEmployeeRequest);
        
        assertEquals(searchResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
        
    }*/
    
/*    @Test
    public void givenGetEmployee_whenJsonRequested_thenCorrectDataRetrieved() throws IOException {
        final HttpUriRequest request = new HttpGet(SERVICE_URL + "/1");
    
        request.setHeader("Accept", "application/json");
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = mapper.readValue(httpResponse.getEntity().getContent(), Employee.class);
    
        assertEquals(emp.getFirstName(), "Jane");
    }
    
    @Test
    public void givenAddEmployee_whenJsonRequestSent_thenResponseCodeCreated() throws IOException {
        final HttpPost request = new HttpPost(SERVICE_URL);
    
        Employee emp = new Employee(5, "Johny", "Doe", 23);
        ObjectMapper mapper = new ObjectMapper();
        String empJson = mapper.writeValueAsString(emp);
        StringEntity input = new StringEntity(empJson);
        input.setContentType("application/json");
        request.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_CREATED);
    }
    
    @Test
    public void givenAddEmployee_whenRequestForExistingObjectSent_thenResponseCodeConflict() throws IOException {
        final HttpPost request = new HttpPost(SERVICE_URL);
    
        Employee emp = new Employee(1, "Johny", "Doe", 25);
        ObjectMapper mapper = new ObjectMapper();
        String empJson = mapper.writeValueAsString(emp);
        StringEntity input = new StringEntity(empJson);
        input.setContentType("application/json");
        request.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_CONFLICT);
    }*/

}