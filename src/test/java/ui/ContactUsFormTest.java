package ui;
import base.BaseTest;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
@Epic("Customer Support")
@Feature("Contact Form")
class ContactUsFormTest extends BaseTest {

    @Test(description = "Test Case 6: Contact Us Form")
    @Description("Verify that contact us form can be submitted successfully")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a user, I want to contact support via contact form")
    public void testContactUsForm() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String subject = "Test Subject";
        String message = "This is a test message for contact us form.";

        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = homePage.clickContactUs();

        // Verify GET IN TOUCH is visible
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(),
                "GET IN TOUCH section is not visible");

        // Fill contact form
        contactUsPage.fillContactForm(name, email, subject, message);

        // Note: Upload file is optional - if you have a test file, you can use:
        // contactUsPage.uploadFile("path/to/test/file.txt");

        // Submit form
        contactUsPage.clickSubmit();

        // Verify success message
        Assert.assertTrue(contactUsPage.isSuccessMessageDisplayed(),
                "Success message is not displayed");

        // Click Home button
        homePage = contactUsPage.clickHome();
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Home page is not loaded after clicking Home button");
    }
}
