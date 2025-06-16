package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginTestPage {
    public LoginTestPage () {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(css = "input[placeholder='Username']")
    public WebElement userName;
    @FindBy (css = "input[placeholder='Password']")
    public WebElement password;
    @FindBy (id = "login-button")
    public WebElement loginButton;
    @FindBy (xpath = "//div[@class='app_logo']")
    public WebElement mainPageTitle;
    @FindBy (css = "div[class='error-message-container error']")
    public WebElement invalidLoginError;
}
