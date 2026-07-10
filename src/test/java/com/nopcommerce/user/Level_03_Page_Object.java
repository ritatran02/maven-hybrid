package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.user.UserCustomerInforPO;
import pageObjects.user.UserHomePO;
import pageObjects.user.UserLoginPO;
import pageObjects.user.UserRegisterPO;

import java.time.Duration;

public class Level_03_Page_Object extends BaseTest {
    // 1 - Phân tích testcase về mặt tính năng/nghiệp vụ xem nó đi qua bao page/flow ntn
    // 2 - Vẽ flow của các test case ra
    // 3 - Tạo Page Object Class
    // 4 - Tạo Test Class
    // 5 - Viết hàm giả trên Test Class
    // 6 - Implement các hàm này bên Page Object
    // 7 - Define các locator ở Page UI class
    // 8 - Ráp UI/ Locator vào bên Page Object class
    // 9 - Ráp data test vào bên Test Clas
    // 10 - Run và Done

    // Declare Variables
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInforPO customerInfoPage; // User-Defined
    private String firstName, lastName, emailAddress, companyName, password, confirmPassword;

    // Pre-Condition
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver(); // BuiltIn

        driver.get("http://demo.nopcommerce/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new UserHomePO(driver);

        firstName = "Tran";
        lastName = "Rita";
        emailAddress = "ritatran" + generateRandomNumber() + "@gmail.com";
        companyName = "Yoo";
        password = "Ngan@123";
    }

    @Test
    public void User_TC_01_Register(){
        // Action 1
        homePage.clickToRegisterLink();

        // Từ Home page qua Register page
        // Page đó được sinh ra và bắt đầu làm những action của page đó
        registerPage = new UserRegisterPO(driver);

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
        registerPage.clickToLogoutLink();
        registerPage.clickToLoginLink();

        // Từ Register page qua Login page
        // Page đó được sinh ra và bắt đầu từ những action của page đó
        loginPage = new UserLoginPO(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        // Từ Login page qua Home page
        // Page đó được sinh ra và bắt đầu từ những action của page đó
        homePage = new UserHomePO(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_TC_03_My_Account(){
        homePage.clickToMyAccountLink();

        // Từ Home page qua My Account pagt
        // Page đó đc sinh ra và bắt đầu từ những action của page đó
        customerInfoPage = new UserCustomerInforPO(driver);

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
}
