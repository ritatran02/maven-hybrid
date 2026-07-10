package pageObjects.jquery;

import commons.BasePage;
import commons.BrowserList;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }
    private WebDriver driver;
    private WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }
        return driver;
    }

    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }

    public void openPageByNumber(String pageNumber){
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER, pageNumber);
    }

    public boolean isPageActivedByNumber(String pageNumber) {
        waitForElementVisible(driver,HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_NUMBER,pageNumber);
        return isElementDisplayed(driver,HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_NUMBER, pageNumber);
    }

    public void enterToHeaderTextboxByName(String header, String value){
        waitForElementVisible(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, header);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, value, header);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER, header);
    }

    public boolean isPageInfoDisplayed(String female, String country, String male, String total) {
        waitForElementVisible(driver,HomePageUI.DYNAMIC_PAGE_INFO, female, country, male, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_PAGE_INFO, female, country, male, total);
    }

    public void clickLoadDataButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    public void enterToTextboxByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSendKey) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        sendKeyToElement(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, valueToSendKey, rowIndex, String.valueOf(columnIndex));
    }

    public void checkToCheckboxByColumnNameAndRowIndex(String columnName, String rowIndex) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        waitForElementVisible(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        checkToCheckboxRadio(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex ,String.valueOf(columnIndex));
    }

    public void selectToDropdownByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSelect) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        waitForElementClickable(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        selectItemInDropdown(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX, valueToSelect, rowIndex, String.valueOf(columnIndex));
    }

    public void actionToRowByRowIndex(String rowIndex, String actionName) {
        waitForElementClickable(driver,HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX, rowIndex, actionName);
        clickToElement(driver, HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX, rowIndex, actionName);
    }

    public List<String> getColumnAllValueByColumnName(String columnName) {
        List<WebElement> allPage = getListElement(driver, HomePageUI.ALL_PAGE);
        List<String> columnAllValue = new ArrayList<String>();

        waitListElementVisible(driver,HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME,columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME,columnName) + 1;

        for(WebElement page : allPage){
            page.click();
            List<WebElement> columnAllValueElement = getListElement(driver, HomePageUI.DYNAMIC_COLUMN_INDEX,String.valueOf(columnIndex));

            for (WebElement value : columnAllValueElement){
                columnAllValue.add(value.getText());
            }
        }
        return columnAllValue;
    }

    public boolean isFileLoaded(String fileName) {
        waitForElementVisible(driver,HomePageUI.IS_FILE_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.IS_FILE_LOADED, fileName);
    }

    public void clickStartUpload() {
        List<WebElement> startButtons = getListElement(driver, HomePageUI.START_UPLOAD_BUTTON);
        for (WebElement startButton : startButtons){
            waitForElementClickable(driver, startButton).click();
            sleepInSeconds(2);
        }
    }

    public boolean isFiledUploadedSuccess(String fileName) {
        waitForElementVisible(driver,HomePageUI.IS_FILE_UPLOADED, fileName);
        return isElementDisplayed(driver,HomePageUI.IS_FILE_UPLOADED,fileName);
    }
}
