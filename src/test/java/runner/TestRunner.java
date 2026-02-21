package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepdefinitions","browser"},
        plugin = {"html:target/cucumber-reports.html","json:target/cucumber.json", "pretty"},
        tags = "(@smoke or @regression) and not (@wip or @ignore)")

public class TestRunner extends AbstractTestNGCucumberTests {

}


