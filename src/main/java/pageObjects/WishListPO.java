package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishListPO {

    WebDriver driver;
    SeleniumHelpers selenium;

    public WishListPO(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }

    @FindBy(css = ".wishlist_table tbody tr td.product-price :nth-child(2) bdi")
    private List<WebElement> productPrice;

    @FindBy(css = ".wishlist_table tbody tr td.product-add-to-cart")
    private List<WebElement> addToCartButton;

    @FindBy(css = ".heading-row .header-cart .la-shopping-bag")
    private WebElement cartButton;

    public boolean isProductPresent(String productName) throws InterruptedException {
        String xPath = "//a[contains(text(),'" + productName + "')]";
        return selenium.isElementPresent(By.xpath(xPath));

    }

    public int findLowestPrice() {
        ArrayList<Integer> prices = new ArrayList<Integer>();
        for (int i = 0; i < productPrice.size(); i++) {
            Integer priceInt = Integer.valueOf(productPrice.get(i).getText().replace(",", "").replace("£", "").replace(".00", ""));
            prices.add(priceInt);
        }
        Integer minPrice = Collections.min(prices);
        System.out.println(minPrice);
        return minPrice;
    }

    public void clickOnAddToCartButton(int minPrice) throws InterruptedException {
        for (int i = 0; i < productPrice.size(); i++) {
            Integer priceInt1 = Integer.valueOf(productPrice.get(i).getText().replace(",", "").replace("£", "").replace(".00", ""));

            System.out.println(priceInt1);
            if (priceInt1 == minPrice) {
                addToCartButton.get(i).click();
                selenium.hardWait(3);
                break;
            }
        }

    }

    public void clickOnCartButton() throws InterruptedException {
        selenium.clickOn(cartButton);
    }
}
