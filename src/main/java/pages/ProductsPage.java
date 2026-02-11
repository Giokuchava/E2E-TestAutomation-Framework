package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ProductsPage extends BasePage {

    private final By allProductsTitle = By.xpath("//h2[text()='All Products']");
    private final By productsList = By.className("features_items");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsTitle = By.xpath("//h2[text()='Searched Products']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ALL PRODUCTS' is visible")
    public boolean isAllProductsVisible() {
        return isDisplayed(allProductsTitle);
    }

    @Step("Verify products list is visible")
    public boolean isProductsListVisible() {
        return isDisplayed(productsList);
    }

    @Step("Search for product: {productName}")
    public void searchProduct(String productName) {
        type(searchInput, productName);
        click(searchButton);
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public boolean isSearchedProductsVisible() {
        return isDisplayed(searchedProductsTitle);
    }
}
