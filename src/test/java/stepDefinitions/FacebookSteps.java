package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class FacebookSteps {
    WebDriver driver;

    @Given("Open facebook application")
    public void openFacebookApplication() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Then("Verify email textbox is displayed")
    public void verifyEmailTextboxIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.name("email")).isDisplayed());
    }

    @Then("Verify password textbox is displayed")
    public void verifyPasswordTextboxIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.name("pass")).isDisplayed());
    }

    @And("Close application")
    public void closeApplication() {
        driver.quit();
    }
}
