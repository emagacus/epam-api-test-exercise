package com.jsonplaceholder.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

/**
 * Common methods for the /comments  endpoint
 */
public class Comments {

    private static String endpoint = "/comments";

    public static Response getComments(RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get(endpoint) : get(endpoint);
    }

}
