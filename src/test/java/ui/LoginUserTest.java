package ui;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Test Case 2: Login User with correct email and password
 *
 * This test depends on RegisterUserTest to have valid credentials
 */
@Epic("User Management")
@Feature("User Authentication")
public class LoginUserTest extends BaseTest {

    @Test(description = "Test Case 2: Login User with correct email and password",
            priority = 2,
            dependsOnMethods = {"ui.RegisterUserTest.testRegisterUserForLogin"})
    @Description("Verify that user can login with correct credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a registered user, I want to login with my credentials")
    public void testLoginWithCorrectCredentials() {
        // Use credentials from RegisterUserTest
        String email = RegisterUserTest.registeredEmail;
        String password = RegisterUserTest.registeredPassword;
        String name = RegisterUserTest.registeredName;

        Allure.step("Using credentials from registration", () -> {
            Allure.addAttachment("Email", email);
            Allure.addAttachment("Expected Name", name);
        });

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page is not loaded");

        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isLoginToAccountVisible(),
                "Login to your account section is not visible");

        signupLoginPage.performLogin(email, password);

        // Verify user is logged in
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(name),
                "User is not logged in as " + name);

        Allure.step("User logged in successfully with email: " + email);

        // Logout after test
        homePage.clickLogout();
    }
}