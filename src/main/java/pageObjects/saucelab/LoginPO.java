package pageObjects.saucelab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.saucelab.LoginPageUI;

public class LoginPO  extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver){
        this.driver = driver;
    }

    public ProductPO loginToSauce(String userName, String password) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
        sendKeyToElement(driver, LoginPageUI.USERNAME_PASSWORD, password);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getProductPage(driver);
    }
}
