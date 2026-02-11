package ui;
import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

@Epic("Product Management")
@Feature("Product Listing")
class VerifyProductsTest extends BaseTest {

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    @Description("Verify that products page displays all products correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("As a user, I want to view all available products")
    public void testVerifyAllProducts() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();

        // Verify user is navigated to ALL PRODUCTS page
        Assert.assertTrue(productsPage.isAllProductsVisible(),
                "ALL PRODUCTS section is not visible");

        // Verify products list is visible
        Assert.assertTrue(productsPage.isProductsListVisible(),
                "Products list is not visible");

        // Note: You can add more verification for product details
        // by clicking on individual products and verifying their details page
    }
}