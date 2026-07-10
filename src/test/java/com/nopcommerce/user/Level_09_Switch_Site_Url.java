package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.user.*;

public class Level_09_Switch_Site_Url extends BaseTest {

    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserLoginPO userLoginPage;
    private UserCustomerInforPO userCustomerInfoPage; // User-Defined
    private UserAddressPO userAddressPage;
    private UserRewardPointPO userRewardPointPage;
    private UserOrderPO userOrderPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private String firstName, lastName, emailAddress, companyName, password, confirmPassword;
    private String userUrl, adminUrl;
    private String adminEmailAddress, adminPassword;

    // Pre-Condition
    @Parameters({"browser","userUrl","adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl){
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        //driver = getBrowserDriver(browserName, this.userUrl);
        userHomePage = PageGenerator.getUserHomePage(driver);

        //userHomePage = UserPO.getHomePage(driver);

        firstName = "Tran";
        lastName = "Rita";
        emailAddress = "ritatran" + generateRandomNumber() + "@gmail.com";
        companyName = "Yoo";
        password = "Ngan@123";
        confirmPassword = "Ngan@123";
        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";

        userRegisterPage = userHomePage.clickToRegisterLink();

        userRegisterPage.clickToFemaleRatio();
        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.enterToEmailTextbox(emailAddress);
        userRegisterPage.enterToCompanyNameTextbox(companyName);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmPasswordTextbox(confirmPassword);
        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(),"Your registration completed");
        userRegisterPage.clickToLogoutLink();
    }

    @Test
    public void Role_01_User_Site_To_Admin_Site(){
        userLoginPage = userRegisterPage.clickToLoginLink();
        userHomePage = userLoginPage.loginToSystem(emailAddress,password);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        // Step để order 1 product nào đó
        // ...
        // Qua trang Admin để verify/approve cái đã order trc đó vs quyền Admin
        userHomePage.openPageUrl(driver,this.adminUrl);
        adminLoginPage = PageGenerator.getAdminLoginPage(driver);

        // Login vào trang Admin
        adminLoginPage.enterToEmailTextbox(adminEmailAddress);
        adminLoginPage.enterToPasswordTextbox(adminPassword);
        adminLoginPage.clickToLoginButton();
        adminDashboardPage = PageGenerator.getAdminDashboardPage(driver);
    }

    @Test
    public void Role_02_Admin_Site_To_User_Site(){
        adminDashboardPage.openPageUrl(driver,this.userUrl);
    }

    // Post-Condition
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void loginToSystem(String emailAddress, String password){
        userLoginPage.enterToEmailTextbox(emailAddress);
        userLoginPage.enterToPasswordTextbox(password);
        userLoginPage.clickToLoginButton();
    }

    public void adminLoginToSystem(String adminEmailAddress, String adminPassword){
        userLoginPage.enterToEmailTextbox(adminEmailAddress);
        userLoginPage.enterToPasswordTextbox(adminPassword);
        userLoginPage.clickToLoginButton();
    }
}
