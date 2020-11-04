package com.microservice.regression.restassured.get;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microservice.regression.restassured.resthelper.RestAssuredHttpRequests.getHttp;
import static com.microservice.regression.restassured.resthelper.RestAssuredTestAssist.validateStatusCode;
import static org.hamcrest.Matchers.*;

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
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("test first ID")
    public void testFirstID() {
        getHttp(SERVICE_ENDPOINT)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .statusCode(200)
                .body("data.id[0]", equalTo(7)); //note: can also be used is(7)
    }

    @Test
    @DisplayName("test first Email")
    public void testFirstEmail() {
        getHttp(SERVICE_ENDPOINT)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .statusCode(200)
                .body("data.email[0]", equalTo("michael.lawson@reqres.in"));
    }

    @Test
    @DisplayName("test all the values in the first object in an array")
    public void testAllValuesInFirstObject() {
        getHttp(SERVICE_ENDPOINT)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .statusCode(200)
                .body("data.id[0]", equalTo(7))
                .body("data.email[0]", equalTo("michael.lawson@reqres.in"))
                .body("data.first_name[0]", equalTo("Michael"))
                .body("data.last_name[0]", equalTo("Lawson"));
    }

    @Test
    @DisplayName("test all the last names")
    public void testAllLastNames() {
        getHttp(SERVICE_ENDPOINT)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .statusCode(200)
                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"));
    }

    @Test
    @DisplayName("test the total length9size of the objects")
    public void testTotalLengthOfObjects() {
        getHttp(SERVICE_ENDPOINT)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .statusCode(200)
                .body("size()", is(6));
    }

}
