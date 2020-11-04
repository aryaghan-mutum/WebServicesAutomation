package com.microservice.regression.restassured.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class SimpleRestAssuredTests2 {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users/2";

    @Test
    @DisplayName("test ")
    public void test() {
        given().when()
                .get(SERVICE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

}
