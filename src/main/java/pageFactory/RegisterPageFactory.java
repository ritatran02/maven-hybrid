package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageFactory extends BasePage {
    private WebDriver driver;

    public RegisterPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-female")
    private WebElement genderFemaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailAddressTextbox;

    @FindBy(id = "Company")
    private WebElement companyTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement registerSuccessMessage;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    @FindBy(className = "ico-login")
    private WebElement loginLink;

    public void clickToFemaleRatio() {
        waitForElementClickable(driver, genderFemaleRadio);
        clickToElement(genderFemaleRadio);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToEElement(firstNameTextbox, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToEElement(lastNameTextbox, lastName);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailAddressTextbox);
        sendKeyToEElement(emailAddressTextbox, emailAddress);
    }

    public void enterToCompanyNameTextbox(String companyName) {
        waitForElementVisible(driver, companyTextbox);
        sendKeyToEElement(companyTextbox, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToEElement(passwordTextbox, password);
    }

    public void enterToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToEElement(confirmPasswordTextbox, confirmPassword);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(registerButton);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getElementText(registerSuccessMessage);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driver, logoutLink);
        clickToElement(logoutLink);
    }

    public void clickToLoginLink() {
        waitForElementClickable(driver, loginLink);
        clickToElement(loginLink);
    }
}
