package POM;

import Actions.WebActionsForElement;
import FileWrappers.ReadDataFromJSonFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchingResultPage {


    public static void Scrollinguntil( WebDriver driver) throws InterruptedException {

        new WebActionsForElement(driver).ScrollWithJS(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/NextLinkLocator"),WebActionsForElement.Locators.id);
    }
    public static void Clickonnext(WebDriver driver ){
        new WebActionsForElement(driver).clickonElement(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/NextLinkLocator"),WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);
    }
    public static  int countOfResult(WebDriver driver ) {
        List<WebElement> Result = driver.findElements(new WebActionsForElement(driver).returnElementLocatorBy(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/CountofResultLinks"), WebActionsForElement.Locators.XPath));
        return Result.size();
    }
    public static String getTest(WebDriver driver , String selector)
    {
        return (new WebActionsForElement(driver).waitUntilCanValidat(
                (new WebActionsForElement(driver).returnElementLocatorBy(selector, WebActionsForElement.Locators.id))
                , WebActionsForElement.ExpectedConditionsEnum.presenceOfElement)).getText();
    }

}
