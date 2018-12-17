package com.qa.tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class sampleLoginCode {
	
	 RequestSpecification httpRequest = RestAssured.given().body(
             "button=login&"
                     +       "Password=Vermolen&"
                     +       "RememberLogin=false&"
                     +       "ReturnUrl=/connect/authorize/callback?client_id=gatewayapi&scope=openid%20profile%20roles%20gateway_rights%20userId&response_type=code&redirect_uri=http://localhost:4456/callback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&"
                     +       "Username=Han&"
                     +       "response_type=code").headers("Content-Type", "application/x-www-form-urlencoded");
     Response loginResponse = httpRequest.post("http://localhost:5000/account/login?client_id=gatewayapi&scope=openid profile roles userId gateway_rights&response_type=code&redirect_uri=http://localhost:4456/callback");

     Response authorizeResponse = RestAssured.given().cookies(loginResponse.cookies()).get("http://localhost:5000/connect/authorize/callback?client_id=gatewayapi&scope=openid profile roles gateway_rights userId&response_type=code&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&redirect_uri=http://localhost:4456/callback");

     System.out.println("authorizeResponse: " + authorizeResponse.getBody().xmlPath().prettyPrint());
     String code = authorizeResponse.getBody().xmlPath().getNodeChildren("html.body.form.input").get(0).getAttribute("value");
     System.out.println("code: " + code);
     Response tokenResponse = RestAssured.given().body(
             "scope=userId offline_access openid&"
                     + "client_id=gatewayapi&"
                     + "client_secret=secret&"
                     + "code=" + code + "&"
                     + "redirect_uri=http://localhost:4456/callback&"
                     + "grant_type=authorization_code"
     ).headers("Content-Type", "application/x-www-form-urlencoded").post("http://localhost:5000/connect/token");
     System.out.println("tokenResponse: " + tokenResponse.getBody().jsonPath().prettyPrint());
     bearerToken = tokenResponse.getBody().jsonPath().get("access_token");
     System.out.println("bearerToken: " + bearerToken);

     user_query = "{\"query\":\"{\\n  \\tcustomerInfo(userId: \\\"" + user_access
             + "\\\"){\\n  \\teMail\\n\\t}\\npremises(userId: \\\"" + user_access + "\\\", ";


}
