package ui;
import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
@Epic("Product Management")
@Feature("Product Search")
class SearchProductTest extends BaseTest {

    @Test(description = "Test Case 9: Search Product")
    @Description("Verify that product search functionality works correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a user, I want to search for specific products")
    public void testSearchProduct() {
        String searchTerm = "top";

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();

        // Verify ALL PRODUCTS is visible
        Assert.assertTrue(productsPage.isAllProductsVisible(),
                "ALL PRODUCTS section is not visible");

        // Search for product
        productsPage.searchProduct(searchTerm);

        // Verify SEARCHED PRODUCTS is visible
        Assert.assertTrue(productsPage.isSearchedProductsVisible(),
                "SEARCHED PRODUCTS section is not visible");

        // Note: You can add verification that all visible products
        // are related to the search term
    }
}
