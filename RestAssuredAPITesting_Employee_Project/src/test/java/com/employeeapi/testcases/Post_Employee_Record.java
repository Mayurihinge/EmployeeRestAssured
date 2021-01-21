package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utility.RestUtils;


import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Post_Employee_Record extends TestBase {
	
	
	String empName = RestUtils.empName();
	String empSal =  RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	
	void create() throws InterruptedException
	{
		logger.info("-------------------------Creating new record ---------------------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httprequest= RestAssured.given();
		
		JSONObject obj = new  JSONObject();
		obj.put("name" ,empName);
		obj.put("salary", empSal);
		obj.put("age", empAge);
		
		String contenttype = response.header("Content-Type");
		System.out.println("CT is ------------->" +contenttype);
		httprequest.header("Content-Type ", "application/json");
		httprequest.body(obj.toJSONString());
		response = httprequest.request(Method.POST ,"/create");
		Thread.sleep(4000);
	
	}
	
	@Test
	void responsebody()
	{
		logger.info("------------------------geting the response body ----------------------");
		String responsebody=response.getBody().asString();
		System.out.println("Response body ----->" +responsebody);
		Assert.assertTrue(responsebody.contains(empName));
		Assert.assertTrue(responsebody.contains(empAge));
		
	}
	
	@Test
	void getStatusCode()
	{
		int statuscode =response.getStatusCode();
		logger.info(statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	
	@AfterClass
void teardown()
{
		logger.info("------------ending the test cases -------------------------");
}
}
