package api;
import base.BaseAPITest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Epic("API Testing")
@Feature("Brands API")
class BrandsAPITest extends BaseAPITest {

    /**
     * API 3: GET All Brands List
     *
     * Expected Result:
     * 1. API response code is 200
     * 2. Response contains list of brands
     */
    @Test(description = "API 3: GET All Brands List")
    @Description("Verify that GET request to /brandsList returns all brands")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a user, I want to retrieve all brands via API")
    public void testGetAllBrands() {
        logRequest(APIEndpoints.GET_ALL_BRANDS, "GET");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get(APIEndpoints.GET_ALL_BRANDS)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.asString().contains("brands"),
                "Response should contain brands");
    }

    /**
     * API 4: PUT To All Brands List
     *
     * Expected Result:
     * 1. API response code is 405
     * 2. Response message: "This request method is not supported."
     */
    @Test(description = "API 4: PUT To All Brands List")
    @Description("Verify that PUT request to /brandsList is not supported")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a system, I want to reject unsupported HTTP methods")
    public void testPutToAllBrands() {
        logRequest(APIEndpoints.PUT_ALL_BRANDS, "PUT");

        Response response = given()
                .spec(requestSpec)
                .when()
                .put(APIEndpoints.PUT_ALL_BRANDS)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 405, "Status code should be 405");
        Assert.assertTrue(response.asString().contains("This request method is not supported"),
                "Response should indicate method not supported");
    }
}
