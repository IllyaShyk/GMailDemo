package functional;

import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.DrivePage;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.FunctionalTestRule;
import utils.Log4Test;
import utils.PropertyLoader;

/**
 * Created by Illya
 */

@Listeners({FunctionalTestRule.class})
public abstract class FunctionalTest {

    public static WebDriverWrapper driver;

    public static final String BASE_URL = PropertyLoader.loadProperty("site.url");

    @BeforeSuite
    public void setUp() {
        String browser = PropertyLoader.loadProperty("browser.name");
        driver = WebDriverFactory.initDriver(browser);
        Log4Test.info("**********Starting_TestSuit**********");
        Log4Test.info("Start browser " + browser);
    }

    @BeforeMethod
    public void getDefaultURL() {
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void cleanProcedures(ITestResult testResult) {
        String methodName = testResult.getMethod().getMethodName();
        if (methodName.equalsIgnoreCase("createFolderOnGmailDrive")) {
            Object[] param = testResult.getParameters();
            new DrivePage(driver).deleteFolderWithName(param[2].toString());  //param[2] - third parameter of
            driver.close();                                                   //the Test - name of folder
            driver.switchToTab(0);
        }
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown () {
        if (driver != null) {
            Log4Test.info("Close browser");
            driver.quit();
        }
        Log4Test.info("**********End_TestSuit**********");
    }


}
