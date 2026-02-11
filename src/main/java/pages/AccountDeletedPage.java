package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {

    private final By accountDeletedTitle = By.xpath("//b[text()='Account Deleted!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ACCOUNT DELETED!' is visible")
    public boolean isAccountDeletedVisible() {
        return isDisplayed(accountDeletedTitle);
    }

    @Step("Click Continue button")
    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }
}
