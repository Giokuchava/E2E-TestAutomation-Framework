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
        // Create a fresh account for this test instead of relying on RegisterUserTest
        // This is more reliable because the account definitely exists
        String name = "LogoutTestUser";
        String email = "logouttest" + System.currentTimeMillis() + "@example.com";
        String password = "Test@123";

        System.out.println("=== LogoutUserTest Debug Info ===");
        System.out.println("Creating fresh account for logout test");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);
        System.out.println("================================");

        // Create account
        HomePage homePage = new HomePage(driver);
        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        SignupPage signupPage = signupLoginPage.performSignup(name, email);

        // Fill signup form
        AccountCreatedPage accountCreatedPage = signupPage.fillCompleteSignupForm(
                password, "15", "5", "1990", "Test", "User", "TestCo",
                "123 Test St", "Apt 1", "India", "TestState", "TestCity", "12345", "1234567890"
        );

        // Continue to home page (user is now logged in)
        homePage = accountCreatedPage.clickContinue();

        System.out.println("Account created successfully, user is logged in");

        // Verify user is logged in
        Assert.assertTrue(homePage.isUserLoggedIn(name),
                "User is not logged in after registration");

        Allure.step("User is logged in, now testing logout");

        // Logout and get login page (clickLogout waits for redirect)
        signupLoginPage = homePage.clickLogout();

        // Verify user is navigated to login page
        Assert.assertTrue(signupLoginPage.isLoginToAccountVisible(),
                "User is not navigated to login page after logout");

        Allure.step("User logged out successfully");

        // Clean up - delete the test account
        try {
            homePage = signupLoginPage.performLogin(email, password);
            AccountDeletedPage deletedPage = homePage.clickDeleteAccount();
            Allure.step("Test account cleaned up");
        } catch (Exception e) {
            System.out.println("Note: Could not clean up test account: " + e.getMessage());
        }
    }
}