package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class GetApiTestRest {
	
	
	@Test
	public void Loginpost () {
		RestAssured.baseURI="http://35.157.221.79:5000";
		
		given().
		       param("Username", "Han").
		       param("Password", "Vermolen").
		       param("ReturnUrl", "/connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256").
		when().get("/account/login").then().assertThat().statusCode(200);
		
	}

}
