package com.microservice.regression.restassured.resthelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredHttpRequests {

    public static Response getHttp(String serviceUrl) {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(serviceUrl);

        return response;
    }

    public static Response getHttp2(String serviceUrl) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .when()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .get(serviceUrl)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }

    public static Response postHttp(String serviceUrl) {
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post(serviceUrl);

        return response;
    }

    public Response deleteHttp(String serviceUrl) {
        given().delete(serviceUrl)
                .then();

        return null;
    }


}
