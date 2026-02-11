package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

/**
 * Base page class with common methods for all page objects
 */
public class BasePage {

    protected WebDriver driver;
    protected WaitHelper waitHelper;
    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Click on element
     * @param locator element locator
     */
    @Step("Click on element")
    protected void click(By locator) {
        waitHelper.waitForElementClickable(locator).click();
    }

    /**
     * Type text into element
     * @param locator element locator
     * @param text text to type
     */
    @Step("Type '{text}' into element")
    protected void type(By locator, String text) {
        WebElement element = waitHelper.waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element
     * @param locator element locator
     * @return element text
     */
    @Step("Get text from element")
    protected String getText(By locator) {
        return waitHelper.waitForElementVisible(locator).getText();
    }

    /**
     * Check if element is displayed
     * @param locator element locator
     * @return true if element is displayed
     */
    @Step("Verify element is displayed")
    protected boolean isDisplayed(By locator) {
        try {
            return waitHelper.waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Scroll to element
     * @param locator element locator
     */
    @Step("Scroll to element")
    protected void scrollToElement(By locator) {
        WebElement element = waitHelper.waitForElementPresent(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scroll to bottom of page
     */
    @Step("Scroll to bottom of page")
    protected void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scroll to top of page
     */
    @Step("Scroll to top of page")
    protected void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Hover over element
     * @param locator element locator
     */
    @Step("Hover over element")
    protected void hover(By locator) {
        WebElement element = waitHelper.waitForElementVisible(locator);
        actions.moveToElement(element).perform();
    }

    /**
     * Get attribute value
     * @param locator element locator
     * @param attribute attribute name
     * @return attribute value
     */
    @Step("Get attribute '{attribute}' from element")
    protected String getAttribute(By locator, String attribute) {
        return waitHelper.waitForElementVisible(locator).getAttribute(attribute);
    }

    /**
     * Wait for page to load
     */
    @Step("Wait for page to load")
    protected void waitForPageLoad() {
        waitHelper.waitForElementPresent(By.tagName("body"));
    }

    /**
     * Accept alert
     */
    @Step("Accept alert")
    protected void acceptAlert() {
        waitHelper.waitForAlert();
        driver.switchTo().alert().accept();
    }

    /**
     * Dismiss alert
     */
    @Step("Dismiss alert")
    protected void dismissAlert() {
        waitHelper.waitForAlert();
        driver.switchTo().alert().dismiss();
    }

    /**
     * Get alert text
     * @return alert text
     */
    @Step("Get alert text")
    protected String getAlertText() {
        waitHelper.waitForAlert();
        return driver.switchTo().alert().getText();
    }
}