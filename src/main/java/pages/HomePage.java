package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Home Page
 */
public class HomePage extends BasePage {

    // Locators
    private final By signupLoginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By logoutLink = By.xpath("//a[contains(text(),'Logout')]");
    private final By deleteAccountLink = By.xpath("//a[contains(text(),'Delete Account')]");
    private final By contactUsLink = By.xpath("//a[contains(text(),'Contact us')]");
    private final By productsLink = By.xpath("//a[contains(@href,'/products')]");
    private final By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    private final By loggedInUser = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By homeLink = By.xpath("//a[text()=' Home']");
    private final By subscriptionSection = By.id("footer");
    private final By subscriptionTitle = By.xpath("//h2[text()='Subscription']");
    private final By emailInput = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By successMessage = By.className("alert-success");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify home page is loaded")
    public boolean isHomePageLoaded() {
        return isDisplayed(homeLink);
    }

    @Step("Click on Signup/Login link")
    public SignupLoginPage clickSignupLogin() {
        click(signupLoginLink);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Logout link")
    public void clickLogout() {
        click(logoutLink);
    }

    @Step("Click on Delete Account link")
    public AccountDeletedPage clickDeleteAccount() {
        click(deleteAccountLink);
        return new AccountDeletedPage(driver);
    }

    @Step("Click on Contact Us link")
    public ContactUsPage clickContactUs() {
        click(contactUsLink);
        return new ContactUsPage(driver);
    }

    @Step("Click on Products link")
    public ProductsPage clickProducts() {
        click(productsLink);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart link")
    public CartPage clickCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    @Step("Verify user is logged in as '{username}'")
    public boolean isUserLoggedIn(String username) {
        String text = getText(loggedInUser);
        return text.contains(username);
    }

    @Step("Get logged in username")
    public String getLoggedInUsername() {
        return getText(loggedInUser);
    }

    @Step("Scroll to subscription section")
    public void scrollToSubscription() {
        scrollToElement(subscriptionSection);
    }

    @Step("Verify subscription section is visible")
    public boolean isSubscriptionVisible() {
        return isDisplayed(subscriptionTitle);
    }

    @Step("Enter email in subscription field: {email}")
    public void enterSubscriptionEmail(String email) {
        type(emailInput, email);
    }

    @Step("Click subscribe button")
    public void clickSubscribe() {
        click(subscribeButton);
    }

    @Step("Verify success message is displayed")
    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    @Step("Get success message text")
    public String getSuccessMessage() {
        return getText(successMessage);
    }

    @Step("Scroll to top of page")
    public void scrollToTop() {
        super.scrollToTop();
    }

    @Step("Scroll to bottom of page")
    public void scrollToBottom() {
        super.scrollToBottom();
    }
}