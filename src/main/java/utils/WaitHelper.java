package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for handling explicit waits
 */
public class WaitHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    /**
     * Wait for element to be visible
     * @param locator element locator
     * @return visible WebElement
     */
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     * @param locator element locator
     * @return clickable WebElement
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present in DOM
     * @param locator element locator
     * @return WebElement present in DOM
     */
    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be invisible
     * @param locator element locator
     * @return true if element becomes invisible
     */
    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be invisible with custom timeout
     * @param locator element locator
     * @param timeoutInSeconds custom timeout
     * @return true if element becomes invisible
     */
    public boolean waitForElementInvisible(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element
     * @param locator element locator
     * @param text expected text
     * @return true if text is present
     */
    public boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for URL to contain specific text
     * @param urlPart expected URL part
     * @return true if URL contains the text
     */
    public boolean waitForUrlContains(String urlPart) {
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }

    /**
     * Wait for alert to be present
     */
    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait for frame to be available and switch to it
     * @param locator frame locator
     */
    public void waitForFrameAndSwitch(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    /**
     * Custom wait with custom timeout
     * @param locator element locator
     * @param timeoutInSeconds custom timeout
     * @return visible WebElement
     */
    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}