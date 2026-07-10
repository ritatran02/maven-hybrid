package com.nopcommerce.user;

import commons.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;

public class Level_06_Page_Generator extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInforPO customerInfoPage; // User-Defined
    private String firstName, lastName, emailAddress, companyName, password, confirmPassword;

    // Pre-Condition
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        //driver = getBrowserDriver(browserName); // BuiltIn

        //homePage = UserPO.getHomePage(driver);

        firstName = "Tran";
        lastName = "Rita";
        emailAddress = "ritatran" + generateRandomNumber() + "@gmail.com";
        companyName = "Yoo";
        password = "Ngan@123";
        confirmPassword = "Ngan@123";
    }

    @Description("User_TC_01_Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void User_TC_01_Register(){
        registerPage = homePage.clickToRegisterLink();

        registerPage.clickToFemaleRatio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyNameTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(confirmPassword);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");



    }

    @Description("User_TC_02_Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void User_TC_02_Login(){
        homePage = registerPage.clickToLogoutLink();
        loginPage = registerPage.clickToLoginLink();

        homePage = loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Description("User_TC_03_My_Account")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_TC_03_My_Account(){
        customerInfoPage = homePage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderFemaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
    }

    // Post-Condition
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void loginToSystem(String emailAddress, String password){
        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();
    }
}
