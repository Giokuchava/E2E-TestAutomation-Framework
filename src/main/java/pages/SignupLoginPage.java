package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Signup/Login Page
 */
public class SignupLoginPage extends BasePage {

    // Locators for Signup section
    private final By signupNameInput = By.name("name");
    private final By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");

    // Locators for Login section
    private final By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private final By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");
    private final By loginErrorMessage = By.xpath("//p[text()='Your email or password is incorrect!']");

    // Common
    private final By newUserSignupTitle = By.xpath("//h2[text()='New User Signup!']");
    private final By loginToAccountTitle = By.xpath("//h2[text()='Login to your account']");

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'New User Signup!' is visible")
    public boolean isNewUserSignupVisible() {
        return isDisplayed(newUserSignupTitle);
    }

    @Step("Verify 'Login to your account' is visible")
    public boolean isLoginToAccountVisible() {
        return isDisplayed(loginToAccountTitle);
    }

    @Step("Enter signup name: {name}")
    public void enterSignupName(String name) {
        type(signupNameInput, name);
    }

    @Step("Enter signup email: {email}")
    public void enterSignupEmail(String email) {
        type(signupEmailInput, email);
    }

    @Step("Click Signup button")
    public SignupPage clickSignupButton() {
        click(signupButton);
        return new SignupPage(driver);
    }

    @Step("Perform new user signup with name: {name} and email: {email}")
    public SignupPage performSignup(String name, String email) {
        enterSignupName(name);
        enterSignupEmail(email);
        return clickSignupButton();
    }

    @Step("Enter login email: {email}")
    public void enterLoginEmail(String email) {
        type(loginEmailInput, email);
    }

    @Step("Enter login password")
    public void enterLoginPassword(String password) {
        type(loginPasswordInput, password);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Perform login with email: {email}")
    public void performLogin(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }

    @Step("Verify login error message is displayed")
    public boolean isLoginErrorMessageDisplayed() {
        return isDisplayed(loginErrorMessage);
    }

    @Step("Get login error message text")
    public String getLoginErrorMessage() {
        return getText(loginErrorMessage);
    }
}