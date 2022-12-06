package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

    protected TestEnvironment env;
    protected RequestSpecification specification;

    @BeforeClass
    public void loadEnvironment() {
        env = new TestEnvironment("./src/test/java/data/environments/%s".formatted(System.getProperty("testEnvironment")));
        RestAssured.baseURI = env.getHost();
    }

    @BeforeMethod
    public void resetSpecification()
    {
        specification = null;
    }

    public String getResponseValue(Response response, String key){
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get(key);
    }
}
