package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Get_Single_Employee_Record extends TestBase{

	
	@BeforeClass
	void getSingleEmployeeRecord() throws InterruptedException
	{
	   logger.info("-----------------strted tsetcase single employee record----------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	   httprequest= RestAssured.given();
	   response=httprequest.request(Method.GET,"/employee/" +empID);
	   Thread.sleep(3);
	}
	
	@Test
	void getResponseBody()
	{
		logger.info("-----------------checking for emp id in response body----------");
		String responsebody=response.getBody().asString();
		logger.info(responsebody);
		//Assert.assertEquals(responsebody.contains(empID), true);
		Assert.assertTrue(responsebody.contains(empID));
	}
	
	@Test
	void checkStatusCode()
	{
	logger.info("-----------------checking for status code----------");
	int statuscode=response.getStatusCode();
	Assert.assertEquals(statuscode, 200);
}
	
	@AfterClass
	void teardown()
	{
		logger.info("-----------------finished with geting the emp record----------");
	}
}
