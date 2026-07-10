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

public class Level_02_BasePage_I_Initial {
    private WebDriver driver;
    BasePage basePage;
    private String firstName, lastName, emailAddress, companyName, password;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        basePage = new BasePage();

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
        basePage.waitForElementClickable(driver,"//a[@class='ico-register']");
        basePage.clickToElement(driver,"//a[@class='ico-register']");

        basePage.waitForElementClickable(driver,"//input[@id='gender-female']");
        basePage.clickToElement(driver,"//input[@id='gender-female']");

        basePage.sendKeyToElement(driver,"//input[@id='FirstName']",firstName);
        basePage.sendKeyToElement(driver,"//input[@id='LastName']",lastName);
        basePage.sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id='Company']",companyName);
        basePage.sendKeyToElement(driver,"//input[@id='Password']",password);
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']",password);

        basePage.waitForElementClickable(driver,"//button[@id='register-button']");
        basePage.clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"),"Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        basePage.waitForElementClickable(driver,"//a[@class='ico-logout']");
        basePage.clickToElement(driver,"//a[@class='ico-logout']");

        basePage.waitForElementClickable(driver,"//a[@class='ico-login']");
        basePage.clickToElement(driver,"//a[@class='ico-login']");

        basePage.sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id='Password']",password);

        basePage.waitForElementClickable(driver,"//button[contains(@class,'login-button')]");
        basePage.clickToElement(driver,"//button[contains(@class,'login-button')]");

        Assert.assertTrue(basePage.isElementDisplayed(driver,"//a[@class='ico-account' and text()='My account']"));

    }

    @Test
    public void TC_03(){
        basePage.waitForElementClickable(driver,"//a[@class='ico-account']");
        basePage.clickToElement(driver,"//a[@class='ico-account']");

        Assert.assertTrue(basePage.isElementDisplayed(driver,"//input[@id='gender-female']"));

        Assert.assertEquals(basePage.getElementAttribute(driver,"//input[@id='FirstName']","value"),firstName);
        Assert.assertEquals(basePage.getElementAttribute(driver,"//input[@id='LastName']","value"),lastName);
        Assert.assertEquals(basePage.getElementAttribute(driver,"//input[@id='Company']","value"),companyName);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private int generateRandomNumber(){
        return new Random().nextInt(9999);
    }
}
