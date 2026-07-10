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

public class Level_11_DataTable extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    @Parameters({"url","browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        driver = getBrowserDriver(url, browserName);
        homePage = HomePageObject.getHomePage(driver);
    }

    @Test
    public void Table_01_Paging(){
        //log.info("Table_01_Paging");
        // 1 - Mở ra 1 trang bất kì ựa vào số trang truyền vào
        homePage.openPageByNumber("5");
        verifyTrue(homePage.isPageActivedByNumber("5"));
        homePage.refreshCurrentPage(driver);
    }

    @Test
    public void Table_02_Search(){
        //log.info("Table_02_Search");
        // 2 - Search ở bất kì 1 header textbox nào dựa vào tên cột
        homePage.enterToHeaderTextboxByName("Country","Afghanistan");
        homePage.sleepInSeconds(3);
        verifyTrue(homePage.isPageInfoDisplayed("384187","Afghanistan", "407124","791312"));
        homePage.refreshCurrentPage(driver);
    }

    //@Test
    public void Table_03_Index(){
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();

        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","2","Rita");
        homePage.sleepInSeconds(2);
        homePage.enterToTextboxByColumnNameAndRowIndex("Company","2","Yoo");
        homePage.sleepInSeconds(2);
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","2","8");
        homePage.sleepInSeconds(2);
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","2","Japan");
        homePage.sleepInSeconds(2);
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","2");
        homePage.sleepInSeconds(2);
        homePage.actionToRowByRowIndex("2","Insert");
        homePage.sleepInSeconds(3);

        homePage.actionToRowByRowIndex("6","Remove");
        homePage.sleepInSeconds(3);

//        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","6","Min");
//        homePage.sleepInSeconds(2);
//        homePage.enterToTextboxByColumnNameAndRowIndex("Country","6","Japan");
//        homePage.sleepInSeconds(2);
//        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","6","1");
//        homePage.sleepInSeconds(2);
    }

    //@Test
    public void Table_05_Get_All_Value(){
        List<String> countryActualValue = homePage.getColumnAllValueByColumnName("Country");
        System.out.println(countryActualValue.size());
    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }
}
