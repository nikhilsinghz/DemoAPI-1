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
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

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
    	
     	ResponseBody body = response.getBody();
        
     	//System.out.println("Response Body is: " + body.asString());
    	//String successCode = response.jsonPath().get("SuccessCode");
    	//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }
    
    
    @Test 
    public void Loginpost()
    {		
    	RestAssured.baseURI ="http://35.157.221.79:5000";
    	RequestSpecification request = RestAssured.given();

    	JSONObject requestParams = new JSONObject();
    	requestParams.put("Username", "Han");
    	requestParams.put("Password", "Vermolen"); 
    	requestParams.put("ReturnUrl", "/connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256");

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
 	requestParams.put("button", "login");
 	requestParams.put("Username", "Hanz");
 	requestParams.put("Password", "Vermolen"); 

 	request.body(requestParams.toString());
 	Response response = request.get("/account/login");

 	int statusCode = response.getStatusCode();
 	System.out.println("statusCode is " + statusCode);
 	System.out.println(response.body().toString());
 	System.out.println("this is respnse" + response);
 	Assert.assertEquals(statusCode, 200);
 	
 	//ResponseBody body = response.getBody();
    //System.out.println("Response Body for Ivalid Creds: " + body.asString());
 	

 
	 	 
 }
  
 
 @Test
 public void accesstoken_for_invalid_client_id() {
	RestAssured.baseURI ="http://35.157.221.79:5000";
	RequestSpecification request = RestAssured.given();
	JSONObject requestParams = new JSONObject();

 	//requestParams.put("scope", "openid profile roles gateway_rights offline_access userId");
 	
 	requestParams.put("client_id", "zelfstroomapp");
 	requestParams.put("client_secret", "rhBNDktLtn4b6l9WTgmtzCvlUAPgFC");
 	requestParams.put("code", "a42885953b4113a35f433d5899422ec4fc4a9d1fbce29093ad923e35a6aac76b"); //df58b53b482229da76b8ae6dc18e52e4a89f7d82f7c69a68164b0e824dfc554b
 	requestParams.put("redirect_uri", "nl.zelfstroom.app://callback");
 	requestParams.put("grant_type", "authorization_code");
 	//requestParams.put("refresh_token", "a1f25a033945372b2e201c9417433e2c3fe31fb7fdfa442e353cd9c9eff6ffa6");
 	requestParams.put("code_verifier", "M25iVXpKU3puUjFaYWg3T1NDTDQtcW1ROUY5YXlwalNoc0hhakxifmZHag");

 	request.body(requestParams.toString());
 	Response response = request.get("/connect/token");
 	
 	
 	int statusCode = response.getStatusCode();
 	System.out.println(response.body().toString());
 	System.out.println("this is respnse" + response);
 	Assert.assertEquals(statusCode, 200);
 	
 	ResponseBody body = response.getBody();
 	System.out.println("Response Body is: " + body.asString());

 }
 
 
 @Test
 public void InvalidPassword()
 {
	RestAssured.baseURI ="http://35.157.221.79:5000";
 	RequestSpecification request = RestAssured.given();

 	JSONObject requestParams = new JSONObject();
 	requestParams.put("button", "login");
 	requestParams.put("Username", "Han");
 	requestParams.put("Password", "Vermolenz"); 

 	request.body(requestParams.toString());
 	Response response = request.get("/account/login");

 	int statusCode = response.getStatusCode();
 	System.out.println("statusCode is " + statusCode);
 	System.out.println(response.body().toString());
 	System.out.println("this is respnse" + response);
 	Assert.assertEquals(statusCode, 200);
 	
 	ResponseBody body = response.getBody();
    System.out.println("Response Body for Ivalid Creds: " + body.asString());
 }
 
 @Test
 public void IDP_User_info_call () {
	RestAssured.baseURI ="http://35.157.221.79:5000";
	RequestSpecification request = RestAssured.given();
	
	JSONObject requestParams = new JSONObject();
 	
 	//requestParams.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjExNjZmNzUzNjI5MWUxOWJlZWQzMDJiZDNmYTYzNDRhIiwidHlwIjoiSldUIn0.eyJuYmYiOjE1NDQxNjk0OTcsImV4cCI6MTU0NDE3MzA5NywiaXNzIjoiaHR0cDovLzM1LjE1Ny4yMjEuNzk6NTAwMCIsImF1ZCI6Imh0dHA6Ly8zNS4xNTcuMjIxLjc5OjUwMDAvcmVzb3VyY2VzIiwiY2xpZW50X2lkIjoiemVsZnN0cm9vbWFwcCIsInN1YiI6IjU1ZjJlMWU2LWY4YWEtNDBmNi1iYjI4LWI3ZWM0YzcyYjc2YyIsImF1dGhfdGltZSI6MTU0NDE2OTQ4OCwiaWRwIjoibG9jYWwiLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwicm9sZXMiLCJ1c2VySWQiLCJnYXRld2F5X3JpZ2h0cyIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJwd2QiXX0.T42ow7W5YyQNWjo5ZEmwG-T3HqJIICqYGXJznuVOG1IFtvKWvz1rpcPNGUhr9y0u5O0l5w8c2hrNNN-YbDw5V9QxMkEV_XCJoThBgtkuyNAej9nnrvJRrKzI9-VfOKg1R10gUQ0Ae2ZqTHT5aUP1kUFXZSqQPQv4aKuNtv357roFzr5G-BFzxwxubryZf0sAtM2WGMZ4w5bA4F1rv3z8NzIcbOsLZbRwCyk1uXr1GPiOK-ihxurOvx7r_YHB8EAWIWwX290VPMo7JZgTgEcbnjUpJyZRFLjaWxc9DyPkjqT-WD6u8FA7X4y2FEbchGdCRq9K3lusnfgX7oA8mhG5SA");
 	request.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjExNjZmNzUzNjI5MWUxOWJlZWQzMDJiZDNmYTYzNDRhIiwidHlwIjoiSldUIn0.eyJuYmYiOjE1NDQxNjk0OTcsImV4cCI6MTU0NDE3MzA5NywiaXNzIjoiaHR0cDovLzM1LjE1Ny4yMjEuNzk6NTAwMCIsImF1ZCI6Imh0dHA6Ly8zNS4xNTcuMjIxLjc5OjUwMDAvcmVzb3VyY2VzIiwiY2xpZW50X2lkIjoiemVsZnN0cm9vbWFwcCIsInN1YiI6IjU1ZjJlMWU2LWY4YWEtNDBmNi1iYjI4LWI3ZWM0YzcyYjc2YyIsImF1dGhfdGltZSI6MTU0NDE2OTQ4OCwiaWRwIjoibG9jYWwiLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwicm9sZXMiLCJ1c2VySWQiLCJnYXRld2F5X3JpZ2h0cyIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJwd2QiXX0.T42ow7W5YyQNWjo5ZEmwG-T3HqJIICqYGXJznuVOG1IFtvKWvz1rpcPNGUhr9y0u5O0l5w8c2hrNNN-YbDw5V9QxMkEV_XCJoThBgtkuyNAej9nnrvJRrKzI9-VfOKg1R10gUQ0Ae2ZqTHT5aUP1kUFXZSqQPQv4aKuNtv357roFzr5G-BFzxwxubryZf0sAtM2WGMZ4w5bA4F1rv3z8NzIcbOsLZbRwCyk1uXr1GPiOK-ihxurOvx7r_YHB8EAWIWwX290VPMo7JZgTgEcbnjUpJyZRFLjaWxc9DyPkjqT-WD6u8FA7X4y2FEbchGdCRq9K3lusnfgX7oA8mhG5SA");
 	//request.headers(requestParams.toString());
 	Response response = request.get("/connect/userinfo");
 	
 	
 	int statusCode = response.getStatusCode();
 	System.out.println(response.body().toString());
 	System.out.println("this is respnse" + response);
 	Assert.assertEquals(statusCode, 200);
 	
 	ResponseBody body = response.getBody();
 	System.out.println("Response Body is: " + body.asString());

 
 
 }
    
}