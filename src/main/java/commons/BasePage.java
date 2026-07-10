package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // 1 - Access Modifier: public/ protected/ private/ default
    // 2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElement>/ ...
    //     Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    // 3 - Tên hàm: Đặt tên có nghĩa theo chức năng cần viết
    //     Convention tuân theo chuẩn của từng ngôn ngữ lập trình
    //     calmeCase: từ đầu tiên viết tường - chữ cái đầu tiên của các từ tiếp theo viết hoa
    // 4 - Có tham số hay không (tùy thuộc vào chức năng cần viết)
    // 5 - Kiểu dữ liệu trả về cho hàm (liên quan đến các step mình viết trong hàm đó)
    //     Nếu như có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở số 2
    //     Nếu như có return th nó là cái step cuối cùng

    // Tuân theo nguyên tắc của tính đog gòi (Encapsulation)
    // Hàm static có thẻ truy cập trực tiếp từ phạm vi Class

    public static BasePage getBasePage(){
        return new BasePage();
    }

    // Common function (hàm dùng chung) cho nhiều class khác
    public void openPageUrl(WebDriver driver, String url){
        driver.get(url);
    }

    // openPageUrl(driver, "https:://demo.nopcommerce.com");
    // openPageUrl(driver, "https:://demo.nopcommerce.com/register");

//    // K truyền 1 dữ liệu củ thể riêng tư vào
//    public void openUrl(WebDriver driver){
//        driver.get("https:://demo.nopcommerce.com");
//        // Url của trang Home
//    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent()); // hàm alertIsPresent có action switchTo().alert()
    }

    public void acceptToAlert(WebDriver driver){
            waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver){
        waitAlertPresence(driver);
        return driver.switchTo().alert().getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keysToSend){
        waitAlertPresence(driver).sendKeys(keysToSend);
    }

    public void switchToWindowByID(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            if(!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows){
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)){
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows){
            if (!runWindows.equals(parentID)){
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String... restParameter){
        return driver.findElements(getLocator(castParameter(locator, restParameter)));
    }

    public String castParameter(String locator, String... restParameter){
        return String.format(locator, (Object[]) restParameter);
    }

    public By getLocator(String prefixLocator){
        By by = null;
        if(prefixLocator.toUpperCase().startsWith("ID")){
            by = By.id(prefixLocator.substring(3));
        } else  if (prefixLocator.toUpperCase().startsWith("CLASS")){
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toUpperCase().startsWith("NAME")){
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toUpperCase().startsWith("TAGNAME")){
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toUpperCase().startsWith("CSS")){
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toUpperCase().startsWith("XPATH")){
            by = By.xpath(prefixLocator.substring(6));
        } else  {
            throw new RuntimeException("Locator type is not support!");
        }
        return by;
    }

    public By getByXPath(String locator){
        return By.xpath(locator);
    }
    public void clickToElement(WebDriver driver, String locator){
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String restParameter){
        getElement(driver, castParameter(locator,restParameter)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, CharSequence keyToSend){
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver, String locator, CharSequence keyToSend, String restParameter){
        getElement(driver,castParameter(locator,restParameter)).clear();
        getElement(driver, castParameter(locator,restParameter)).sendKeys(keyToSend);
    }


    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend, String restParameter1, String restParemeter2){
        getElement(driver, castParameter(locator,restParameter1, restParemeter2)).clear();
        getElement(driver, castParameter(locator,restParameter1, restParemeter2)).sendKeys(keyToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem){
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem, String... restParameter){
        new Select(getElement(driver, castParameter(locator, restParameter))).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem){
        getElement(driver, parentLocator).click();
        sleepInSeconds(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSeconds(2);
        for (WebElement item : allItems){
            if (item.getText().trim().equals(expectedItem))
                item.click();
            break;
        }
    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String restParameter){
        return getElement(driver, castParameter(locator, restParameter)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator){
        return getElement(driver,locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String restParameter){
        return getElement(driver,castParameter(locator, restParameter)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getElement(driver,locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver,locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locator, String restParameter){
        return getListElement(driver,castParameter(locator, restParameter)).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator){
        if (!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator, String... restParameter){
        if (!getElement(driver, castParameter(locator,restParameter)).isSelected()){
            getElement(driver, castParameter(locator,restParameter)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator){
        if (getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String restParameter){
        return getElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String restParameter1, String restParameter2, String restParameter3, String restParameter4){
        return getElement(driver, castParameter(locator, restParameter1, restParameter2, restParameter3, restParameter4)).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String locator){
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParameter){
        return getElement(driver, castParameter(locator, restParameter)).isSelected();
    }

    public void switchToIframe(WebDriver driver, String locator){
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultPage(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void clickToElementByAction(WebDriver driver, String locator){
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter){
        new Actions(driver).click(getElement(driver, castParameter(locator, restParameter))).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void clickAndHoldToElement(WebDriver driver, String locator){
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void releaseLeftMouse(WebDriver driver){
        new Actions(driver).release();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getElement(driver,sourceLocator),getElement(driver,targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getElement(driver, locator), keys).perform();
    }

    public void scrollToElement(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void scrollToBottomPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url){
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator){
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
                "border: 2px solid red, border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",getElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",getElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",getElement(driver, locator));
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName){
        return getElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName, String restParameter){
        return getElement(driver, castParameter(locator,restParameter)).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName){
        return getElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String restParameter){
        return getElement(driver, castParameter(locator, restParameter)).getDomProperty(propertyName);
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].setAttribute('" + attributeName + "','" + attributeValue + "');",getElement(driver,locator));
    }

    public void removeAttribute(WebDriver driver, String locator, String removeAttribute){
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + removeAttribute + "');",getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParameter){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String restParameter1, String restParameter2){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getLocator(castParameter(locator, restParameter1, restParameter2))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator, String... restParameter){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String restParameter1, String restParameter2, String restParameter3, String restParameter4){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getLocator(castParameter(locator, restParameter1, restParameter2, restParameter3, restParameter4))));
    }

    public void waitForElementPresense(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getLocator(locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, WebElement element){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator, String... restParameter){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getLocator(castParameter(locator, restParameter))));
    }

    public void uploadFileMultipleFiles(WebDriver driver, String... fileNames){
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";

        for (String file : fileNames){
            fullFileName = fullFileName + filePath + file + "\n";
        }
        getElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
    }

    // Only use for TC_07
//    public RewardPointPageObject openRewardPointPage(WebDriver driver){
//        waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
//        clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
//        return PageGenerator.getRewardPointPage(driver);
//    }
//
//    public CustomerInforPageObject openCustomerInfoPage(WebDriver driver){
//        waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
//        clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
//        return PageGenerator.getCustomerInforPage(driver);
//    }
//
//    public AddressPageObject openAddressPage(WebDriver driver){
//        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
//        clickToElement(driver, BasePageUI.ADDRESS_LINK);
//        return PageGenerator.getAddressPage(driver);
//    }
//
//    public OrderPageObject openOrderPage(WebDriver driver){
//        waitForElementClickable(driver, BasePageUI.ORDER_LINK);
//        clickToElement(driver, BasePageUI.ORDER_LINK);
//        return PageGenerator.getOrderPage(driver);
//    }
}
