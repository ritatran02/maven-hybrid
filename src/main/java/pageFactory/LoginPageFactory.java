package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageFactory extends BasePage {
    private WebDriver driver;
    public LoginPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//button[contains(@class,'login-button')]")
    private WebElement loginButton;

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToEElement(emailTextbox, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToEElement(passwordTextbox, password);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(loginButton);
    }
}

