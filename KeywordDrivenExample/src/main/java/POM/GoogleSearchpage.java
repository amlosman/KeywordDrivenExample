package POM;

import Actions.WebActionsForElement;
import FileWrappers.ReadDataFromJSonFile;
import org.openqa.selenium.WebDriver;

public class GoogleSearchpage {


    public  static void Searching(WebDriver driver)
    {


        new WebActionsForElement(driver)
                .sendKeystoElement(new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Locaters/SearchLocator"),WebActionsForElement.Locators.Name,
                        WebActionsForElement.ExpectedConditionsEnum.
                                ElementToBeClickable,new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile).getValueOfNode("Data/SearchKeys") );
    }

}
