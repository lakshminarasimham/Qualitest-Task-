package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.AddToCartPO;
import pageObjects.HomePO;
import pageObjects.WishListPO;
import utilities.Constants;
import utilities.SeleniumHelpers;
import utilities.WebDriverManager;

public class SampleSteps {
    private final WebDriver driver = WebDriverManager.getDriver();
    private SeleniumHelpers selenium = new SeleniumHelpers(driver);
    private HomePO home = new HomePO(driver);
    private WishListPO wishList = new WishListPO(driver);
    private AddToCartPO addToCart = new AddToCartPO(driver);
    public static int x;


    @Given("I add four different products to my wishlist")
    public void i_add_four_different_products_to_my_wishlist() throws InterruptedException {
        selenium.navigateToPage(Constants.URL);
        home.addProductInToWishListByProductName(Constants.FirstProduct);
        home.addProductInToWishListByProductName(Constants.SecondProduct);
        home.addProductInToWishListByProductName(Constants.ThirdProduct);
        home.addProductInToWishListByProductName(Constants.FourthProduct);

    }
    @When("I view my wishlist table")
    public void i_view_my_wishlist_table() throws InterruptedException {
        home.clickOnWishlistButton();
    }
    @Then("I find my total four selected items in my wishlist")
    public void i_find_my_total_four_selected_items_in_my_wishlist() throws InterruptedException {
        wishList.isProductPresent(Constants.FirstProduct);
        wishList.isProductPresent(Constants.SecondProduct);
        wishList.isProductPresent(Constants.ThirdProduct);
        wishList.isProductPresent(Constants.FourthProduct);
    }
    @When("I search for lowest price product")
    public void i_search_for_lowest_price_product() {
       this.x =  wishList.findLowestPrice();
    }
    @When("I am able to add the lowest price item to my cart")
    public void i_am_able_to_add_the_lowest_price_item_to_my_cart() throws InterruptedException {
        wishList.clickOnAddToCartButton(x);
    }
    @Then("I am able to verify the item in my cart")
    public void i_am_able_to_verify_the_item_in_my_cart() throws InterruptedException {
        wishList.clickOnCartButton();
        String s=Integer.toString(x);
        Assert.assertTrue(addToCart.isProductAdded(s));
    }

}
