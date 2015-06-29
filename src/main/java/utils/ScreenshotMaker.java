package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import selenium.WebDriverWrapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Illya
 */
public class ScreenshotMaker {
    protected WebDriverWrapper driver;
    private static String screenShotDirectory;

    public ScreenshotMaker(WebDriverWrapper driver)
    {
        this.driver = driver ;
        screenShotDirectory = PropertyLoader.loadProperty("screenshot.folder");
        File scrDir = new File (screenShotDirectory);
        if (!scrDir.exists())
            scrDir.mkdirs();
    }

    public static void clearScreenShotSubDirectory(String screenShotSubDirectory)
    {
        File scrSubDir = new File(PropertyLoader.loadProperty("project.dir"),
                                 (new File(screenShotDirectory,screenShotSubDirectory)).toString());
        if (scrSubDir.exists()) {
            try {
                FileUtils.cleanDirectory(scrSubDir);
            } catch (IOException e) {
                Log4Test.error("Exception while clean ScreenshotDirectory" + e.getMessage());
            }
        }
    }
       //remoteWebDriver implements takes screenShot so no need in Augmentation
    public void takeScreenShot(String scrName)
    {
        String scrFormat = PropertyLoader.loadProperty("screenshot.format");
        try {
            File scrFile = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenShotDirectory + scrName + scrFormat));
        }
        catch (Exception e) {
            Log4Test.error("Exception while taking Screenshot " + e.getMessage());
        }
    }

}
