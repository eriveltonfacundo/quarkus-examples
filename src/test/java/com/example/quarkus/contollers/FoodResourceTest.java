package com.example.quarkus.contollers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FoodResourceTest {

    @Test
    public void testGetFoodsEndpoint() {
        given()
                .when().get("/foods")
                .then()
                .statusCode(404);
    }
}