package pages;

import org.openqa.selenium.support.PageFactory;
import selenium.WebDriverWrapper;

/**
 * Created by Illya
 */
public abstract class BasePage {

    protected WebDriverWrapper driver;

    public BasePage(WebDriverWrapper driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
