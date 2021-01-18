package com.microservice.regression.restassured.resthelper;

import io.restassured.response.ValidatableResponse;

import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredTestAssist {

    /**
     * Validate the status code of a response is 200
     */
    public static ValidatableResponse validateStatusCode(String serviceUrl, Integer statusCode) {
        ValidatableResponse validatableResponse = getHttp(serviceUrl)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .statusCode(statusCode);

        return validatableResponse;
    }

}
