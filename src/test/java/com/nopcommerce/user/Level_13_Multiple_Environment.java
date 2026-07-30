package com.nopcommerce.user;

import commons.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import jiraConfigs.JiraCreateIssue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.user.*;
import utilities.DataConfigNet;
import utilities.PropertiesConfig;

public class Level_13_Multiple_Environment extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInforPO customerInfoPage; // User-Defined
    private UserAddressPO addressPage;
    private UserRewardPointPO rewardPointPage;
    private UserOrderPO orderPage;
    private String firstName, lastName, emailAddress, companyName, password, confirmPassword;
    private DataConfigNet dataConfigNet;
    private PropertiesConfig propertiesConfig;

    // Pre-Condition
    @Parameters({"server","browser"})
    @BeforeClass
    public void beforeClass(String server, String browserName){
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);

        homePage = PageGenerator.getUserHomePage(driver);
        dataConfigNet = DataConfigNet.getData();

        firstName = dataConfigNet.getFirstName();
        lastName = dataConfigNet.getLastName();
        emailAddress = dataConfigNet.getEmailAddress();
        companyName = "Yoo";
        confirmPassword = "Ngan@123";
    }

    @Description("User_TC_01_Register")
    @Severity(SeverityLevel.CRITICAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_TC_01_Register(){
        registerPage = homePage.clickToRegisterLink();

        registerPage.clickToFemaleRatio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyNameTextbox(companyName);
        registerPage.enterToPasswordTextbox(propertiesConfig.getApplicationPassword());
        registerPage.enterToConfirmPasswordTextbox(propertiesConfig.getApplicationPassword());
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
    }

    @Description("User_TC_02_Login")
    @Severity(SeverityLevel.CRITICAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_TC_02_Login(){
        homePage = registerPage.clickToLogoutLink();
        loginPage = registerPage.clickToLoginLink();

        homePage = loginPage.loginToSystem(emailAddress, propertiesConfig.getApplicationPassword());

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Description("User_TC_03_My_Account")
    @Severity(SeverityLevel.NORMAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_TC_03_My_Account(){
        customerInfoPage = homePage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderFemaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
    }

    @Description("User_TC_04_Dynamic_Page")
    @Severity(SeverityLevel.NORMAL)
    @JiraCreateIssue(isCreateIssue = true)
    //@Test
    public void User_TC_04_Dynamic_Page(){
        // Customer Infor => Address
        addressPage = (UserAddressPO) customerInfoPage.openSidebarLinkByPageName("Addresses");

        // Address => Reward Point
//        rewardPointPage = (UserRewardPointPO) addressPage.openSidebarLinkByPageName("Reward points");
//
//        // Reward Point => Order
//        orderPage = (UserOrderPO) rewardPointPage.openSidebarLinkByPageName("Orders");
//
//        // Order => Address
//        addressPage = (UserAddressPO) orderPage.openSidebarLinkByPageName("Addresses");
//
//        // Address => Customer Infor
//        customerInfoPage = (UserCustomerInforPO) addressPage.openSidebarLinkByPageName("Customer info");
//
//        rewardPointPage = (UserRewardPointPO) customerInfoPage.openSidebarLinkByPageName("Reward points");
//
//        addressPage = (UserAddressPO) rewardPointPage.openSidebarLinkByPageName("Addresses");
    }

    //@Test
    public void User_TC_05_Dynamic_Page(){
        // Address -> Reward points
        addressPage.openSiderbarLinkByPageNames("Reward points");
        rewardPointPage = PageGenerator.getUserRewardPointPage(driver);

        // Reward points -> Orders
        rewardPointPage.openSiderbarLinkByPageNames("Orders");
        orderPage = PageGenerator.getUserOrderPage(driver);

        // Orders -> Addresses
        orderPage.openSiderbarLinkByPageNames("Addresses");
        addressPage = PageGenerator.getUserAddressPage(driver);
    }

    // Post-Condition
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowser();
        closeBrowserDriver();
    }

    public void loginToSystem(String emailAddress, String password){
        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();
    }
}
