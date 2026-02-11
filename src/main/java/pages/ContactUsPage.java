package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ContactUsPage extends BasePage {

    private final By getInTouchTitle = By.xpath("//h2[text()='Get In Touch']");
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By subjectInput = By.name("subject");
    private final By messageTextarea = By.id("message");
    private final By uploadFileInput = By.name("upload_file");
    private final By submitButton = By.name("submit");
    private final By successMessage = By.xpath("//div[contains(@class,'alert-success')]");
    private final By homeButton = By.xpath("//a[contains(@class,'btn-success')]");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'GET IN TOUCH' is visible")
    public boolean isGetInTouchVisible() {
        return isDisplayed(getInTouchTitle);
    }

    @Step("Fill contact form")
    public void fillContactForm(String name, String email, String subject, String message) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageTextarea, message);
    }

    @Step("Upload file: {filePath}")
    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    @Step("Click Submit button")
    public void clickSubmit() {
        click(submitButton);
        acceptAlert();
    }

    @Step("Verify success message is displayed")
    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    @Step("Click Home button")
    public HomePage clickHome() {
        click(homeButton);
        return new HomePage(driver);
    }
}