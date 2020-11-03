package com.microservice.regression.restassured.delete;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredDeleteTests {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users/2";

    /**
     * 204 - No Content: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/204
     * Note: Test will fail if the statusCode is 200
     */
    @Test
    @DisplayName("test delete")
    public void testDelete() {
        given().delete(SERVICE_ENDPOINT).then().statusCode(204).log().all();
    }

}
