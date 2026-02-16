package browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    static WebDriver driver;

    // Get the WeDriver instance
    public static WebDriver getDriver()
    {
        if (driver == null)
        {
            String desiredBrowser = System.getProperty("browser", "chrome");
            String desiredOS=System.getProperty("os", "WINDOWS");
            boolean runOnGrid = Boolean.parseBoolean(System.getProperty("runOnGrid", "false"));
            switch (desiredBrowser)
            {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.password_manager_leak_detection", false);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    if(runOnGrid)
                    {
                        chromeOptions.setCapability("platformName", desiredOS);

                        // Create RemoteWebDriver session with Grid
                        try
                        {
                            driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
                        }
                        catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else
                    {
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(chromeOptions);
                    }
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized");
                    if(runOnGrid)
                    {
                        firefoxOptions.setCapability("platformName", desiredOS);

                        // Create RemoteWebDriver session with Grid
                        try
                        {
                            driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
                        }
                        catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else
                    {
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver(firefoxOptions);
                    }
                    break;
            }
        }
        return driver;
    }

    // Quit the WeDriver instance
    public static void quitDriver()
    {
        driver.quit();
        driver = null;
    }
}
