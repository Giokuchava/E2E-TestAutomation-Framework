package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object for Signup Form Page
 */
public class SignupPage extends BasePage {

    // Locators
    private final By enterAccountInfoTitle = By.xpath("//b[text()='Enter Account Information']");
    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");
    private final By passwordInput = By.id("password");
    private final By dayDropdown = By.id("days");
    private final By monthDropdown = By.id("months");
    private final By yearDropdown = By.id("years");
    private final By newsletterCheckbox = By.id("newsletter");
    private final By specialOffersCheckbox = By.id("optin");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyInput = By.id("company");
    private final By address1Input = By.id("address1");
    private final By address2Input = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[text()='Create Account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ENTER ACCOUNT INFORMATION' is visible")
    public boolean isEnterAccountInfoVisible() {
        return isDisplayed(enterAccountInfoTitle);
    }

    @Step("Select title: {title}")
    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            click(titleMr);
        } else {
            click(titleMrs);
        }
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    @Step("Select date of birth: {day}/{month}/{year}")
    public void selectDateOfBirth(String day, String month, String year) {
        Select daySelect = new Select(driver.findElement(dayDropdown));
        daySelect.selectByValue(day);

        Select monthSelect = new Select(driver.findElement(monthDropdown));
        monthSelect.selectByValue(month);

        Select yearSelect = new Select(driver.findElement(yearDropdown));
        yearSelect.selectByValue(year);
    }

    @Step("Select newsletter checkbox")
    public void selectNewsletter() {
        click(newsletterCheckbox);
    }

    @Step("Select special offers checkbox")
    public void selectSpecialOffers() {
        click(specialOffersCheckbox);
    }

    @Step("Enter first name: {firstName}")
    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    @Step("Enter company: {company}")
    public void enterCompany(String company) {
        type(companyInput, company);
    }

    @Step("Enter address 1: {address}")
    public void enterAddress1(String address) {
        type(address1Input, address);
    }

    @Step("Enter address 2: {address}")
    public void enterAddress2(String address) {
        type(address2Input, address);
    }

    @Step("Select country: {country}")
    public void selectCountry(String country) {
        Select countrySelect = new Select(driver.findElement(countryDropdown));
        countrySelect.selectByVisibleText(country);
    }

    @Step("Enter state: {state}")
    public void enterState(String state) {
        type(stateInput, state);
    }

    @Step("Enter city: {city}")
    public void enterCity(String city) {
        type(cityInput, city);
    }

    @Step("Enter zipcode: {zipcode}")
    public void enterZipcode(String zipcode) {
        type(zipcodeInput, zipcode);
    }

    @Step("Enter mobile number: {mobileNumber}")
    public void enterMobileNumber(String mobileNumber) {
        type(mobileNumberInput, mobileNumber);
    }

    @Step("Click Create Account button")
    public AccountCreatedPage clickCreateAccount() {
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }

    @Step("Fill complete signup form")
    public AccountCreatedPage fillCompleteSignupForm(String password, String day, String month,
                                                     String year, String firstName, String lastName,
                                                     String company, String address1, String address2,
                                                     String country, String state, String city,
                                                     String zipcode, String mobileNumber) {
        selectTitle("Mr");
        enterPassword(password);
        selectDateOfBirth(day, month, year);
        selectNewsletter();
        selectSpecialOffers();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompany(company);
        enterAddress1(address1);
        enterAddress2(address2);
        selectCountry(country);
        enterState(state);
        enterCity(city);
        enterZipcode(zipcode);
        enterMobileNumber(mobileNumber);
        return clickCreateAccount();
    }
}