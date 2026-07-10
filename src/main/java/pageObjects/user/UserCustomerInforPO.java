package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageUIs.user.UserCustomerInfoPageUI;

public class UserCustomerInforPO extends UserSidebarPO {
    private WebDriver driver;

    public UserCustomerInforPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean isGenderFemaleSelected() {
        waitForElementSelected(driver, UserCustomerInfoPageUI.GENDER_FEMALE_RADIO);
        return isElementSelected(driver, UserCustomerInfoPageUI.GENDER_FEMALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX,"value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX,"value");
    }
}
