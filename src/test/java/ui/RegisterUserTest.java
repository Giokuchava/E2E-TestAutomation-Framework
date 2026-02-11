package ui;

import base.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Test Case 1: Register User
 *
 * This test creates a user account and stores credentials for use in login tests
 */
@Epic("User Management")
@Feature("User Registration")
public class RegisterUserTest extends BaseTest {

    private Faker faker = new Faker();

    // Static variables to store credentials for use in login tests
    public static String registeredEmail;
    public static String registeredPassword;
    public static String registeredName;
    private static boolean accountDeleted = false;

    @Test(description = "Test Case 1: Register User for Login Tests", priority = 1)
    @Description("Register a user account and keep it for login tests")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a new user, I want to register an account so that I can login later")
    public void testRegisterUserForLogin() {
        // Test data - generate unique credentials
        registeredName = faker.name().firstName();
        registeredEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        registeredPassword = "Test@123";
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String company = faker.company().name();
        String address1 = faker.address().streetAddress();
        String address2 = faker.address().secondaryAddress();
        String state = faker.address().state();
        String city = faker.address().city();
        String zipcode = faker.address().zipCode();
        String mobileNumber = faker.phoneNumber().cellPhone();

        Allure.step("Generated credentials for registration and login", () -> {
            Allure.addAttachment("Email", registeredEmail);
            Allure.addAttachment("Password", "********");
            Allure.addAttachment("Name", registeredName);
        });

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page is not loaded");

        SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isNewUserSignupVisible(),
                "New User Signup section is not visible");

        SignupPage signupPage = signupLoginPage.performSignup(registeredName, registeredEmail);
        Assert.assertTrue(signupPage.isEnterAccountInfoVisible(),
                "Enter Account Information section is not visible");

        AccountCreatedPage accountCreatedPage = signupPage.fillCompleteSignupForm(
                registeredPassword, "15", "5", "1990", firstName, lastName, company,
                address1, address2, "India", state, city, zipcode, mobileNumber
        );

        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(),
                "Account Created message is not visible");

        homePage = accountCreatedPage.clickContinue();
        Assert.assertTrue(homePage.isUserLoggedIn(registeredName),
                "User is not logged in as " + registeredName);

        // Logout after registration to test login functionality
        homePage.clickLogout();

        Allure.step("User registered successfully and logged out. Credentials saved for login test.");
    }

    @Test(description = "Test Case 1 Full: Register and Delete User", priority = 10, dependsOnMethods = "testRegisterUserForLogin")
    @Description("Complete registration test with account deletion")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a user, I want to be able to delete my account")
    public void testDeleteUserAccount() {
        // Only delete if not already deleted
        if (!accountDeleted) {
            HomePage homePage = new HomePage(driver);

            // Login first
            SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
            signupLoginPage.performLogin(registeredEmail, registeredPassword);

            // Delete account
            AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
            Assert.assertTrue(accountDeletedPage.isAccountDeletedVisible(),
                    "Account Deleted message is not visible");

            accountDeletedPage.clickContinue();
            accountDeleted = true;
        }
    }
}