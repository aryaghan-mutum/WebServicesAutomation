package com.microservice.regression.restassured.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class RestAssuredPostTests {

    private static final String SERVICE_ENDPOINT = "https://reqres.in/api/users";

    @Test
    @DisplayName("test all the last names")
    public void testAllLastNames() {

        HashMap<String, String> map = new HashMap<>();
        map.put("email", "x.com");
        map.put("first_name", "anu");
        map.put("last_name", "rag");

        given()
                .contentType("application/json")
                .body(map)
                .then()
                .statusCode(201)
                .body("data.id[0]", equalTo(1))
                .body("data.email[0]", equalTo("george.bluth@reqres.in"))
                .body("data.first_name[0]", equalTo("George"))
                .body("data.last_name[0]", equalTo("Bluth"));


    }


}
