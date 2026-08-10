package commons;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.LogManager;

public class BaseTest {
    private WebDriver driver;

    public static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return ThreadDriver.get();
    }


    protected WebDriver getBrowserDriver(String url, String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_PATH + "\\downloadFiles");
                firefoxOptions.addPreference("browser.download.useDownloadDir", true);
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html, text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
                firefoxOptions.addPreference("pdfjs.disabled", true);
                ThreadDriver.set(new FirefoxDriver(firefoxOptions));
                break;
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_PATH + "\\downloadFiles");
                chromeOptions .setExperimentalOption("prefs", chromePrefs);
                ThreadDriver.set(new ChromeDriver(chromeOptions));
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                ThreadDriver.set(new EdgeDriver(edgeOptions));
                break;
            case HEAD_FIREFOX:
                FirefoxOptions headFirefoxOptions = new FirefoxOptions();
                headFirefoxOptions.addArguments("-headless");
                headFirefoxOptions.addArguments("window-size=1920x1080");
                ThreadDriver.set(new FirefoxDriver(headFirefoxOptions));
                break;
            case HEAD_CHROME:
                ChromeOptions headChromeOptions = new ChromeOptions();
                headChromeOptions.addArguments("--headless");
                headChromeOptions.addArguments("window-size=1920x1080");
                ThreadDriver.set(new ChromeDriver(headChromeOptions));
                break;
            case HEAD_EDGE:
                EdgeOptions headEdgeOptions = new EdgeOptions();
                headEdgeOptions.addArguments("--headless");
                headEdgeOptions.addArguments("window-size=1920x1080");
                ThreadDriver.set(new EdgeDriver(headEdgeOptions));
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        ThreadDriver.get().get(url);
        // driver.get("https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
        ThreadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       // log.info("=============================== INIT BROWSER AND DRIVER ===============================");
        return ThreadDriver.get();
    }

    protected int generateRandomNumber() {
        return new Random().nextInt(999999);
    }


    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            //log.info("-------------------- PASSED --------------------");
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            //log.info("-------------------- FAILED --------------------");
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected void closeBrowser (){
        WebDriver currentDriver = ThreadDriver.get();

        if (currentDriver != null) {
            currentDriver.quit();
            ThreadDriver.remove();
        }
        //log.info("=============================== CLOSE BROWSER AND DRIVER ===============================");
    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("htmlAllure");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles != null && listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void closeBrowserDriver() {
        String cmd = null;

        try {
            WebDriver currentDriver = ThreadDriver.get();

            if (currentDriver != null) {

                String osName = System.getProperty("os.name").toLowerCase();
                String driverInstanceName = currentDriver.toString().toLowerCase();

                String browserDriverName;

                if (driverInstanceName.contains("chrome")) {
                    browserDriverName = "chromedriver";
                } else if (driverInstanceName.contains("firefox")) {
                    browserDriverName = "geckodriver";
                } else if (driverInstanceName.contains("edge")) {
                    browserDriverName = "msedgedriver";
                } else {
                    browserDriverName = "safaridriver";
                }

                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq "
                            + browserDriverName + "*\"";
                } else {
                    cmd = "pkill " + browserDriverName;
                }

                currentDriver.manage().deleteAllCookies();
                currentDriver.quit();
                ThreadDriver.remove();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (cmd != null && !cmd.isEmpty()) {
                try {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


