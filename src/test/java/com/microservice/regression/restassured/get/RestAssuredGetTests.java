package com.microservice.regression.restassured.get;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredGetTests {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users?page=2";

    @Test
    @DisplayName("print respons body")
    public void printResponseBody() {
        getHttp(SERVICE_ENDPOINT)
                .then().statusCode(200)
                .log().all();
    }

    @Test
    @DisplayName("test first ID")
    public void testFirstID() {
        getHttp(SERVICE_ENDPOINT).then().statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

    @Test
    @DisplayName("test first Email")
    public void testFirstEmail() {
        getHttp(SERVICE_ENDPOINT).then().statusCode(200)
                .body("data.email[0]", equalTo("michael.lawson@reqres.in"));
    }

    @Test
    @DisplayName("test all the values in the first object in an array")
    public void testAllValuesInFirstObject() {
        getHttp(SERVICE_ENDPOINT).then().statusCode(200)
                .body("data.id[0]", equalTo(7))
                .body("data.email[0]", equalTo("michael.lawson@reqres.in"))
                .body("data.first_name[0]", equalTo("Michael"))
                .body("data.last_name[0]", equalTo("Lawson"));
    }

    @Test
    @DisplayName("test all the last names")
    public void testAllLastNames() {
        getHttp(SERVICE_ENDPOINT).then().statusCode(200)
                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"));
    }

}
