package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPITest extends Testbase {
	Testbase testbase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;
	
	
	//@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {

		testbase = new Testbase();
		serviceUrl = prop.getProperty("URL");

		apiUrl = prop.getProperty("serviceURL"); // http://35.157.221.79:5000 + Required Redirections

		url = serviceUrl + apiUrl; // Append both url , this gives me actual URL

	}

   //@Test
    public void postAPITest() {
    	restClient = new RestClient();
    	
    	HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		
		
    }
    
    
    @Test
    public void Loginget()
    {		
    	RestAssured.baseURI ="http://35.157.221.79:5000";
    	RequestSpecification request = RestAssured.given();

    	Response response = request.get("/connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256");

    	int statusCode = response.getStatusCode();
    	System.out.println(response.body());
    	Assert.assertEquals(statusCode, 200);
    	//String successCode = response.jsonPath().get("SuccessCode");
    	//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }
    
    
    @Test 
    public void Loginpost()
    {		
    	RestAssured.baseURI ="http://35.157.221.79:5000";
    	RequestSpecification request = RestAssured.given();

    	JSONObject requestParams = new JSONObject();
    	requestParams.put("ReturnUrl", "/connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256");
    	requestParams.put("Username", "Han");
    	requestParams.put("Password", "Vermolen"); 

    	request.body(requestParams.toString());
    	Response response = request.post("/account/login");

    	int statusCode = response.getStatusCode();
    	System.out.println(response.body());
    	Assert.assertEquals(statusCode, 302);
    	//String successCode = response.jsonPath().get("SuccessCode");
    	//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
}
    
 @Test
 public void Incorrectusername()
 {
	RestAssured.baseURI ="http://35.157.221.79:5000";
 	RequestSpecification request = RestAssured.given();

 	JSONObject requestParams = new JSONObject();
 	requestParams.put("ReturnUrl", "/connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256");
 	requestParams.put("Username", "Han");
 	requestParams.put("Password", "Vermolen"); 

 	request.body(requestParams.toString());
 	Response response = request.post("/account/login");

 	int statusCode = response.getStatusCode();
 	System.out.println("RESPONSECODE" + statusCode);
 	System.out.println(response.body());
 	Assert.assertEquals(statusCode, 200);
 	//String successCode = response.jsonPath().get("SuccessCode");
 	//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS"); 
	 
	 	 
 }
  
 
 
 
 
 
 
 
 
    
}