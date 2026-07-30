package pageObjects.saucelab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import javax.lang.model.element.Element;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProductPO extends BasePage {
    private WebDriver driver;

    public ProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String sortCriteria) {
        waitForElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN, sortCriteria);
    }

    public String getSortItemSelected(){
        waitForElementVisible(driver, ProductPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN);
    }

    public boolean isProductNameSortByAscending() {
        // Lấy ra hết tất cả element chứa Product Name
        List<WebElement> productName = getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        // Khai báo 1 mảng danh sách A
        ArrayList<String> productList = new ArrayList<String>();

        // Dùng bòng lặp lấy Product Name text lưu vào ds A
        for (WebElement product : productName) {
            productList.add(product.getText());
        }

        // Khai báo 1 mảng ds B lấy dữ liệu từ A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product : productList){
            sortedList.add(product);
        }

        // Sort Ascending ds B
        Collections.sort(sortedList);

        return productList.equals(sortedList);
    }


    public boolean isProductNameSortByDescending() {
        List<WebElement> productName = getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        ArrayList<String> productList = new ArrayList<String>();

        for (WebElement product : productName) {
            productList.add(product.getText());
        }

        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product : productList){
            sortedList.add(product);
        }

        // Sort Ascending ds B
        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortByAscending() throws ParseException {
        // Lấy ra hết tất cả element chứa Product Name
        List<WebElement> productPrice = getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        // Khai báo 1 mảng danh sách A
        ArrayList<Float> productList = new ArrayList<Float>();
        ArrayList<Date> productListDate = new ArrayList<Date>();

        // Dùng bòng lặp lấy Product Name text lưu vào ds A
        for (WebElement product : productPrice) {
            productList.add(Float.parseFloat(product.getText().replace("$","")));

            productListDate.add(new SimpleDateFormat("MMM dd yyyy").parse(product.getText().replace(",","")));
        }

        // Khai báo 1 mảng ds B lấy dữ liệu từ A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product : productList){
            sortedList.add(product);
        }

        // Sort Ascending ds B
        Collections.sort(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductNamePriceByDescending() {
        List<WebElement> productPrice = getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        ArrayList<Float> productList = new ArrayList<Float>();

        for (WebElement product : productPrice) {
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product : productList){
            sortedList.add(product);
        }

        // Sort Ascending ds B
        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return productList.equals(sortedList);
    }
}
