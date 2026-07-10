package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class UserAddressPO extends UserSidebarPO {
    private WebDriver driver;

    public UserAddressPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
