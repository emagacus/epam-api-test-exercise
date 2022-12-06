package com.jsonplaceholder.invalidDataTests;

import runner.TestBase;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.jsonplaceholder.endpoints.Posts;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.is;

import static io.restassured.RestAssured.*;

public class PostsNegative extends TestBase {

    @Test(description = "Verify post comments return empty list with invalid id")
    public void getPostComments() {
        Posts.getPostComments("invalid_id").then().assertThat().statusCode(200)
                .and().body("isEmpty()", Matchers.is(true));
    }

    @Test(description = "Verify id is assigned automatically")
    public void createPostIdCantBeAssigned() {
        String invalidId = env.getVariable("invalidId");
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("id", invalidId);
        payload.put("title", "Test title");
        payload.put("body", "Test body");
        payload.put("userId", env.getVariable("userId"));
        specification = given().contentType(ContentType.JSON).body(payload);
        Posts.createPost(specification).then().assertThat().statusCode(201)
                .and().body("id", not(invalidId));
    }

    @Test(description = "Verify there is an error when attempting to put and inexistent post")
    public void editInexistentPost() {

        Map<String, String> payload = new HashMap<String, String>();
        payload.put("id", env.getVariable("postId"));
        payload.put("title", "new title");
        payload.put("body", "new body");
        payload.put("userId", env.getVariable("userId"));
        specification = given().contentType(ContentType.JSON).body(payload);
        Posts.putPost(env.getVariable("invalidId"), specification).then().assertThat().statusCode(500);
    }

    @Test(description = "verify no real object is returning when patching an inexistent post")
    public void patchPost() {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("title", "Updated title");
        specification = given().contentType(ContentType.JSON).body(payload);
        Posts.patchPost(env.getVariable("invalidId"), specification).then().assertThat().statusCode(200)
                .and().body("title", containsString("Updated title"))
                .and().body("body", is(nullValue()));
    }

}
