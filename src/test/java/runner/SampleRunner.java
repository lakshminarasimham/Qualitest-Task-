package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"json:target/cucumber-report.json", "html:target/cucumber"},
        features = "src/test/resources/features/sample.feature",
        glue = "steps",
        tags = "@test"
)
public class SampleRunner extends AbstractTestNGCucumberTests {
}
