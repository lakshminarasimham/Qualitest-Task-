package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;

public class AddToCartPO {
    WebDriver driver;
    SeleniumHelpers selenium;

    public AddToCartPO(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }

    public boolean isProductAdded(String price) throws InterruptedException {
        String xpath = "//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']//tbody/tr//bdi[contains(text(),'"+price+"')]";
        return selenium.isElementPresent(By.xpath(xpath));
    }

}
