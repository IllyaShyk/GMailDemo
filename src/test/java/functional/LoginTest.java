package functional;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;

/**
 * Created by Illya
 */
public class LoginTest extends FunctionalTest {

    @Test(dataProvider = "accounts", dataProviderClass = StaticDataProviders.class)
    public void loginGmailTest(String login, String pass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(login).clickNextBtn();
        loginPage.unCheckStaySignInChBox().enterPassword(pass);
        MailPage mailPage = loginPage.clickSignInBtn();
        Assert.assertTrue(mailPage.isOnPage(), "User is NOT logined - mailPage is not active");
    }


}
