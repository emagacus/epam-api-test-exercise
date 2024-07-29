package com.jsonplaceholder.endpoints;

import static io.restassured.RestAssured.get;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Common methods for the /comments  endpoint
 */
public class Comments {

    private final static String ENDPOINT = "/comments";

    public static Response getComments(RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get(ENDPOINT) : get(ENDPOINT);
    }

}
