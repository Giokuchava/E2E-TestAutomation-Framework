package api;

import base.BaseAPITest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("API Testing")
@Feature("Products API")
public class ProductsAPITest extends BaseAPITest {

    /**
     * API 1: GET All Products List
     *
     * Expected Result:
     * 1. API response code is 200
     * 2. Response contains list of products
     */
    @Test(description = "API 1: GET All Products List")
    @Description("Verify that GET request to /productsList returns all products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a user, I want to retrieve all products via API")
    public void testGetAllProducts() {
        logRequest(APIEndpoints.GET_ALL_PRODUCTS, "GET");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get(APIEndpoints.GET_ALL_PRODUCTS)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.asString().contains("products"),
                "Response should contain products");
    }

    /**
     * API 2: POST To All Products List
     *
     * Expected Result:
     * 1. API response code is 405
     * 2. Response message: "This request method is not supported."
     */
    @Test(description = "API 2: POST To All Products List")
    @Description("Verify that POST request to /productsList is not supported")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a system, I want to reject unsupported HTTP methods")
    public void testPostToAllProducts() {
        logRequest(APIEndpoints.POST_ALL_PRODUCTS, "POST");

        Response response = given()
                .spec(requestSpec)
                .when()
                .post(APIEndpoints.POST_ALL_PRODUCTS)
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
     * API 5: POST To Search Product
     *
     * Test Data: search_product=top
     * Expected Result:
     * 1. API response code is 200
     * 2. Response contains searched products
     */
    @Test(description = "API 5: POST To Search Product")
    @Description("Verify that POST request to search product returns searched products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a user, I want to search for products via API")
    public void testSearchProduct() {
        String searchTerm = "top";
        logRequest(APIEndpoints.SEARCH_PRODUCT, "POST");

        Response response = given()
                .spec(requestSpec)
                .formParam("search_product", searchTerm)
                .when()
                .post(APIEndpoints.SEARCH_PRODUCT)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.asString().contains("products"),
                "Response should contain products");
    }

    /**
     * API 6: POST To Search Product without search_product parameter
     *
     * Expected Result:
     * 1. API response code is 400
     * 2. Response message: "Bad request, search_product parameter is missing in POST request."
     */
    @Test(description = "API 6: POST To Search Product without search_product parameter")
    @Description("Verify that POST request without search_product parameter returns error")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a system, I want to validate required parameters")
    public void testSearchProductWithoutParameter() {
        logRequest(APIEndpoints.SEARCH_PRODUCT, "POST");

        Response response = given()
                .spec(requestSpec)
                .when()
                .post(APIEndpoints.SEARCH_PRODUCT)
                .then()
                .extract()
                .response();

        logResponse(response.statusCode(), response.asString());

        // Assertions
        Assert.assertEquals(response.statusCode(), 400, "Status code should be 400");
        Assert.assertTrue(response.asString().contains("search_product parameter is missing"),
                "Response should indicate missing parameter");
    }
}

