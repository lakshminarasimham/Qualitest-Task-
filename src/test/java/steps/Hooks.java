package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.WebDriverManager;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws Exception {
        WebDriverManager.setUp("chrome");
    }


    @After
    public static void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshotBytes = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", scenario.getName());
            }
        } catch (Exception e) {
            //No action needed
        }
        WebDriverManager driverManager = new WebDriverManager();
        driverManager.tearDown();

    }
}
