package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


//Git add 2.0.0.2
public class RestClient {
	
	//1. Post Method (will write such code so it will call specific mentioned url amd we will get the response in the jason object) 
	public void get (String url) throws ClientProtocolException, IOException {
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpPost httpost = new HttpPost(url); //http post request 
	CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpost);//hit the POST URL
	
	//a.Status Code 
	int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();	
	System.out.println("Status Codde ---->"+ statusCode);
	
	
	//b jason string
	String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
	JSONObject responseJson = new JSONObject(responseString);
	System.out.println("Response JSON from API---->"+ responseJson);
	
	//c. All headers 
	Header[] headerArray = closeableHttpResponse.getAllHeaders();
	HashMap<String,String> allHeaders = new HashMap<String, String>();  //storing all information (key and value) , so we are creating one hashmap
	
	for(Header header : headerArray){
		allHeaders.put(header.getName(), header.getValue());
	}
	System.out.println("Headers Array--->" +allHeaders);
	}

	
}
