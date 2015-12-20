package com.blog.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebClientHandler {
	
	private static final DefaultHttpClient httpClient = new DefaultHttpClient();
	private static final String BASE_URL = "https://raw.githubusercontent.com/rajan-startup/blog/master/src/main/webapp/";

	public static void main(String[] args){
		
//		System.out.println(getData("book/list"));
		System.out.println(getData("book/5@BOOK@-Data Structure"));
	}
	
	public static String getData(String url){

		url = url.replaceAll("@", "%40");
		url = url.replaceAll(" ", "%20");
		HttpGet getRequest = new HttpGet(BASE_URL+url);
		getRequest.addHeader("accept", "application/json");
		

		try{
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			StringBuilder output = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				output.append(line);
				output.append("\n");
			}
			return output.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
