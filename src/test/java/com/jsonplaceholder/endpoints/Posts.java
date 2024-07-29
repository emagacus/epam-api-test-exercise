package com.jsonplaceholder.endpoints;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.patch;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.put;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Common methods for the /posts  ENDPOINT
 */
public class Posts {
    private final static String ENDPOINT = "/posts";

    public static Response getPosts(RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get(ENDPOINT) : get(ENDPOINT);
    }

    public static Response getPost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get("%s/%s".formatted(ENDPOINT, postId))
                : get("%s/%s".formatted(ENDPOINT, postId));
    }

    public static Response getPostComments(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get("{%s/%s/comments".formatted(ENDPOINT, postId))
                : get("%s/%s/comments".formatted(ENDPOINT, postId));
    }

    public static Response createPost(RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].post(ENDPOINT) : post(ENDPOINT);
    }

    public static Response putPost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].put("%s/%s".formatted(ENDPOINT, postId))
                : put("%s/%s".formatted(ENDPOINT, postId));
    }

    public static Response patchPost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].patch("%s/%s".formatted(ENDPOINT, postId))
                : patch("%s/%s".formatted(ENDPOINT, postId));
    }

    public static Response deletePost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].delete("%s/%s".formatted(ENDPOINT, postId))
                : delete("%s/%s".formatted(ENDPOINT, postId));
    }
}
