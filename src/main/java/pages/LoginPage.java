package pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginPage {
    private WebDriver driver;
    private String oragneHRMurl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    // Locators
    @FindBy(name = "username")
    private WebElement userName;
    @FindBy(name = "password")
    private WebElement passWord;
    @FindBy(tagName = "button")
    private WebElement loginButton;
    @FindBy(xpath = "//p[text()='Invalid credentials']")
    private WebElement invalidCredentials;
    @FindBy(xpath = "//p[text()='Forgot your password? ']")
    private WebElement forgotYourPassword;

    //Constructor
    public LoginPage()
    {
        String desiredBrowser = System.getProperty("browser", "chrome");
        boolean runOnGrid = Boolean.parseBoolean(System.getProperty("runOnGrid", "false"));
        switch (desiredBrowser)
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if(runOnGrid)
                {
                    chromeOptions.setCapability("platformName", "WINDOWS");

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
                    firefoxOptions.setCapability("platformName", "WINDOWS");

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
        PageFactory.initElements(driver, this);  // Initializes all @FindBy
    }

    // Actions
    public void openLoginPage()
    {
        driver.get(oragneHRMurl);
        customWait();
    }

    public void enterUserName(String user)
    {
        customWait();
        userName.sendKeys(user);
    }

    public void enterPassword(String pass)
    {
        customWait();
        passWord.sendKeys(pass);
    }

    public void clickOnLoginButton()
    {
        loginButton.click();
        customWait();
    }

    public void clickOnForgotYourPassword()
    {
        customWait();
        forgotYourPassword.click();
    }

    public boolean isinvalidCredentialsDisplayed()
    {
        customWait();
        return invalidCredentials.isDisplayed();
    }

    public String getInvalidCredentialsMessage()
    {
        return invalidCredentials.getText().trim();
    }

    public String currentURL()
    {
        customWait();
        return driver.getCurrentUrl();
    }

    public void closeBrowser()
    {
        driver.close();
    }

    public void customWait()
    {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("Some thing went wrong");
            e.printStackTrace();
        }
    }

    // Business Method
    public void login(String user, String pass)
    {
        enterUserName(user);
        enterPassword(pass);
        clickOnLoginButton();
    }
}
