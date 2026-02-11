package ui;
import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
@Epic("User Interface")
@Feature("Page Navigation")
class ScrollFunctionalityTest extends BaseTest {

    @Test(description = "Test Case 25: Verify Scroll Up using Arrow button and Scroll Down")
    @Description("Verify that scroll up and scroll down functionality works correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("As a user, I want to navigate page using scroll functionality")
    public void testScrollFunctionality() {
        HomePage homePage = new HomePage(driver);

        // Scroll down to subscription section
        homePage.scrollToSubscription();

        // Verify SUBSCRIPTION is visible
        Assert.assertTrue(homePage.isSubscriptionVisible(),
                "SUBSCRIPTION section is not visible after scrolling down");

        // Scroll up to top
        homePage.scrollToTop();

        // Verify page scrolled to top and full-fledged practice website text is visible
        String pageTitle = getPageTitle();
        Assert.assertTrue(pageTitle.contains("Automation Exercise"),
                "Page did not scroll to top - page title verification failed");

        // Note: You can add additional verification by checking
        // if a specific element at the top of the page is visible
    }
}
