package pageUIs.jquery;

public class HomePageUI {
    public static final String DYNAMIC_PAGE_BY_NUMBER = "XPath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_PAGE_ACTIVED_BY_NUMBER = "Xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String DYNAMIC_HEADER_TEXTBOX_BY_NAME = "Xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String DYNAMIC_PAGE_INFO = "xpath=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']" +
            "/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";

    public static final String LOAD_DATA_BUTTON ="css=button#load";
    public static final String DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME = "xPath=//th[text()='%s']/preceding-sibling::th";
    public static final String DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "xPath=//tr[%s]/td[%s]/input";
    public static final String DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX = "xPath=//tr[%s]/td[%s]//select";
    public static final String DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "xPath=//tr[%s]/td[%s]//input";
    public static final String DYNAMIC_ACTION_BY_ROW_INDEX = "xPath=//tr[%s]/td[contains(@id,'rowButton')]//button[contains(@title,'%s')]";
    public static final String ALL_PAGE = "xPath=//li[@class='qgrd-pagination-page']/a";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME = "xPath=//div[text()='%s']/ancestor::th/preceding-sibling::th";
    public static final String DYNAMIC_COLUMN_INDEX = "xPath=//td[%s]";

    // JQuery Upload
    public static final String IS_FILE_LOADED = "xPath=//p[@class='name' and text()='%s']";
    public static final String IS_FILE_UPLOADED = "xPath=//p[@class='name']/a[text()='%s']";
    public static final String START_UPLOAD_BUTTON = "Css=table button.start";
}
