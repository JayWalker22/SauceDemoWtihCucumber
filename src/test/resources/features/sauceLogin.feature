
Feature: User Login Test
  @sauce
  Scenario: Login with valid credentials
    Given The user navigates to the login page
    When The user enters valid username and password
    Then The user should be redirected to the homepage
  @sauce
  Scenario: User attempts to log in with incorrect ID and password
    Given The user navigates to the login page
    When The user attempts to log in with credentials from the "negative" sheet in the Excel file
    Then The user should not be able to log in and should see an error message