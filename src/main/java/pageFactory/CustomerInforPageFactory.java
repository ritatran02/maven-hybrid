package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerInforPageFactory extends BasePage {
    private WebDriver driver;

    public CustomerInforPageFactory(WebDriver driver){
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
    private WebElement companyNameTextbox;
    public boolean isGenderFemaleSelected() {
        waitForElementVisible(driver, genderFemaleRadio);
        return isElementSelected(genderFemaleRadio);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, firstNameTextbox);
        return getElementAttribute(firstNameTextbox,"value");
    }

    public String  getLastNameTextboxValue() {
        waitForElementVisible(driver, lastNameTextbox);
        return getElementAttribute(lastNameTextbox,"value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(driver, companyNameTextbox);
        return getElementAttribute(companyNameTextbox,"value");
    }
}
