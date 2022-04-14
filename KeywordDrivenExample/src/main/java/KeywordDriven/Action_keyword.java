package KeywordDriven;

import Actions.WebActionsForElement;
import FileWrappers.ReadDataFromJSonFile;
import FileWrappers.ReadFromPropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webBrowser.WebBrowsers;

import java.util.List;

public class Action_keyword {
    public static WebDriver driver;
    public static void ExcuteAction_keyword(String action)
    {
        switch (action){
            case "openBrowser":
            {
                driver= WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.Chrome, ReadFromPropertiesFile.getValue("headless")=="yes");
                WebBrowsers.staticmaximizeWindow(driver);

                break;
            }
            case "navigate":{
                driver.navigate().to(ReadFromPropertiesFile.getValue("GoogleURL"));
                break;
            }
            case "searching":{
                new WebActionsForElement(driver)
                        .sendKeystoElement(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/SearchLocator"),WebActionsForElement.Locators.Name,
                                WebActionsForElement.ExpectedConditionsEnum.
                                        visibilityOfElement,new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Data/SearchKeys") );

                break;
            }
            case "scrolling":
            {
                try {
                    new WebActionsForElement(driver).ScrollWithJS(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/NextLinkLocator"),WebActionsForElement.Locators.id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "goToNext":
            {
                new WebActionsForElement(driver).clickonElement(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/NextLinkLocator"),WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);
                break;
            }
            case "counting":
            {
                List<WebElement> Result = driver.findElements(new WebActionsForElement(driver).returnElementLocatorBy(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/CountofResultLinks"), WebActionsForElement.Locators.XPath));
                Assert.assertEquals(Result.size(),10);
                break;
            }
            case "validation":{
                 Assert.assertTrue( driver.findElement(new WebActionsForElement(driver).returnElementLocatorBy(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/ResultStatusLocator"), WebActionsForElement.Locators.id)).getText().contains("Page 3"));
                break;
            }
            case "closeBrowser":{
                driver.quit();
                break;
            }
        }
    }
}
