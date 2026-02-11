package api;

import base.BaseAPITest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Epic("API Testing")
@Feature("User Authentication API")
class UserAuthenticationAPITest extends BaseAPITest {

    /**
     * API 7: POST To Verify Login with valid details
     *
     * Test Data: email & password
     * Expected Result:
     * 1. API response code is 200
     * 2. Response message contains "User exists!"
     */
    @Test(description = "API 7: POST To Verify Login with valid details")
    @Description("Verify that POST request with valid credentials returns success")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a user, I want to verify my login credentials via API")
    public void testVerifyLoginWithValidDetails() {
        logRequest(APIEndpoints.VERIFY_LOGIN, "POST");

        Response response = given()
                .spec(requestSpec)
                .formParam("email", "test@example.com")
                .formParam("password", "test123")
                .when()
                .post(APIEndpoints.VERIFY_LOGIN)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    }

    /**
     * API 8: POST To Verify Login without email parameter
     *
     * Test Data: password (without email)
     * Expected Result:
     * 1. API response code is 400
     * 2. Response message: "Bad request, email or password parameter is missing in POST request."
     */
    @Test(description = "API 8: POST To Verify Login without email parameter")
    @Description("Verify that POST request without email parameter returns error")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a system, I want to validate required login parameters")
    public void testVerifyLoginWithoutEmail() {
        logRequest(APIEndpoints.VERIFY_LOGIN, "POST");

        Response response = given()
                .spec(requestSpec)
                .formParam("password", "test123")
                .when()
                .post(APIEndpoints.VERIFY_LOGIN)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 400, "Status code should be 400");
        Assert.assertTrue(response.asString().contains("email or password parameter is missing"),
                "Response should indicate missing parameter");
    }

    /**
     * API 9: DELETE To Verify Login
     *
     * Expected Result:
     * 1. API response code is 405
     * 2. Response message: "This request method is not supported."
     */
    @Test(description = "API 9: DELETE To Verify Login")
    @Description("Verify that DELETE request to /verifyLogin is not supported")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a system, I want to reject unsupported HTTP methods")
    public void testDeleteVerifyLogin() {
        logRequest(APIEndpoints.VERIFY_LOGIN, "DELETE");

        Response response = given()
                .spec(requestSpec)
                .when()
                .delete(APIEndpoints.VERIFY_LOGIN)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 405, "Status code should be 405");
        Assert.assertTrue(response.asString().contains("This request method is not supported"),
                "Response should indicate method not supported");
    }

    /**
     * API 11: POST To Create/Register User Account
     *
     * Test Data: name, email, password, title, birth_date, birth_month, birth_year,
     *            firstname, lastname, company, address1, address2, country, zipcode,
     *            state, city, mobile_number
     * Expected Result:
     * 1. API response code is 201
     * 2. Response message: "User created!"
     */
    @Test(description = "API 11: POST To Create/Register User Account")
    @Description("Verify that POST request creates a new user account")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a new user, I want to create an account via API")
    public void testCreateUserAccount() {
        logRequest(APIEndpoints.CREATE_ACCOUNT, "POST");

        String timestamp = String.valueOf(System.currentTimeMillis());

        Response response = given()
                .spec(requestSpec)
                .formParam("name", "Test User")
                .formParam("email", "testuser" + timestamp + "@example.com")
                .formParam("password", "password123")
                .formParam("title", "Mr")
                .formParam("birth_date", "15")
                .formParam("birth_month", "5")
                .formParam("birth_year", "1990")
                .formParam("firstname", "Test")
                .formParam("lastname", "User")
                .formParam("company", "Test Company")
                .formParam("address1", "123 Test St")
                .formParam("address2", "Apt 4")
                .formParam("country", "India")
                .formParam("zipcode", "12345")
                .formParam("state", "TestState")
                .formParam("city", "TestCity")
                .formParam("mobile_number", "1234567890")
                .when()
                .post(APIEndpoints.CREATE_ACCOUNT)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 201, "Status code should be 201");
        Assert.assertTrue(response.asString().contains("User created!"),
                "Response should indicate user created");
    }
}
