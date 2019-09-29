import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.IOException;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        // Don't use
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        BaseTest testClass = (BaseTest) iTestResult.getInstance();
        try {
            testClass.pages.attachScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        BaseTest testClass = (BaseTest) iTestResult.getInstance();
        try {
            testClass.pages.attachScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        BaseTest testClass = (BaseTest) iTestResult.getInstance();
        try {
            testClass.pages.attachScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // Don't use
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        // Don't use
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // Don't use
    }
}