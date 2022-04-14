package TestScenario;
import FileWrappers.ExcelFileManager;
import KeywordDriven.Action_keyword;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
public class TestKeywordDriven {

    @Description("Run by KeyWord Driven")
    @Test(dataProvider = "Keyword")
    public void TestKeys(String Action) throws InterruptedException {
        System.out.println(Action);
        Action_keyword.ExcuteAction_keyword(Action);
    }
    @DataProvider(name = "Keyword")
    public Object[][] ReadFromexcel()
    {
        ExcelFileManager excel = new ExcelFileManager(new File("Keyword.xlsx"));
        excel.switchToSheet("Sheet1");


        Object[][]Locator = new Object[10][1];
        for (int i=2;i<12;i++) {
            Locator[i-2][0] =excel.getCellData("KeyWord", i);
        }
        return Locator;
    }

}
