package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;

public class HomePO {

    WebDriver driver;
    SeleniumHelpers selenium;

    public HomePO(WebDriver driver)
    {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(css = ".heading-row .header-wishlist a")
    private WebElement wishListButton;


    public void addProductInToWishListByProductName(String productName) throws InterruptedException {
        String xpath = "//ul[@class='products columns-5']//h2[text()='"+productName+"']/parent::*/following::span[text()='Add to wishlist'][1]";
        selenium.clickOn(By.xpath(xpath));
    }

    public void clickOnWishlistButton() throws InterruptedException {
        selenium.clickOn(wishListButton);
    }

}
