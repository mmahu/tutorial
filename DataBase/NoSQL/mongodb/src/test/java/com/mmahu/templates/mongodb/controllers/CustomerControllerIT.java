package com.mmahu.templates.mongodb.controllers;

import com.mmahu.templates.mongodb.model.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {

    @LocalServerPort
    private long port;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI ="http://localhost:" + port;
    }

    @Test
    public void testCrud() {
        Customer smith = new Customer("2", "smith");
        Customer taylor = new Customer("3", "taylor");

        given().body(smith).contentType(ContentType.JSON).put("/customer");
        given().body(taylor).contentType(ContentType.JSON).put("/customer");

        given().get("/customer").then().body("id", hasItems("2", "3"));

        given().body(smith).contentType(ContentType.JSON).delete("/customer");
        given().body(taylor).contentType(ContentType.JSON).delete("/customer");

        given().get("/customer").then().body("id", not(hasItems("2", "3")));
    }

    @Test
    public void testFind() {
        Customer[] result = given().get("/customer").as(Customer[].class);
    }
}
