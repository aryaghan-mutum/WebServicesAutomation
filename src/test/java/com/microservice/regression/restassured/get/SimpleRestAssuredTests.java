package com.microservice.regression.restassured.get;

import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class SimpleRestAssuredTests {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users?page=2";

    @Test
    @DisplayName("test status code")
    public void testStatusCode() {
        Assert.assertEquals(getHttp(SERVICE_ENDPOINT).getStatusCode(), 200);
    }

    @Test
    @DisplayName("test response time is not null")
    public void testResponseTime() {
        Assert.assertNotNull(getHttp(SERVICE_ENDPOINT).getTime());
    }

    @Test
    @DisplayName("test content type")
    public void testContentType() {
        String contentType = getHttp(SERVICE_ENDPOINT).getHeader("content-type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");
    }

    @Test
    @DisplayName("test response body is not null")
    public void testResponseBody() {
        ResponseBody body = getHttp(SERVICE_ENDPOINT).getBody();
        Assert.assertNotNull(body);
    }

    @Test
    @DisplayName("test response body string")
    public void testResponseBodyString() {
        Assert.assertNotNull(getHttp(SERVICE_ENDPOINT).asString());
    }

}
