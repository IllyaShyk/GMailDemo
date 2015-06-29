package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by Illya
 */
public class FunctionalTestRule extends TestListenerAdapter {


    @Override
    public void onTestStart(ITestResult testResult) {
        Log4Test.info(String.format("-------%s-------", testResult.getName()));
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        Log4Test.result("FAILED!");
        //String methodName = testResult.getMethod().getMethodName();
        //new ScreenshotMaker(FunctionalTest.driver).takeScreenShot(methodName);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Log4Test.result("PASSED!");
    }



}
