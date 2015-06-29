package functional;

import org.testng.annotations.DataProvider;


/**
 * Class for diff DataProviders for tests
 */
public class StaticDataProviders {

    @DataProvider(name = "accounts")
    public static Object[][] create() {
        return new Object[][]{
                {"mails4testuser", "rfghjyrfghjy"},      //data {login, pass} for Gmail
        };
    }

    @DataProvider(name = "folders")
    public static Object[][] createData() {
        return new Object[][]{
                {"mails4testuser", "rfghjyrfghjy", "Temporary"},      //data {login, pass, folderName}
        };
    }
}

