package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class SeleniumHelpers
{
    WebDriver driver;
    JavaHelpers helper;
    Actions actions;

    public SeleniumHelpers(WebDriver driver)
    {
        this.driver = driver;
        helper = new JavaHelpers();
        actions = new Actions(driver);
    }

    // Take screenshot
    /**
     * Take screenshot of the web page
     *
     * @param fileName screenshot file name
     * @throws IOException
     */
    public void takeScreenshot(String fileName) throws IOException
    {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scrFile,
                new File(Constants.SCREENSHOT_LOCATION + fileName + helper.getTimeStamp("_yyyyMMdd_HHmmss") + ".png"));
    }



    /**
     * Get Text from field
     * @param e WebElement object
     * @return text from field
     */
    public String getText(WebElement e)
    {
        return waitTillElementIsVisible(e).getText().trim();
    }

    public String getText(By object)
    {
        return driver.findElement(object).getText();
    }

    /**
     * Click on Element
     * @param e WebElement object
     * @throws InterruptedException
     */
    public void clickOn(WebElement e) throws InterruptedException
    {
        waitTillElementIsClickable(e).click();
        waitForJavascriptToLoad();
    }

    /**
     * Click on Element
     * @param e WebElement object
     * @throws InterruptedException
     */
    public void click(WebElement e) throws InterruptedException
    {
        e.click();
        waitForJavascriptToLoad();
    }

    /**
     * Click on Element
     * @param by By object
     * @throws InterruptedException
     */
    public void clickOn(By by) throws InterruptedException
    {
        waitTillElementIsClickable(by).click();
        waitForJavascriptToLoad();
    }

    /**
     * To determine whether WebElement has given Attribute or not
     * @param e WebElement object
     * @param attributeName attribute name e.g. style
     * @return boolean
     */
    public boolean isElementAtrributePresent(WebElement e, String attributeName)
    {
        return e.getAttribute(attributeName) != null;
    }

    /**
     * To get Element attribute value
     * @param e WebElement object
     * @param attributeName attribute name e.g. style
     * @return attribute value
     */
    public String getElementAttributeValue(WebElement e, String attributeName)
    {
        if(isElementAtrributePresent(e,attributeName))
        {
            return e.getAttribute(attributeName);
        }
        return "Attribute" + attributeName +" not found";
    }

    /**
     * method verify whether element is present on screen
     *
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or
     *                              otherwise occupied, and the thread is
     *                              interrupted, either before or during the
     *                              activity.
     */
    public Boolean isElementPresent(By targetElement) throws InterruptedException
    {
        return driver.findElements(targetElement).size() > 0;
    }


    //Waits
    /**
     * To wait until element is clickable
     * @param e WebElement object
     * @return WebElement object
     */
    public WebElement waitTillElementIsClickable(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.elementToBeClickable(e));
        return e;
    }

    /**
     * To wait until element is clickable
     * @param by By object
     * @return WebElement object
     */
    public WebElement waitTillElementIsClickable(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * o wait until element is visible
     * @param e WebElement object
     * @return WebElement object
     */
    public WebElement waitTillElementIsVisible(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.visibilityOf(e));
        return e;
    }
    /**
     * o wait until element is visible
     * @param e WebElement object
     * @param waitDurationInSeconds wait duration in seconds
     * @return WebElement object
     */
    public WebElement waitTillElementIsVisible(WebElement e, int waitDurationInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
        wait.until(ExpectedConditions.visibilityOf(e));
        return e;
    }

    /**
     * o wait until element is visible
     * @param by By object
     * @param waitDurationInSeconds wait duration in seconds
     * @return WebElement object
     */
    public void waitTillElementIsVisible(By by, int waitDurationInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait until element is invisible
     * @param e WebElement object
     */
    public void waitTillElementIsNotVisible(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.invisibilityOf(e));
    }

    /**
     * Wait until element is invisible
     * @param by By object
     */
    public void waitTillElementIsNotVisible(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Wait until element is invisible
     * @param e WebElement object
     * @param waitDurationInSeconds wait duration in seconds
     */
    public void waitTillElementIsNotVisible(WebElement e,int waitDurationInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
        wait.until(ExpectedConditions.invisibilityOf(e));
    }

    /**
     * Wait until element is invisible
     * @param waitDurationInSeconds wait duration in seconds
     * @param by By object
     */
    public void waitTillElementIsNotVisible(By by, int waitDurationInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,  waitDurationInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Wait for specified duration and check if element is visible or not
     * @param e WebElement object
     * @param duration wait duration in seconds
     * @return WebElement if visible or null if not visible
     */
    public WebElement waitInCaseElementVisible(WebElement e, int duration)
    {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        try
        {
            return wait.until(ExpectedConditions.visibilityOf(e));
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * Wait for specific duration and check if element present or not
     * @param e WebElement object
     * @param duration wait duration in seconds
     * @return WebElement if presesnt null if not visible
     */
    public WebElement waitInCaseElementPresent(By e, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        try{
            return wait.until(ExpectedConditions.presenceOfElementLocated(e));
        }
        catch (Exception ex) {
            return null;
        }
    }

    /**
     * Wait for specified duration and check if element is visible or not
     * @param by By object
     * @param duration wait duration in seconds
     * @return WebElement if visible or null if not visible
     */
    public WebElement waitInCaseElementVisible(By by, int duration)
    {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        try
        {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * Wait for specified duration and check if element is clickable or not
     * @param e WebElement object
     * @param duration wait duration in seconds
     * @return WebElement if clickable or null if not visible
     */
    public WebElement waitInCaseElementClickable(WebElement e, int duration)
    {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        try
        {
            return wait.until(ExpectedConditions.elementToBeClickable(e));
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * Wait till Element count is less than provided number
     * @param by By object
     * @param count provide number
     */
    public void waitTillElementsCountIsLessThan(By by, int count)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(by, count));
    }

    /**
     * Wait till Element count is more than provided number
     * @param by By object
     * @param count provide number
     */
    public void waitTillElementsCountIsMoreThan(By by, int count)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, count));
    }

    /**
     * Wait till Element count is equal to provided number
     * @param by By object
     * @param count provide number
     */
    public void waitTillElementsCountIsEqualTo(By by, int count)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.numberOfElementsToBe(by, count));
    }

    /**
     * Wait till frame is available for switching
     * @param e WebElement object
     */
    public void waitTillframeToBeAvailableAndSwitchToIt(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(e));
    }

    /**
     * Wait till element not attached to DOM
     * @param e WebElement object
     */
    public void waitTillElementNotAttachedToDOM(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.stalenessOf(e));
    }

    /**
     * Waiting before performing next action
     * @param seconds provide duration e.g. 1,2 etc
     * @throws InterruptedException
     */
    public void hardWait(int seconds) throws InterruptedException
    {
        Thread.sleep(seconds * 1000);
    }

    /**
     * Wait till all elements are located
     * @param by By object
     * @return List of WebElement
     */
    public List<WebElement> waitTillAllElementsAreLocated(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * Wait till element is refreshed
     * @param e WebElement object
     * @return WebElement object
     */
    public WebElement waitTillElementIsRefreshed(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(e)));
    }

    public  void waitForJavascriptToLoad() throws InterruptedException
    {
        Thread.sleep(1000);
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver driver)
            {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait <WebDriver> wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        try
        {
            wait.until(expectation);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        catch(Error e)
        {
            e.printStackTrace();
        }
    }


    //Navigation
    public void navigateToPage(String url)
    {
        driver.get(url);
    }

    public void refreshPage()
    {
        driver.navigate().refresh();
    }

    public void back()
    {
        driver.navigate().back();
    }

    public String getURL()
    {
        return driver.getCurrentUrl();
    }


    //Alerts
    public void waitTillAlertPresent()
    {
        WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void dismissAlert()
    {
        driver.switchTo().alert().dismiss();
    }

    public void acceptAlert()
    {
        driver.switchTo().alert().accept();
    }

    /**
     * Switch to alert
     */
    public void switchToAlert()
    {
        driver.switchTo().alert();
    }

    public String getTextFromAlert()
    {
        return driver.switchTo().alert().getText().trim();
    }


    //Drop-down
    public void selectDropdownValueByText(WebElement e, String text)
    {
        new Select(e).selectByVisibleText(text);
    }

    public String getSelectedDropdownValue(WebElement e)
    {
        return new Select(e).getFirstSelectedOption().getText();
    }

    public String selectDropdownValueByIndex(WebElement e, int index)
    {
        new Select(e).selectByIndex(index);
        return new Select(e).getFirstSelectedOption().getText().trim();
    }

    public List<String> getAllDropdownValues(WebElement e)
    {
        List<String> dropdownvalues = new ArrayList<String>();
        List<WebElement> list = new Select(e).getOptions();

        for(WebElement item :list)
        {
            dropdownvalues.add(item.getText().trim());
        }

        return dropdownvalues;
    }


}

