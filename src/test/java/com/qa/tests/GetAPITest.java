 package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
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
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
		
		testbase = new Testbase();
		serviceUrl = prop.getProperty("URL");
		
		apiUrl = prop.getProperty("serviceURL");
		
		
	     url = serviceUrl + apiUrl ;
		
	
	}
	
@Test
public void postTest () throws ClientProtocolException, IOException {
	RestClient restClient = new RestClient();
	restClient.get(url);	
	
	
}
}
