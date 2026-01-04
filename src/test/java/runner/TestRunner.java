package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepdefinitions", "browser"},
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"},
        tags = "(@smoke or @regression) and not (@ignore or @wip)")

public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}