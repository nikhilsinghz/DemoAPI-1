package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.RestClient;

public class GetAPITest extends Testbase {

	private static final Object RESPONSE_STATUS_CODE_302 = null;
	Testbase testbase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;

	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {

		testbase = new Testbase();
		serviceUrl = prop.getProperty("URL");

		apiUrl = prop.getProperty("serviceURL"); // http://35.157.221.79:5000 + Required Redirections

		url = serviceUrl + apiUrl; // Append both url , this gives me actual URL

	}

	//@Test(priority = 1)
	public void postLoginTest() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		closebaleHttpResponse = restClient.get("http:/35.157.221.79:5000/account/login");

		// a.Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");

		// b jason string
//		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
//			
//		JSONObject responseJson = new JSONObject(responseString);
//		System.out.println("Response JSON from API---->"+ responseJson);
//		

		// c. All headers
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>(); // storing all information (key and value) ,
																			// so we are creating one hashmap

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->" + allHeaders);

	}
	
	
	@Test(priority = 1)
	public void postTestWithoutHeader() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		closebaleHttpResponse = restClient.get("http://35.157.221.79:5000//connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256");

		// a.Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_302, "Status Code is not 302");

		// b jason string
//		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
//			
//		JSONObject responseJson = new JSONObject(responseString);
//		System.out.println("Response JSON from API---->"+ responseJson);
//		

		// c. All headers
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>(); // storing all information (key and value) ,
																			// so we are creating one hashmap

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->" + allHeaders);

	}

	@Test(priority = 2)
	public void postTestWithHeaders() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		headerMap.put("Username", "Han");
		headerMap.put("Password", "Vermolen");

		closebaleHttpResponse = restClient.get("http://35.157.221.79:5000//connect/authorize/callback?client_id=zelfstroomapp&scope=openid%20profile%20roles%20gateway_rights%20offline_access%20userId&response_type=code&redirect_uri=nl.zelfstroom.app%3A%2F%2Fcallback&response_mode=form_post&state=30906730-14d1-4083-ac3b-b763dd528acd&code_challenge=qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es&code_challenge_method=S256");
		//System.out.println(" This is test response" + closebaleHttpResponse);
		// a.Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");

//		// b jason string
//		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
//		//System.out.println(responseString);
//		JSONObject responseJson = new JSONObject(responseString);
//		System.out.println("Response JSON from API---->" + responseJson);

		// c. All headers
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>(); // storing all information (key and value) ,
																			// so we are creating one hashmap

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->" + allHeaders);

	}

}
