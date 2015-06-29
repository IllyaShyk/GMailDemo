package functional;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;

/**
 * Created by Illya
 */
public class LogoutTest extends FunctionalTest {

    @Test(dataProvider = "accounts", dataProviderClass = StaticDataProviders.class)
    public void logoutGmailTest(String login, String pass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(login).clickNextBtn();
        loginPage.unCheckStaySignInChBox().enterPassword(pass);
        MailPage mailPage = loginPage.clickSignInBtn();
        if (!mailPage.isOnPage()) Assert.fail("User is NOT logined");
        loginPage =  mailPage.clickAccountOptionsLink().clickSignOutLink();
        Assert.assertTrue(loginPage.isOnPage(), "User is NOT logout");
    }

}
