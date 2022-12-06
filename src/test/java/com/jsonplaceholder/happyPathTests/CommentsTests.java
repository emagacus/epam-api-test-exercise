package com.jsonplaceholder.happyPathTests;

import org.testng.annotations.Test;
import com.jsonplaceholder.endpoints.Comments;
import runner.TestBase;
import static io.restassured.RestAssured.*;

public class CommentsTests extends TestBase {

    @Test
    public void getComments() {
        Comments.getComments()
                .then().assertThat().statusCode(200);
    }

    @Test(description = "Get comments from specific post")
    public void getCommentsFromPost() {
        specification = given()
                .queryParam("postId", env.getVariable("postId"));
        Comments.getComments(specification).then().assertThat().statusCode(200);
    }

}
