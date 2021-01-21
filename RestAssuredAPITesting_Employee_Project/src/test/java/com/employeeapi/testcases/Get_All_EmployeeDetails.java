package com.employeeapi.testcases;

import org.apache.http.HttpRequest;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Get_All_EmployeeDetails extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("------------Started get all employees ------------");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "/employees");
		Thread.sleep(3);

	}

	@Test
	void checkResponseBody() {
		logger.info("-------checking the response body-------");
		String responsebody = response.getBody().asString();
		logger.info(responsebody);
		Assert.assertTrue(responsebody != null);
	}

	@Test
	void checkStatusCode() {
		logger.info("-------------checking the Status Code ------------");
		int statuscode = response.getStatusCode();
		logger.info(statuscode);
		Assert.assertEquals(statuscode, 200);

	}

	@Test
	void checkResponseTime() {
		logger.info("-------------checking the Response time------------");
		long responseTime = response.getTime();
		logger.info(responseTime);
		//System.out.println(responseTime);
		if (responseTime > 2000)

			logger.warn("responsetime is greater than 2000 ");
		Assert.assertTrue(responseTime < 3000);

	}

	@Test
	void checkStatusLine() {
		logger.info("-------------checking the status line------------");
		String statusline = response.getStatusLine();
		logger.info(statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}

	@Test
	void checkServerType() {
		logger.info("-------------checking the server type------------");
		String servertype = response.header("Server");
		logger.info(servertype);
		Assert.assertEquals(servertype, "nginx/1.16.0");
	}

	@Test
	void checkContentType() {
		logger.info("-------------checking the content type------------");
		String contenttype = response.header("Content-Type");
		logger.info(contenttype);
		Assert.assertEquals(contenttype, "application/json;charset=utf-8");
	}

	@Test
	void checkContentEncoding() {
		logger.info("-------------checking the content encoding------------");
		String contentencoding = response.header("Content-Encoding");
		logger.info(contentencoding);
		Assert.assertEquals(contentencoding, "gzip");
	}

	@Test
	void checkContentLength() {
		logger.info("-------------checking the content length------------");
		String contentlength = response.header("Content-Length");
		logger.info(contentlength);
		if (Integer.parseInt(contentlength) < 100)
			logger.warn("content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentlength) > 100);
	}

	@AfterClass
	void TearDown() {
		logger.info("Finished with get all emplyess deatils ");
	}
}
