package com.microservice.regression.restassured.post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredPostTests {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users?page=2";


//    @Test
//    @DisplayName("test all the last names")
//    public void testAllLastNames() {
//        (SERVICE_ENDPOINT).then()
//                .statusCode(200)
//                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"));
//    }


}
