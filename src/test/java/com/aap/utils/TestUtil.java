package com.aap.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import com.aap.stepDefinition.OrderService;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtil {
	
	public static  Properties config = new Properties();
	static Logger logger = Logger.getLogger(TestUtil.class);
	static StringBuilder request_sb = new StringBuilder();
	
	static String requestTimeStamp;
	static String responseTimeStamp;
	static StringBuilder response_sb = new StringBuilder();
    static String env;

	
	static {
		try {
			env = System.getProperty("Env");
			System.out.println("********CONFIG FILE USING IS *********"+"config_"+env+".properties");
			InputStream str = new FileInputStream("config_"+env+".properties");
			config.load(str);			
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	 }
	
	public static RequestBuilder buildHTTPGetRequest(String url,String contentType)
	{
		RequestBuilder requestBuilder = RequestBuilder.get().setUri(url)
				.setHeader(HttpHeaders.CONTENT_TYPE, contentType)
				.setHeader(	HttpHeaders.HOST,getHostName(url));
		return requestBuilder;
	}
	
	public static String getHostName(String uri)
	{
		String hostname = null;
		if(uri.startsWith("https://") || uri.startsWith("http://"))
			hostname =  uri.split("//")[1].split("/")[0];
		else
			hostname = uri.split("/")[0];
		return hostname;

	}
	
	public static HttpResponse getHttpResponse(RequestBuilder requestBuilder) {

		HttpResponse response = null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpUriRequest request = requestBuilder.build();
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			response = null;
			e.printStackTrace();
		} catch (IOException e) {
			response = null;
			e.printStackTrace();
		}
		return response;
	}
	
	public static String readResponseString(HttpResponse response)
	{
		StringBuffer result = new StringBuffer();
		String line = null;
		String responseString = null;
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			responseString = result.toString();
		}  catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("******* END OF RESPONSE STRING **********");
		return responseString;
	}
	
	public static void printResponseToLog(HttpResponse response, String data) {
		logger.info("HTTP Response Code :"+response.getStatusLine().getStatusCode());
		response_sb.append("<TABLE BORDER=\"2\" cellpadding=\"5\" cellspacing=\"0\">");
		response_sb.append("<TR><TD width=\"350\">"+"HTTP Response Code"+"</TD>");
		response_sb.append("<TD>"+response.getStatusLine().getStatusCode()+"</TD><br></TR>");
//		response_sb.append("Status : " + response.getStatusLine().getStatusCode() + "<br>");
		
		
		List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());        
		
	    for (Header header : httpHeaders) {
//	    	response_sb.append(header.getName()+"\t\t\t\t\t: " + header.getValue() + "<br>");
	    	response_sb.append("<TR><TD width=\"350\">"+header.getName()+"</TD>");
	    	response_sb.append("<TD>"+header.getValue()+"</TD><br></TR>");
	    }
	    response_sb.append("<TR><TD width=\"350\">"+"Response body"+"</TD>");
    	response_sb.append("<TD>"+data+"</TD><br></TR>");
