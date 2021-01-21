package com.employeeapi.testcases;

import org.junit.AfterClass;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class Delete_Employee_Record extends TestBase{
	
	
	@BeforeClass
	void deleteEntry()
	{
		logger.info("-------------------------deleting a  record ---------------------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httprequest= RestAssured.given();
		response= httprequest.request(Method.GET,"/employees");
		
		//get jasonpath  obj
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//capture id
		String empid= jsonPathEvaluator.get("[0].id");
		
		response = httprequest.request(Method.DELETE ,"/delete/" +empid);
				
				
	}

	@Test
	void checkresponsebody()
	{ logger.info("------------checking the response body ---------------");
		String responsebody =response.getBody().asString();
		logger.info(responsebody);
		Assert.assertTrue(responsebody.contains("Successfully! Record has been deleted"));
		
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
