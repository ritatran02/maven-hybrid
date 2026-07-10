package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;

public class Level_08_Page_Navigation extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInforPO customerInfoPage; // User-Defined
    private UserAddressPO addressPage;
    private UserRewardPointPO rewardPointPage;
    private UserOrderPO orderPage;
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

    @Test
    public void User_TC_02_Login(){
        homePage = registerPage.clickToLogoutLink();
        loginPage = registerPage.clickToLoginLink();

        homePage = loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_TC_03_My_Account(){
        customerInfoPage = homePage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderFemaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
    }

    @Test
    public void User_TC_04_Switch_Page(){
        // Customer Infor => Address
        addressPage = customerInfoPage.openAddressPage(driver);

        // Address => Reward Point
        rewardPointPage = addressPage.openRewardPointPage(driver);

        // Reward Point => Order
        orderPage = rewardPointPage.openOrderPage(driver);

        // Order => Address
        addressPage = orderPage.openAddressPage(driver);

        // Address => Customer Infor
        customerInfoPage = addressPage.openCustomerInfoPage(driver);

        rewardPointPage = customerInfoPage.openRewardPointPage(driver);

        addressPage = rewardPointPage.openAddressPage(driver);
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
