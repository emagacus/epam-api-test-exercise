package com.jsonplaceholder.endpoints;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

/**
 * Common methods for the /posts  endpoint
 */
public class Posts {
    private static String endpoint = "/posts";

    public static Response getPosts(RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get(endpoint) : get(endpoint);
    }

    public static Response getPost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get("%s/%s".formatted(endpoint, postId))
                : get("%s/%s".formatted(endpoint, postId));
    }

    public static Response getPostComments(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].get("{%s/%s/comments".formatted(endpoint, postId))
                : get("%s/%s/comments".formatted(endpoint, postId));
    }

    public static Response createPost(RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].post(endpoint) : post(endpoint);
    }

    public static Response putPost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].put("%s/%s".formatted(endpoint, postId))
                : put("%s/%s".formatted(endpoint, postId));
    }

    public static Response patchPost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].patch("%s/%s".formatted(endpoint, postId))
                : patch("%s/%s".formatted(endpoint, postId));
    }

    public static Response deletePost(String postId, RequestSpecification... specification) {
        return specification.length > 0 ? specification[0].delete("%s/%s".formatted(endpoint, postId))
                : delete("%s/%s".formatted(endpoint, postId));
    }
}