//	    response_sb.append("Body-------------<br>");
//		response_sb.append("   " + data + "<br>");
		response_sb.append("</TABLE>");
		 
	    //logger.info(response_sb.toString());
		
	}	
	
	public static File showRequestHTML(String title,RequestBuilder reqBuilderObj, String requestJson) {
		requestTimeStamp = getTime();
		HttpUriRequest request = reqBuilderObj.build();
		request_sb.append("Date: "+requestTimeStamp);
		
		request_sb.append("<TABLE BORDER=\"2\" cellpadding=\"5\" cellspacing=\"0\">");
        request_sb.append("<TR>");
        request_sb.append("<TD width=\"350\">"+"URI"+"</TD>");
        request_sb.append("<TD>"+request.getURI()+"</TD><br>");
        request_sb.append("</TR>");
        
		List<Header> httpHeaders = Arrays.asList(request.getAllHeaders());        
	    for (Header header : httpHeaders) {
	        //System.out.println("********** Headers in request.. name,value:"+header.getName() + "," + header.getValue());
	        request_sb.append("<TR><TD width=\"350\">"+header.getName()+"</TD>");
	        request_sb.append("<TD>"+header.getValue()+"</TD><br></TR>");
	    }
	    request_sb.append("<TR><TD width=\"350\">"+"Request Body"+"</TD>");
        request_sb.append("<TD>"+requestJson+"</TD><br></TR>");
        
	    request_sb.append("</TABLE>");
		URL defaultImage = OrderService.class.getResource("/request.html");
        File imageFile = null;
		try {
			imageFile = new File(defaultImage.toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		String htmlString = null;
		try {
			htmlString = FileUtils.readFileToString(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		htmlString = htmlString.replace("$title", title);
		//logger.info("******** request_sb.toString() *********");
		//logger.info(request_sb.toString());
		htmlString = htmlString.replace("$request", request_sb.toString());
		File newHtmlFile = new File("template1.html");
		try {
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
			request_sb = new StringBuilder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newHtmlFile;
	}
	
	public static File showResponseHTML(String title){
		responseTimeStamp = getTime();
		URL defaultImage = OrderService.class.getResource("/response.html");
        File imageFile = null;
		try {
			imageFile = new File(defaultImage.toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		String htmlString = null;
		try {
			htmlString = FileUtils.readFileToString(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String body = "Allure report";
		htmlString = htmlString.replace("$date", responseTimeStamp);
		htmlString = htmlString.replace("$title", title);
		
		htmlString = htmlString.replace("$response", response_sb.toString());
		File newHtmlFile = new File("template1.html");
		try {
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
			response_sb = new StringBuilder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newHtmlFile;
	}
	
	public static File showResponseHTML_Defect(String title, String scenarioName){
		responseTimeStamp = getTime();
		URL defaultImage = OrderService.class.getResource("/response_defect.html");
        File imageFile = null;
		try {
			imageFile = new File(defaultImage.toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		String htmlString = null;
		try {
			htmlString = FileUtils.readFileToString(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String body = "Allure report";
		htmlString = htmlString.replace("$date", responseTimeStamp);
		//htmlString = htmlString.replace("$scenarioName", scenarioName);	
		htmlString = htmlString.replace("$title", title);
		
		htmlString = htmlString.replace("$response", response_sb.toString());
		File newHtmlFile = new File("template1.html");
		try {
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
			response_sb = new StringBuilder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newHtmlFile;
	}
	
	private static String getTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		return dateFormat.format(calendar.getTime());
	}
	
	public static byte[] toByteArray(File file) throws IOException {
	    return Files.readAllBytes(Paths.get(file.getPath()));
	}
	
	public static RequestBuilder buildHTTPPostRequest(String url)
	{		      
		RequestBuilder requestBuilder = RequestBuilder.post().setUri(url)
				.setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
				.setHeader(	HttpHeaders.HOST,getHostName(url));				
		return requestBuilder;	
	}
	
	public static RequestBuilder buildHTTPPutRequest(String url)
	{		      
		RequestBuilder requestBuilder = RequestBuilder.put().setUri(url)
				.setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
				.setHeader(	HttpHeaders.HOST,getHostName(url));				
		return requestBuilder;	
	}
	
	public static RequestSpecification buildHttpHeader_RestAssured()
	{		      
		RequestSpecification request = RestAssured.given();
		request.header("ContentType","application/json");							
		return request;	
	}
	
	public static HttpResponse getHttpResponseForStringReqEntity(RequestBuilder requestBuilder, String inputString) {
		HttpResponse response = null;
		try {
			long millis = System.currentTimeMillis() % 1000;
			requestTimeStamp = getTime();
			HttpClient client = HttpClientBuilder.create().build();
			StringEntity requestEntity = new StringEntity(inputString);
			HttpUriRequest request = requestBuilder.setEntity(requestEntity).build();
			response = client.execute(request);
			responseTimeStamp = getTime();
			long millis1 = System.currentTimeMillis() % 1000;
			long diff = millis1-millis;
			System.out.print("execution time is " +diff +"\n");
		} catch (ClientProtocolException e) {
			response = null;
			e.printStackTrace();
		} catch (IOException e) {
			response = null;
			e.printStackTrace();
		}
		return response;
	}
	
	@SuppressWarnings("deprecation")
	public static String readFileAsString(String fileName) throws Exception {
		ClassLoader classLoader = OrderService.class.getClassLoader();
		return IOUtils.toString(classLoader.getResourceAsStream(fileName));
		
	}
	
	
}
