package com.jquery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_12_Upload_File extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    String duongTau = "DuongTau.jpg";
    String hoGuom = "HoGuom.jpg";
    String thanhPho = "ThanhPho.jpg";
    String[] fileNames = {duongTau, hoGuom, thanhPho};

    @Parameters({"url","browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        driver = getBrowserDriver(url, browserName);
        homePage = HomePageObject.getHomePage(driver);
    }

    @Test
    public void Upload_01_Single(){
        homePage.uploadFileMultipleFiles(driver, duongTau);
        homePage.uploadFileMultipleFiles(driver, hoGuom);
        homePage.uploadFileMultipleFiles(driver,thanhPho);

        Assert.assertTrue(homePage.isFileLoaded(duongTau));
        Assert.assertTrue(homePage.isFileLoaded(hoGuom));
        Assert.assertTrue(homePage.isFileLoaded(thanhPho));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFiledUploadedSuccess(duongTau));
        Assert.assertTrue(homePage.isFiledUploadedSuccess(hoGuom));
        Assert.assertTrue(homePage.isFiledUploadedSuccess(thanhPho));
    }

    @Test
    public void Upload_02_Multiple(){
        homePage.refreshCurrentPage(driver);
        homePage.uploadFileMultipleFiles(driver, fileNames);


        Assert.assertTrue(homePage.isFileLoaded(duongTau));
        Assert.assertTrue(homePage.isFileLoaded(hoGuom));
        Assert.assertTrue(homePage.isFileLoaded(thanhPho));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFiledUploadedSuccess(duongTau));
        Assert.assertTrue(homePage.isFiledUploadedSuccess(hoGuom));
        Assert.assertTrue(homePage.isFiledUploadedSuccess(thanhPho));
    }


    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
