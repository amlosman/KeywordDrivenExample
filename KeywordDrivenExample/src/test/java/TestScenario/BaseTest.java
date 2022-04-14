package TestScenario;

import Actions.WebActionsForElement;
import FileWrappers.ReadFromPropertiesFile;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import webBrowser.WebBrowsers;

public class BaseTest {
    WebDriver driver;
    @Description("1- Step: open Google page")
    @Parameters("browser")
    @BeforeClass
    public void beforeClass( @Optional("Chrome") String browser)
    {
        driver= WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf(browser), ReadFromPropertiesFile.getValue("headless")=="yes");
        WebBrowsers.staticmaximizeWindow(driver);
        driver.navigate().to(ReadFromPropertiesFile.getValue("GoogleURL"));

    }
    public WebDriver getDriver()
    {
        return driver;
    }
    @AfterMethod
    public void takeScreenShoot(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            new WebActionsForElement(driver).takeScreenShoot(result.getName());
        }
    }

    @Description("8- Step: close Browser")
    @AfterClass
    public void tearDown()
    {
        WebBrowsers.quitWindow(driver);
    }
}
