package com.jsonplaceholder.invalidDataTests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.jsonplaceholder.endpoints.Comments;
import runner.TestBase;
import static io.restassured.RestAssured.*;

public class CommentsNegative extends TestBase {

    @Test(description = "Get comments from specific post with invalid id")
    public void getCommentsFromInvalidPost() {
        specification = given()
                .queryParam("postId", env.getVariable("invalidId"));
        Comments.getComments(specification).then().assertThat().statusCode(200)
                .and().body("isEmpty()", Matchers.is(true));
    }

}
