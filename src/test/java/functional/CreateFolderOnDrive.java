package functional;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DrivePage;
import pages.LoginPage;
import pages.MailPage;

/**
 * Created by Illya
 */
public class CreateFolderOnDrive extends FunctionalTest {

    @Test(dataProvider = "folders", dataProviderClass = StaticDataProviders.class)
    public void createFolderOnGmailDrive(String login, String pass, String newFolderName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(login).clickNextBtn();
        loginPage.unCheckStaySignInChBox().enterPassword(pass);
        MailPage mailPage = loginPage.clickSignInBtn();
        if (!mailPage.isOnPage()) Assert.fail("User is NOT logined");
        DrivePage drivePage = mailPage.clickOtherAppsLink().clickGoogleDriveLink();
        driver.switchToTab(1);
        drivePage.clickCreateBtn().clickNewFolderBtn();
        drivePage.enterNewFolderName(newFolderName).clickCreateFolderBtn();
        Assert.assertTrue(drivePage.isFolderCreated(newFolderName));
    }
}
