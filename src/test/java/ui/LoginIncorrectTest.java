package ui;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Test Case 3: Login User with incorrect email and password
 */
@Epic("User Management")
@Feature("User Authentication")
public class LoginIncorrectTest extends BaseTest {

    @Test(description = "Test Case 3: Login User with incorrect email and password", priority = 3)
    @Description("Verify error message when login with incorrect credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a system, I want to show error for incorrect login credentials")
    public void testLoginWithIncorrectCredentials() {
        String email = "wrongemail" + System.currentTimeMillis() + "@example.com";
        String password = "wrongpassword123";

        Allure.step("Attempting login with incorrect credentials", () -> {
            Allure.addAttachment("Email", email);
            Allure.addAttachment("Password", "********");
        });

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();

        signupLoginPage.performLogin(email, password);

        // Verify error message is displayed
        Assert.assertTrue(signupLoginPage.isLoginErrorMessageDisplayed(),
                "Error message is not displayed");
        Assert.assertEquals(signupLoginPage.getLoginErrorMessage(),
                "Your email or password is incorrect!",
                "Error message text does not match");

        Allure.step("Error message displayed correctly for incorrect credentials");
    }
}