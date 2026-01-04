package stepdefinitions;

import io.cucumber.datatable.DataTable;
import pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.util.List;

public class LoginPageStepDefinition {
    private LoginPage loginPage;

    //Constructor
    public LoginPageStepDefinition()
    {
        loginPage = new LoginPage();
    }

    @Given("I open the OrangeHRM login page")
    public void i_open_the_orange_hrm_login_page()
    {
        loginPage.openLoginPage();
    }

    @Given("I have entered valid username and password")
    public void i_have_entered_valid_username_and_password()
    {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
    }

    @Given("I have entered invalid {string} and {string}")
    public void i_have_entered_invalid_and(String username, String password)
    {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button()
    {
        loginPage.clickOnLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully()
    {
        Assert.assertEquals(loginPage.currentURL(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Then("I should see an error message indicating {string}")
    public void i_should_see_an_error_message_indicating(String errorMessage)
    {
        Assert.assertEquals(loginPage.isinvalidCredentialsDisplayed(), true);
    }

    @When("I click on the \"Forgot your password?\" link")
    public void i_click_on_the_forgotten_password_link()
    {
        loginPage.clickOnForgotYourPassword();
    }

    @Then("I should be redirected to the password reset page")
    public void i_should_be_redirected_to_the_password_reset_page()
    {
        Assert.assertEquals(loginPage.currentURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");
    }

    @When("I enter following credentials")
    public void i_enter_following_credentials(DataTable dataTable)
    {
        // Convert DataTable to a List of List of Strings
        List<List<String>> data = dataTable.asLists(String.class);

        // Access the first row and the values by index
        String username = data.get(1).get(0);  // First row's "username" column
        String password = data.get(1).get(1);  // First row's "password" column

        // Perform actions with the retrieved data
        loginPage.login(username, password);
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedMessage)
    {
        Assert.assertEquals(loginPage.getInvalidCredentialsMessage(), expectedMessage);
    }

    @Then("I should be on Login Page")
    public void i_should_see_on_login_page()
    {
        Assert.assertEquals(loginPage.currentURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Then("I close the browser")
    public void i_close_the_browser()
    {
        loginPage.closeBrowser();
    }
}
