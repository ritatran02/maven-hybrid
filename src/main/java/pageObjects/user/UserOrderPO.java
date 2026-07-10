package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class UserOrderPO extends UserSidebarPO {
    private WebDriver driver;

    public UserOrderPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
