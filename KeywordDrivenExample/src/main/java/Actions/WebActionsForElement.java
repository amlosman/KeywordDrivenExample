package Actions;

import FileWrappers.LoggingHandling;
import FileWrappers.ReadFromPropertiesFile;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class WebActionsForElement {
WebDriver driver;
    public WebActionsForElement(WebDriver driver)
    {
        this.driver=driver;
    }
    /*wait to validate element*/
    public  WebElement waitUntilCanValidat(By locator, ExpectedConditionsEnum condition) {
        try {
            WebElement element = null;
            switch (condition) {
                case presenceOfElement:
                    element = waitUntil((ExpectedConditions.presenceOfElementLocated(locator)));
                    return element;

                case ElementToBeClickable:
                    element = waitUntil((ExpectedConditions.elementToBeClickable(locator)));
                    return element;
                case visibilityOfElement:
                    element=waitUntil((ExpectedConditions.visibilityOfElementLocated(locator)));
                    return element;
                default:
                    Assert.fail("Wrong condition");
            }
            return element ;
        } catch (Exception e) {
            LoggingHandling.logError(e);
            return null;

        }
    }
    /* Allocate Element*/
    public  By returnElementLocatorBy(String selector, Locators l){
        switch (l){
            case XPath:
                return new By.ByXPath(selector);
            case id:
                return new By.ById(selector);
                case CSS:
                return new By.ByCssSelector(selector);
            case linkText:
                return new By.ByLinkText(selector);
            case Name:
                return new By.ByName(selector);
            default:
                return  null;
        }
    }
    /*Click on element */
    public  void clickonElement(String  selector , Locators l, ExpectedConditionsEnum ConditionOnCurrentPage) {

            By locator = returnElementLocatorBy(selector,l);
            waitUntilCanValidat(locator, ExpectedConditionsEnum.presenceOfElement);
            WebElement element= waitUntilCanValidat(locator,ConditionOnCurrentPage);
            element.click();
        }
   /*Send keys*/
    public void sendKeystoElement(String  selector , Locators l, ExpectedConditionsEnum ConditionOnCurrentPage,String Keys) {

        By locator = returnElementLocatorBy(selector,l);
        waitUntilCanValidat(locator, ExpectedConditionsEnum.presenceOfElement);
        WebElement element=waitUntilCanValidat(locator,ConditionOnCurrentPage);
        element.sendKeys(Keys);
        element.submit();
    }
    /*take screenShoot for errors*/
    @Attachment
    public   void takeScreenShoot(String Error ){
        var camera = (TakesScreenshot)driver;
        File sceenShot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Error = String.format(ReadFromPropertiesFile.getValue("screenShot"),Error);
            Files.move(sceenShot, new File(Error));

        } catch (IOException e) {
            LoggingHandling.logError(e);
        }
    }
    public void ScrollWithJS(String selector, Locators L) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", waitUntilCanValidat(returnElementLocatorBy(selector,L),ExpectedConditionsEnum.presenceOfElement));

    }
    /*Waits types */
    /*implicit wait */
    public WebElement waitUntil(ExpectedCondition<WebElement> s){
        try{
            return new WebDriverWait(driver, 700).until(s);
        }
        catch(Exception e){
            LoggingHandling.logError(e);
            return null;
        }
    }
    public void ExplicitWait(WebElement element) {
        // will wait untill Element Appear
        WebDriverWait wait = new WebDriverWait(driver,500);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /*Locator type*/
    public enum Locators {
        XPath,
        CSS,
        id,
        linkText,
        Name

    }
    /*Expected Condition type*/
    public enum ExpectedConditionsEnum{
        presenceOfElement,
        ElementToBeClickable,
        visibilityOfElement
    }
}
