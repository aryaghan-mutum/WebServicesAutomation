package com.microservice.regression.restassured.post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;
import static com.microservice.regression.restassured.resthelper.RestAssuredTestAssist.validateStatusCode;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.postHttp;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredPostTests {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users?page=2";


    @Test
    @DisplayName("test all the last names")
    public void testAllLastNames() {
        postHttp(SERVICE_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(201).log().all();
    }


}
