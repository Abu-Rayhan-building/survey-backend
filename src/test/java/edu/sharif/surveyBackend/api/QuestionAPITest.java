package edu.sharif.surveyBackend.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class QuestionAPITest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/q")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}