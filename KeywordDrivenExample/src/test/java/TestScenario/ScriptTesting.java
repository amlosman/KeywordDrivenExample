package TestScenario;
import Actions.WebActionsForElement;
import FileWrappers.LoggingHandling;
import FileWrappers.ReadDataFromJSonFile;
import POM.GoogleSearchpage;
import POM.SearchingResultPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScriptTesting  extends BaseTest {

    @Description("2- Step: Search for Vodafone key word")
    @Test
    public void testSearchPage()
    {
        GoogleSearchpage.Searching(getDriver());

    }
    @Description("3- Step: Scroll down on first page")
    @Test(dependsOnMethods = "testSearchPage")
      public void testResultScrollDown()  {
        try {
            SearchingResultPage.Scrollinguntil(getDriver());

        } catch (InterruptedException e) {
            LoggingHandling.logError(e);
        }
    }
    @Description("4- Step: Navigate to page two")
    @Test(dependsOnMethods = "testResultScrollDown")
    public void testNavigateToSecondPage()  {
       SearchingResultPage.Clickonnext(getDriver());
       Assert.assertTrue(SearchingResultPage.getTest(getDriver(),
               new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile)
                       .getValueOfNode("Locaters/ResultStatusLocator")).contains("Page 2"));

    }
    @Description("5- Step: Count of result")
    @Test(dependsOnMethods = "testNavigateToSecondPage")
    public void testCountResultsDisplayedOnTheSecondPage()  {

        //Assert.assertEquals((SearchingResultPage.countOfResult(getDriver())>=10?true:false),true);
        Assert.assertEquals((SearchingResultPage.countOfResult(getDriver())),10);
        System.out.print("Count=" + SearchingResultPage.countOfResult(getDriver()) );
    }
    @Description("6- Step: Scroll down on Second page")
    @Test(dependsOnMethods = "testCountResultsDisplayedOnTheSecondPage")
    public void scrollDownOnSecondPage()  {
        try {
            SearchingResultPage.Scrollinguntil(getDriver());

        } catch (InterruptedException e) {
            LoggingHandling.logError(e);
        }
    }
    @Description("7- Step: Navigate to page Third page")
    @Test(dependsOnMethods = "scrollDownOnSecondPage")
    public void testNavigateToThirdpage()  {

        SearchingResultPage.Clickonnext(getDriver());
        Assert.assertTrue(SearchingResultPage.getTest(getDriver(),
                new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPathfile)
                        .getValueOfNode("Locaters/ResultStatusLocator")).contains("Page 3"));
    }
}
