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

public class Put_Employee_Record extends TestBase{
	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge= RestUtils.empAge();
	
	@BeforeClass
	void updateRecord()
	{
		logger.info("-------------Updating the em[loyee recodr-----------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		httprequest = RestAssured.given();
		 
		JSONObject obj = new JSONObject();
		obj.put("name", empName);
		obj.put("salary", empSal);
		obj.put("age", empAge);
		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(obj.toJSONString());
		
		response = httprequest.request(Method.PUT ,"/update/"+empID );
		
	}

	@Test
	void checkresponsebody()
	{ logger.info("------------checking the response body ---------------");
		String responsebody =response.getBody().asString();
		logger.info(responsebody);
		Assert.assertTrue(responsebody.contains(empName));
		
	}
	
	@Test
	void statuscode()
	{
		logger.info("--------------getting the status code----------------");
		int statuscode =response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}

	@AfterClass
	void teardown()
	{
		logger.info("---------teardown begins---------------------");
	}
	
}
