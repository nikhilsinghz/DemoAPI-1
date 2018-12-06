package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;

//Git add 2.0.0.2
public class RestClient {

	// 1. Get Method without headers (will write such code so it will call specific "mentioned url amd we will get the response in the jason object"

	
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closebaleHttpResponse  = null;
		try {
			closebaleHttpResponse = httpClient.execute(httpget);// http the Get URL			
		} catch(JSONException e) {
			System.out.println(e.getMessage());
		}

		return closebaleHttpResponse;

	}
	// 1. Get Method with header (will write such code so it will call specific-mentioned url amd we will get the response in the jason object), Hashmap = Means it is an object which stores a value on the basis of "Key" and "values". ,,Overloading with two parameter (String url, headerMap) , (this is "OVERLOADED" method example).
	
	
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httget = new HttpGet(url); // http get request

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httget.addHeader(entry.getKey(), entry.getValue());
		
		}

		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httget);// hit the Get URL
		return closebaleHttpResponse;

	}

	//3 . POST Method:
	public CloseableHttpResponse post(String url , String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); //http post
		httppost.setEntity(new StringEntity(entityString));//For payload
		
		//For Headers 
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
	}
	CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);// hit the Post URL
	return closebaleHttpResponse;
	
	}
	
}

//	//a.Status Code 
//	int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();	
//	System.out.println("Status Codde ---->"+ statusCode);
//	
//	
//	//b jason string
//	String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
//		
//	JSONObject responseJson = new JSONObject(responseString);
//	System.out.println("Response JSON from API---->"+ responseJson);
//	
//	//c. All headers 
//	Header[] headerArray = closeableHttpResponse.getAllHeaders();
//	HashMap<String,String> allHeaders = new HashMap<String, String>();  //storing all information (key and value) , so we are creating one hashmap
//	
//	for(Header header : headerArray){
//		allHeaders.put(header.getName(), header.getValue());
//	}
//	System.out.println("Headers Array--->" +allHeaders);
