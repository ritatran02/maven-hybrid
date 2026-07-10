package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class UserRewardPointPO extends UserSidebarPO {
    private WebDriver driver;

    public UserRewardPointPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
