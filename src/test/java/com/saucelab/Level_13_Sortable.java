package com.saucelab;

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
import pageObjects.saucelab.LoginPO;
import pageObjects.saucelab.ProductPO;
import pageObjects.user.*;

import java.text.ParseException;

public class Level_13_Sortable extends BaseTest {

    private WebDriver driver;
    private ProductPO productPage;
    private LoginPO loginPage;

    // Pre-Condition
    @Parameters({"url","browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        driver = getBrowserDriver(url, browserName);
        loginPage = PageGenerator.getPage(driver);
        productPage = loginPage.loginToSauce("standard_user","secret_sauce");

    }

    @Test
    public void Sort_01_Name(){
        productPage.sortBy("Name (A to Z)");
        verifyEquals(productPage.getSortItemSelected(), "Name (A to Z)");
        productPage.sleepInSeconds(5);

        verifyTrue(productPage.isProductNameSortByAscending());

        productPage.sortBy("Name (Z to A)");
        verifyEquals(productPage.getSortItemSelected(), "Name (Z to A)");
        productPage.sleepInSeconds(5);

        verifyTrue(productPage.isProductNameSortByDescending());
    }

    @Test
    public void Sort_02_Price() throws ParseException {
        productPage.sortBy("Price (low to high)");
        verifyEquals(productPage.getSortItemSelected(),"Price (low to high)");
        productPage.sleepInSeconds(5);
        verifyTrue(productPage.isProductPriceSortByAscending());

        productPage.sortBy("Price (high to low)");
        verifyEquals(productPage.getSortItemSelected(),"Price (high to low)");
        productPage.sleepInSeconds(5);
        verifyTrue(productPage.isProductNamePriceByDescending());
    }

    // Post-Condition
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowser();
        closeBrowserDriver();
    }
}
