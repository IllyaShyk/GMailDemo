package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log4Test;
import utils.PropertyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Illya
 */
public class WebDriverWrapper implements WebDriver, TakesScreenshot {

    private  static  WebDriver driver;
    private  static final int TIME_TO_WAIT = Integer.valueOf(PropertyLoader.loadProperty("selenium.max.timeout"));

    public WebDriverWrapper(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getOriginalDriver() {
        return driver;
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outType) {
        try {
            if (driver instanceof FirefoxDriver) {
                return ((FirefoxDriver) driver).getScreenshotAs(outType);
            } else if (driver instanceof ChromeDriver) {
                return ((ChromeDriver) driver).getScreenshotAs(outType);
            } else if (driver instanceof InternetExplorerDriver) {
                return ((InternetExplorerDriver) driver).getScreenshotAs(outType);
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            Log4Test.error("Exception while getting Screenshot: " + e.getMessage());
        }
        return null;
    }

    public void switchToTab(int index){
        try {
            ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(index));
            driver.switchTo().activeElement();
        } catch (Exception e) {
            Log4Test.error("Couldn't switch to another Tab");
        }
    }
}
