package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import selenium.WebDriverWrapper;
import utils.Log4Test;


/**
 * Created by Illya
 */
public class LoginPage extends BasePage{


    private static final String URL_MATCH = "accounts.google.com/ServiceLogin";

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Passwd")
    private WebElement passField;

    @FindBy(id = "signIn")
    private WebElement signInBtn;

    @FindBy(id = "next")
    private WebElement nextBtn;

    @FindBy(id = "PersistentCookie")
    private WebElement staySignInChbox;


    public LoginPage(WebDriverWrapper driver) {
        super(driver);
            if (!driver.getCurrentUrl().contains(URL_MATCH)) {
                Assert.fail("This is not the page you are expected");
            }
    }

        // Type email in emailField
    public LoginPage enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        Log4Test.info("Login with '" + email + "' account");
        return this;
    }

        // Type pass in passField
    public LoginPage enterPassword(String pass) {
        passField.clear();
        passField.sendKeys(pass);
        return this;
    }

        // Click Next button
    public LoginPage clickNextBtn() {
        nextBtn.click();
        return this;
    }

        // Click SignIn button, return MailPage
    public MailPage clickSignInBtn() {
        signInBtn.click();
        return new MailPage(driver);
    }

        // unCheck 'Stay SignIn' checkBox
    public LoginPage unCheckStaySignInChBox() {
        if(staySignInChbox.isSelected())
            staySignInChbox.click();
        return this;
    }

        //Check if LoginPage is active - URL contains URL_MATCH
    public boolean isOnPage() {
        if (driver.getCurrentUrl().contains(URL_MATCH)) {
            Log4Test.info("User is out of account");
            return true;
        } else {
            return false;
        }
    }

}
