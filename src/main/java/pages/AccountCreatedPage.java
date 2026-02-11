package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;

/**
 * Page Object for Account Created Page
 */
public class AccountCreatedPage extends BasePage {

    private final By accountCreatedTitle = By.xpath("//b[text()='Account Created!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ACCOUNT CREATED!' is visible")
    public boolean isAccountCreatedVisible() {
        return isDisplayed(accountCreatedTitle);
    }

    @Step("Click Continue button")
    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }
}