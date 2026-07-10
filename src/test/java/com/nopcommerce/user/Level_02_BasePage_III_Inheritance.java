package com.nopcommerce.user;


import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_III_Inheritance extends BasePage {
    private WebDriver driver;
    private String firstName, lastName, emailAddress, companyName, password;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.get("http://demo.nopcommerce/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        firstName = "Tran";
        lastName = "Rita";
        emailAddress = "ritatran" + generateRandomNumber() + "@gmail.com";
        companyName = "Yoo";
        password = "Ngan@123";
    }

    @Test
    public void TC_01_Register(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");

        waitForElementClickable(driver,"//input[@id='gender-female']");
        clickToElement(driver,"//input[@id='gender-female']");

        sendKeyToElement(driver,"//input[@id='FirstName']",firstName);
        sendKeyToElement(driver,"//input[@id='LastName']",lastName);
        sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendKeyToElement(driver,"//input[@id='Company']",companyName);
        sendKeyToElement(driver,"//input[@id='Password']",password);
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']",password);

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//div[@class='result']"),"Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        waitForElementClickable(driver,"//a[@class='ico-logout']");
        clickToElement(driver,"//a[@class='ico-logout']");

        waitForElementClickable(driver,"//a[@class='ico-login']");
        clickToElement(driver,"//a[@class='ico-login']");

        sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendKeyToElement(driver,"//input[@id='Password']",password);

        waitForElementClickable(driver,"//button[contains(@class,'login-button')]");
        clickToElement(driver,"//button[contains(@class,'login-button')]");

        Assert.assertTrue(isElementDisplayed(driver,"//a[@class='ico-account' and text()='My account']"));

    }

    @Test
    public void TC_03(){
        waitForElementClickable(driver,"//a[@class='ico-account']");
        clickToElement(driver,"//a[@class='ico-account']");

        Assert.assertTrue(isElementDisplayed(driver,"//input[@id='gender-female']"));

        Assert.assertEquals(getElementAttribute(driver,"//input[@id='FirstName']","value"),firstName);
        Assert.assertEquals(getElementAttribute(driver,"//input[@id='LastName']","value"),lastName);
        Assert.assertEquals(getElementAttribute(driver,"//input[@id='Company']","value"),companyName);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private int generateRandomNumber(){
        return new Random().nextInt(9999);
    }
}
