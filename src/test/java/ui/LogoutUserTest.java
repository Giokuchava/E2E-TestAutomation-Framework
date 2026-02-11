package ui;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Test Case 4: Logout User
 */
@Epic("User Management")
@Feature("User Authentication")
public class LogoutUserTest extends BaseTest {

    @Test(description = "Test Case 4: Logout User",
            priority = 4,
            dependsOnMethods = {"ui.RegisterUserTest.testRegisterUserForLogin"})
    @Description("Verify that user can logout successfully")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a logged-in user, I want to logout from my account")
    public void testLogoutUser() {
        // Use credentials from RegisterUserTest
        String email = RegisterUserTest.registeredEmail;
        String password = RegisterUserTest.registeredPassword;
        String name = RegisterUserTest.registeredName;

        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        signupLoginPage.performLogin(email, password);

        // Verify user is logged in
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(name),
                "User is not logged in");

        Allure.step("User is logged in, now testing logout");

        // Logout
        homePage.clickLogout();

        // Verify user is navigated to login page
        signupLoginPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupLoginPage.isLoginToAccountVisible(),
                "User is not navigated to login page after logout");

        Allure.step("User logged out successfully");
    }
}