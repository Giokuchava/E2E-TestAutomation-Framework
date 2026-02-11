package base;

import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

/**
 * Base test class for all API tests
 * Configures RestAssured and common settings
 */
public class BaseAPITest {

    protected RequestSpecification requestSpec;

    @BeforeClass(alwaysRun = true)
    public void setUpAPI() {
        // Set base URI
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();

        // Build request specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

        Allure.addAttachment("API Base URL", ConfigReader.getApiBaseUrl());
    }

    /**
     * Log request details to Allure
     */
    protected void logRequest(String endpoint, String method) {
        Allure.step("API Request: " + method + " " + endpoint);
    }

    /**
     * Log response details to Allure
     */
    protected void logResponse(int statusCode, String responseBody) {
        Allure.step("Response Status Code: " + statusCode);
        Allure.addAttachment("Response Body", "application/json", responseBody, "json");
    }
}