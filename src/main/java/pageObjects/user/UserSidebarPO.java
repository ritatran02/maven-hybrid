package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.user.UserSidebarPageUI;

public class UserSidebarPO extends BasePage {
    private WebDriver driver;

    public UserSidebarPO(WebDriver driver){
        this.driver = driver;
    }
    public UserRewardPointPO openRewardPointPage(WebDriver driver){
        waitForElementClickable(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);
    }

    public UserCustomerInforPO openCustomerInfoPage(WebDriver driver){
        waitForElementClickable(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getUserCustomerInforPage(driver);
    }

    public UserAddressPO openAddressPage(WebDriver driver){
        waitForElementClickable(driver, UserSidebarPageUI.ADDRESS_LINK);
        clickToElement(driver, UserSidebarPageUI.ADDRESS_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage(WebDriver driver){
        waitForElementClickable(driver, UserSidebarPageUI.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }
 // Số lượng page ít - switch ít
    public UserSidebarPO openSidebarLinkByPageName(String pageName){
        waitForElementClickable(driver,UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

        switch (pageName){
            case "Addresses":
                return PageGenerator.getUserAddressPage(driver);
            case "Reward points":
                return PageGenerator.getUserRewardPointPage(driver);
            case "Customer info":
                return PageGenerator.getUserCustomerInforPage(driver);
            case "Orders":
                return PageGenerator.getUserOrderPage(driver);
            default:
                throw new RuntimeException("Page name is not valid!");
        }
    }

    public void openSiderbarLinkByPageNames(String pageName){
        waitForElementClickable(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver,UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }
}
