package api;

import base.BaseTest;
import org.testng.annotations.Test;
import utils.ApiUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseTest {

    // ===== POSITIVE TESTS =====
    @Test
    public void getUserTest() {
        test = extent.createTest("GET User Test");

        Response response = given().spec(requestSpec)
                .when().get("https://reqres.in/api/users/2");

        // Log before assertion
        logRequestResponse("GET /users/2", response);

        // Safe assertion
        if (response.statusCode() == 200) {
            response.then().body("data.id", equalTo(2));
            test.pass("GET User Test passed");
        } else {
            test.fail("GET User Test failed with status " + response.statusCode());
        }
    }

    @Test
    public void createUserTest() {
        test = extent.createTest("POST User Test");

        String payload = ApiUtils.createUserPayload("Lerato", "leratoQA", "lerato@test.com");

        Response response = given().spec(requestSpec)
                .body(payload)
                .when().post("https://reqres.in/api/users");

        logRequestResponse(payload, response);

        if (response.statusCode() == 201) {
            response.then().body("name", equalTo("Lerato"));
            test.pass("POST User Test passed");
        } else {
            test.fail("POST User Test failed with status " + response.statusCode());
        }
    }

    // ===== NEGATIVE TESTS =====
    @Test
    public void getNonExistentUserTest() {
        test = extent.createTest("GET Non-Existent User Test");

        Response response = given().spec(requestSpec)
                .when().get("https://reqres.in/api/users/999");

        logRequestResponse("GET /users/999", response);

        if (response.statusCode() == 404) {
            test.pass("GET Non-Existent User Test passed");
        } else {
            test.fail("GET Non-Existent User Test failed with status " + response.statusCode());
        }
    }
}
