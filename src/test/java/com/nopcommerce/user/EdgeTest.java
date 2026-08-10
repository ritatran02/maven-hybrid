package com.nopcommerce.user;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class EdgeTest {

    @Test
    public void testEdge() {
        WebDriver driver = new EdgeDriver();

        driver.get("https://www.google.com");

        System.out.println("TITLE = " + driver.getTitle());

        driver.quit();
    }
}
