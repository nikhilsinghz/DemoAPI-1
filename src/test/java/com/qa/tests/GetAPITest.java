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
		
		apiUrl = prop.getProperty("serviceURL");
		
		//http://35.157.221.79:5000/account/login
		
	     url = serviceUrl + apiUrl ;  //Append both url , this gives me actual URL
		
	
	}
	
@Test (priority=1)
public void postTestWithoutHeader () throws ClientProtocolException, IOException {
	RestClient restClient = new RestClient();
	closebaleHttpResponse = restClient.get(url);
	
	//a.Status Code 
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();	
		System.out.println("Status Code ---->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status Code is not 200");
		
		
		//b jason string
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
			
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---->"+ responseJson);
		
		
		
		
		
		//c. All headers 
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String, String>();  //storing all information (key and value) , so we are creating one hashmap
		
		for(Header header : headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->" +allHeaders);
	
	
}

@Test (priority=2)
public void postTestWithHeaders () throws ClientProtocolException, IOException {
	RestClient restClient = new RestClient();
	
	
	HashMap<String, String> headerMap =new HashMap <String, String>();
	
	
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
	headerMap.put("Username", "Han");
	headerMap.put("Password", "Vermolen");

	
	
	
	
	closebaleHttpResponse = restClient.get(url);
	
	//a.Status Code 
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();	
		System.out.println("Status Code ---->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status Code is not 200");
		
		
		//b jason string
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
			
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---->"+ responseJson);
		
		
		
		
		
		//c. All headers 
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String, String>();  //storing all information (key and value) , so we are creating one hashmap
		
		for(Header header : headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->" +allHeaders);
	
	
}















}
