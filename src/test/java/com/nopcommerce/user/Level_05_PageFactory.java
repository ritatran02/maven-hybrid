package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.CustomerInforPageFactory;
import pageFactory.HomePageFactory;
import pageFactory.LoginPageFactory;
import pageFactory.RegisterPageFactory;

public class Level_05_PageFactory extends BaseTest {

    private WebDriver driver;
    private HomePageFactory homePage;
    private RegisterPageFactory registerPage;
    private LoginPageFactory loginPage;
    private CustomerInforPageFactory customerInfoPage; // User-Defined
    private String firstName, lastName, emailAddress, companyName, password, confirmPassword;

    // Pre-Condition
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        //driver = getBrowserDriver(browserName); // BuiltIn

        // Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new HomePageFactory(driver);

        firstName = "Tran";
        lastName = "Rita";
        emailAddress = "ritatran" + generateRandomNumber() + "@gmail.com";
        companyName = "Yoo";
        password = "Ngan@123";
        confirmPassword = "Ngan@123";
    }

    @Test
    public void User_TC_01_Register(){
        // Action 1
        homePage.clickToRegisterLink();

        // Từ Home page qua Register page
        // Page đó được sinh ra và bắt đầu làm những action của page đó
        registerPage = new RegisterPageFactory(driver);

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
        loginPage = new LoginPageFactory(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        // Từ Login page qua Home page
        // Page đó được sinh ra và bắt đầu từ những action của page đó
        homePage = new HomePageFactory(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_TC_03_My_Account(){
        homePage.clickToMyAccountLink();
        customerInfoPage = new CustomerInforPageFactory(driver);

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
