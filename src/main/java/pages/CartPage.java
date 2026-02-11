package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class CartPage extends BasePage {

    private final By shoppingCartTitle = By.xpath("//li[@class='active' and text()='Shopping Cart']");
    private final By proceedToCheckoutButton = By.xpath("//a[text()='Proceed To Checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify Shopping Cart page is loaded")
    public boolean isCartPageLoaded() {
        return isDisplayed(shoppingCartTitle);
    }

    @Step("Click Proceed To Checkout")
    public void clickProceedToCheckout() {
        click(proceedToCheckoutButton);
    }
}
