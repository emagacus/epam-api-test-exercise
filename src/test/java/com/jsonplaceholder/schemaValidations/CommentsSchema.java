package com.jsonplaceholder.schemaValidations;
import runner.TestBase;

import java.io.File;
import org.testng.annotations.Test;
import com.jsonplaceholder.endpoints.Comments;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CommentsSchema extends TestBase{

    @Test
    public void validateCommentsSchema(){
        Comments.getComments()
        .then().assertThat().body(JsonSchemaValidator.
        matchesJsonSchema(new File("./src/test/java/data/schemas/comments.json")));
    }
}
