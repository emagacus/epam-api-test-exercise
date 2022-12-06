package com.jsonplaceholder.schemaValidations;
import runner.TestBase;

import java.io.File;
import org.testng.annotations.Test;
import com.jsonplaceholder.endpoints.Posts;
import io.restassured.module.jsv.JsonSchemaValidator;

public class PostsSchema extends TestBase{
    
    @Test
    public void validatePostSchema() {
        Posts.getPost(env.getVariable("postId")).then().assertThat().body(JsonSchemaValidator.
        matchesJsonSchema(new File("./src/test/java/data/schemas/post.json")));
    }

    @Test
    public void validatePostsSchema() {
        Posts.getPosts().then().assertThat().body(JsonSchemaValidator.
        matchesJsonSchema(new File("./src/test/java/data/schemas/posts.json")));
    }
}
