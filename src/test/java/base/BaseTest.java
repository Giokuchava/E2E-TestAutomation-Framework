package base;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;
import utils.ScreenshotHelper;
import utils.WaitHelper;

/**
 * Base test class for all UI tests
 * Handles driver initialization and cleanup
 */
public class BaseTest {

    protected WebDriver driver;
    protected WaitHelper waitHelper;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
        waitHelper = new WaitHelper(driver);

        driver.get(ConfigReader.getBaseUrl());

        // TEMPORARY: Keep browser open to see what's happening
        try {
            Thread.sleep(3000); // Wait 3 seconds after page load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Allure.addAttachment("Test Started", "Browser: " + ConfigReader.getBrowser());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Capture screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment("Failure Screenshot", "Test failed: " + result.getName());
            ScreenshotHelper.captureScreenshotForAllure(driver);

            // Also save to file
            ScreenshotHelper.captureScreenshot(driver, result.getName());
        }

        // Quit driver
        DriverManager.quitDriver();
    }

    /**
     * Navigate to a specific page
     * @param url target URL
     */
    protected void navigateTo(String url) {
        driver.get(url);
        Allure.step("Navigated to: " + url);
    }

    /**
     * Get current page title
     * @return page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     * @return current URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}