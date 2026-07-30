package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.saucelab.LoginPO;
import pageObjects.saucelab.ProductPO;
import pageObjects.user.*;

public class PageGenerator {
    public static UserHomePO getUserHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }

    public static UserLoginPO getUserLoginPage(WebDriver driver){
        return new UserLoginPO(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver){
        return new UserRegisterPO(driver);
    }

    public static UserCustomerInforPO getUserCustomerInforPage(WebDriver driver){
        return new UserCustomerInforPO(driver);
    }

    public static UserHomePO clickToLogoutLink(WebDriver driver) {
        return new UserHomePO(driver);
    }

    public static UserRewardPointPO getUserRewardPointPage(WebDriver driver){
        return new UserRewardPointPO(driver);
    }

    public static UserOrderPO getUserOrderPage(WebDriver driver){
        return new UserOrderPO(driver);
    }

    public static UserAddressPO getUserAddressPage(WebDriver driver){
        return new UserAddressPO(driver);
    }

    public static AdminLoginPO getAdminLoginPage(WebDriver driver){
        return new AdminLoginPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver){
        return new AdminDashboardPO(driver);
    }

    public static LoginPO getPage(WebDriver driver) {
        return new LoginPO(driver);
    }

    public static ProductPO getProductPage(WebDriver driver) {
        return new ProductPO(driver);
    }
}