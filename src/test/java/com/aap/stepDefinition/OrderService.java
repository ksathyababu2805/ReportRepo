package com.aap.stepDefinition;

import java.io.File;
import java.lang.management.ManagementFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import com.aap.utils.CommonUtils;
import com.aap.utils.TestUtil;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class OrderService {	
	static Logger logger = Logger.getLogger(OrderService.class);	
	RequestBuilder requestBuilder;
	String resultdata;		
	String orderServiceURL = TestUtil.config.getProperty("orderServiceURL");

	RequestSpecification request_ra;
	HttpResponse response_ra;
	
	String orderPayload;

	private Scenario scenario;	
	CommonUtils random = new CommonUtils(6,false);
	
	
	@Severity(SeverityLevel.NORMAL)	
	@Given("^I build the request for the order service with \"([^\"]*)\" as \"([^\"]*)\"$")
	public void i_build_the_request_for_the_order_service_with_as(String arg1, String arg2) throws Throwable {
		orderPayload = TestUtil.readFileAsString("Requests/OrderService/addItem.json");
		JSONObject orderPayloadJSON = new JSONObject(orderPayload);
		String[] fieldSet = arg1.split(",",-1);
		String[] fieldValueSet = arg2.split(",",-1);
		for (int i=0;i<fieldSet.length;i++) {
			orderPayloadJSON.put(fieldSet[i],fieldValueSet[i]);
		}
		
		RestAssured.baseURI = orderServiceURL;
		request_ra = TestUtil.buildHttpHeader_RestAssured();
		request_ra.body(orderPayloadJSON.toString());
		logger.info("Order API Request Body is --> " + orderPayloadJSON.toString());
		
		//This code was for building the report
		requestBuilder = TestUtil.buildHTTPPostRequest(orderServiceURL);
		StringEntity entity = new StringEntity(orderPayloadJSON.toString());
		requestBuilder.setEntity(entity);
		saveHtmlAttach("Request", requestBuilder, orderPayloadJSON.toString());
	}	
	
	@Severity(SeverityLevel.BLOCKER)
	@Given("^trigger the service$")
	public void invoketheService() throws Throwable {		
		response_ra = TestUtil.getHttpResponse(requestBuilder);
		logger.info("Request sent!!");
		resultdata = TestUtil.readResponseString(response_ra);
		TestUtil.printResponseToLog(response_ra, resultdata);
		logger.info("Response  is "+resultdata);
		logger.info("Response Code : "
                + response_ra.getStatusLine().getStatusCode());
	}

	@Then("^I validate the create order service response for StatusCode (\\d+)$")
	public void i_validate_the_order_service_response_for_StatusCode(int arg1) throws Throwable {
		String scenarioName = null;
		if (response_ra.getStatusLine().getStatusCode() == arg1) {
			saveHtmlAttach("Response", null, null);
		}else {
			saveHtmlAttachDefectManagement("Response", null, null,scenarioName);
		}
		Assert.assertTrue("The service is not giving the expected response code. Response code expected "+arg1+", But actual "+response_ra.getStatusLine().getStatusCode(), 
				response_ra.getStatusLine().getStatusCode()==arg1);
	}
			
	@Attachment(value = "{0}", type = "text/html")
	public static byte[] saveHtmlAttach(String attachName, RequestBuilder requestBuilderObj, String requestJson) {
	    try {
	    	if("Request".equalsIgnoreCase(attachName)){
		        File imageFile = TestUtil.showRequestHTML(attachName, requestBuilderObj, requestJson);
		        return TestUtil.toByteArray(imageFile);
	    	}else if("Response".equalsIgnoreCase(attachName)){
		        File imageFile = TestUtil.showResponseHTML(attachName);
		        return TestUtil.toByteArray(imageFile);
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return new byte[0];
	}
	 	
	@Attachment(value = "{0}", type = "text/html")
	public static byte[] saveHtmlAttachDefectManagement(String attachName, RequestBuilder requestBuilderObj, String requestJson, String scenarioName) {
	    try {
	    	if("Response".equalsIgnoreCase(attachName)){
		        File imageFile = TestUtil.showResponseHTML_Defect(attachName,scenarioName );
		        return TestUtil.toByteArray(imageFile);
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return new byte[0];
	}	
}
