package browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.time.Duration;
public class BrowserSetupAndTeardown {
    @Before
    public void setUp() {
        BrowserFactory.getDriver().manage().window().maximize();
        BrowserFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("========== Browser launched ==========");
    }

    @After
    public void tearDown() {
        BrowserFactory.quitDriver();
        System.out.println("========== Browser closed ==========");
    }
}