package webBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebBrowsers {

    public static WebDriver chooseBrowserDriver(BrowserEnum browswerName ,boolean hedless) {
        //using chromeoption
      switch(browswerName) {
          case Chrome: {
                WebDriverManager.chromedriver().setup();
                if(hedless)
                    return new  ChromeDriver((ChromeOptions) getBrowseOptions(browswerName));
                return new ChromeDriver();
            }
            case Firefox: {
                WebDriverManager.firefoxdriver().setup();
                if(hedless)
                    return new FirefoxDriver((FirefoxOptions) getBrowseOptions(browswerName));
                return new FirefoxDriver();

            }
            case Edge: {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
          default:
              return new ChromeDriver();
        }

    }


    private static Object getBrowseOptions(BrowserEnum option) {
        ChromeOptions options = new ChromeOptions();
        switch (option) {
            case Chrome :{
            options.setHeadless(true);
            return options;
            }
            case Firefox: {
                FirefoxOptions options1 = new FirefoxOptions();
                options1.setHeadless(true);
                return options1;
            }
            default:
                return options;

        }
    }

    public static void staticmaximizeWindow(WebDriver driver)
    {
        driver.manage().window().maximize();
    }
    public static void quitWindow(WebDriver driver)
    {
        driver.quit();
    }

    public enum BrowserEnum {
        Firefox,
        Edge,
        Chrome,

    }

}
