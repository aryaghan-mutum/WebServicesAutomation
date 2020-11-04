package com.microservice.regression.restassured.resthelper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestAssuredHttpRequests {

    /**
     *
     * @param serviceUrl
     * @return
     */
    public static Response getHttp(String serviceUrl) {
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(serviceUrl);

        return response;
    }

    /**
     *
     * @param serviceUrl
     * @return
     */
    public static Response postHttp(String serviceUrl) {
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(serviceUrl);

        return response;
    }

    public Response deleteHttp(String serviceUrl) {
        given().delete(serviceUrl)
                .then();

        return null;
    }


}
