package com.jsonplaceholder.happyPathTests;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.jsonplaceholder.endpoints.Posts;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.*;
import runner.TestBase;

public class PostTests extends TestBase {

    @Test
    public void getPosts() {
        Posts.getPosts().then().assertThat().statusCode(200);
    }

    @Test
    public void getPost() {
        Posts.getPost(env.getVariable("postId")).then().assertThat().statusCode(200);
    }

    @Test
    public void getPostComments() {
        Posts.getPostComments(env.getVariable("postId")).then().assertThat().statusCode(200);
    }

    @Test
    public void createPost() {

        Map<String, String> payload = new HashMap<String, String>();
        payload.put("title", "Test title");
        payload.put("body", "Test body");
        payload.put("userId", env.getVariable("userId"));
        specification = given().contentType(ContentType.JSON).body(payload);
        Posts.createPost(specification).then().assertThat().statusCode(201)
                .and().body("title", containsString("Test title"))
                .and().body("body", containsString("Test body"));
    }

    @Test
    public void editPost() {

        Map<String, String> payload = new HashMap<String, String>();
        payload.put("id", env.getVariable("postId"));
        payload.put("title", "new title");
        payload.put("body", "new body");
        payload.put("userId", env.getVariable("userId"));
        specification = given().contentType(ContentType.JSON).body(payload);
        Posts.putPost(env.getVariable("postId"), specification).then().assertThat().statusCode(200)
                .and().body("title", containsString("new title"))
                .and().body("body", containsString("new body"));
    }

    @Test
    public void patchPost() {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("title", "Updated title");
        specification = given().contentType(ContentType.JSON).body(payload);
        Posts.patchPost(env.getVariable("postId"), specification).then().assertThat().statusCode(200)
                .and().body("title", containsString("Updated title"))
                .and().body("body", is(notNullValue()));
    }

    @Test
    public void deletePost() {
        Posts.deletePost(env.getVariable("postId")).then().assertThat().statusCode(200);
    }
}
