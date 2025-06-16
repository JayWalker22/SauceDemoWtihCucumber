package stepDefinitions;

import io.cucumber.java.en.*;
import pages.LoginTestPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.ExcelUtilities;
import utilities.ReusableMethods;
import static org.junit.Assert.assertTrue;

public class LoginTestStepDef {
    LoginTestPage page = new LoginTestPage();

    @Given("The user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("sauce"));
    }
    @When("The user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {
        page.userName.sendKeys(ConfigurationReader.getProperty("userName"));
        page.password.sendKeys(ConfigurationReader.getProperty("password"));
        ReusableMethods.bekle(5);
        page.loginButton.click();
        ReusableMethods.bekle(5);

    }
    @Then("The user should be redirected to the homepage")
    public void the_user_should_be_redirected_to_the_homepage() {
        assertTrue("Expected condition not met",page.mainPageTitle.isDisplayed());
    }
    @When("The user attempts to log in with credentials from the {string} sheet in the Excel file")
    public void the_user_attempts_to_log_in_with_credentials_from_the_sheet_in_the_excel_file(String string) {
        ExcelUtilities excelUtilities = new ExcelUtilities("src/test/resources/userData (1).xlsx",string);
        for (int i = 1; i <= excelUtilities.rowCount(); i++) {
            String userName = excelUtilities.getCellData(i,0);
            String password = excelUtilities.getCellData(i,1);

            page.userName.clear();
            page.userName.sendKeys(userName);

            ReusableMethods.bekle(2);

            page.password.clear();
            page.password.sendKeys(password);

            ReusableMethods.bekle(2);

            page.loginButton.click();
        }
    }

    @Then("The user should not be able to log in and should see an error message")
    public void the_user_should_not_be_able_to_log_in_and_should_see_an_error_message() {
        assertTrue(page.invalidLoginError.isDisplayed());
    }

}
