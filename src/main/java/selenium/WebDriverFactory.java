package selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import utils.PropertyLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Illya
 */
public class WebDriverFactory {
    public static final String FIREFOX = "firefox";
    public static final String FIREFOX_REMOTE = "firefox_remote";
    public static final String CHROME = "chrome";
    public static final String HTMLUNIT = "htmlunit";

    public static WebDriverWrapper initDriver (String driverName) {
        WebDriverWrapper driverWrapper = null;
        if (driverName.equalsIgnoreCase(FIREFOX)) {
            driverWrapper = new WebDriverWrapper(new FirefoxDriver());
        } else if (driverName.equalsIgnoreCase(CHROME)) {
            File file = new File(PropertyLoader.loadProperty("selenium.chrome.driver.path"));
            System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
            driverWrapper = new WebDriverWrapper(new ChromeDriver());
        } else if (driverName.equalsIgnoreCase(HTMLUNIT)) {
            driverWrapper = new WebDriverWrapper(new HtmlUnitDriver());
        } else if (driverName.equals(FIREFOX_REMOTE)){
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            try {
                driverWrapper = new WebDriverWrapper(
                                new RemoteWebDriver(
                                new URL(PropertyLoader.loadProperty("selenium.hub")), capabilities));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            Assert.fail("Invalid driver configuration");
        }

        driverWrapper.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driverWrapper.manage().deleteAllCookies();
        driverWrapper.manage().window().maximize();
        return driverWrapper;
    }
}
