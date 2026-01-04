@orangeHRM
Feature: Login Functionality for Orange HRM Website

  As a user of the Orange HRM website
  I should be able to log in to my account with valid credentials
  So that I can access my account-related features

  Background:
    Given I open the OrangeHRM login page

  @smoke @TC001
  Scenario: Successful login with the valid credentials
    Given I have entered valid username and password
    When I click on the login button
    Then I should be logged in successfully
    Then I close the browser

  @smoke @TC002
  Scenario: Login with a single set of credentials
    When I enter following credentials
      | username |  password  |
      | user1    | pass123    |
    Then I should see an error message "Invalid credentials"
    Then I close the browser

  @regression @TC003
  Scenario Outline: Unsuccessful login with invalid credentials
    Given I have entered invalid "<username>" and "<password>"
    When  I click on the login button
    Then  I should see an error message indicating "<error_message>"
    Then I close the browser

    Examples:
      | username    | password    | error_message       |
      | Admin       | admin123456 | Invalid credentials |
      | AdminPerson | admin123    | Invalid credentials |
      | AdminPerson | admin123456 | Invalid credentials |

  @smoke @regression @sanity @TC004
  Scenario: Navigating to the forgotten password page
    When I click on the "Forgot your password?" link
    Then I should be redirected to the password reset page
    Then I close the browser