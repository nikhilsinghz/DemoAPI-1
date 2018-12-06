package com.qa.login;

import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class LoginAPI {
	
	@Test
	public void logiapitest() {
		RestAssured.baseURI="http://35.157.221.79:5000" ;
		
		Response res = given().header("Content-Type","application/x-www-form-urlencoded").
		body("{\"Username\":\"Han\", \"Password\": \"Vermolen\"}").
		
		when().
		post("/connect/token").then().statusCode(200).
		extract().response();
	
	
	}	

}
