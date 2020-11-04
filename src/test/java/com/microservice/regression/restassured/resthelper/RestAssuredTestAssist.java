package com.microservice.regression.restassured.resthelper;

import io.restassured.response.ValidatableResponse;

import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;

public class RestAssuredTestAssist {

    /**
     * Validate the status code of a response is 200
     * @param serviceUrl
     * @param statusCode
     * @return
     */
    public static ValidatableResponse validateStatusCode(String serviceUrl, Integer statusCode) {
        ValidatableResponse validatableResponse = getHttp(serviceUrl)
                .then()
                .assertThat()
                .statusCode(statusCode);

        return validatableResponse;
    }

}
