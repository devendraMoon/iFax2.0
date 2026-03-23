package testUtils;

import Utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ExtendListenerClass {

    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    public void ConfigureReport() throws IOException {

        ConfigReader config = new ConfigReader();

        htmlReporter = new ExtentSparkReporter("ExtentListenerReportDemo.html");  // Extend Report Name

        reports = new ExtentReports(); //

        reports.attachReporter(htmlReporter); // Attached The Extent Report

        // Add system information/ environment information to report
        reports.setSystemInfo("Machine", "PC");
        reports.setSystemInfo("OS", "Windows 10");
        reports.setSystemInfo("browser", config.getBrowser());
        reports.setSystemInfo("Username", "Devendra");

        // Configuration to change the look & feel the report
        htmlReporter.config().setDocumentTitle("Extent Listener Demo");
        htmlReporter.config().setReportName("This is my first report");
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void onStart(ITestContext Result) {
        try {
            ConfigureReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("On Start Method Invoked....");
    }

    public void onFinish(ITestContext Result) {
        System.out.println("On Finished Method Invoked....");
        reports.flush();
    }

    // When a test case gets failed, this method is called
    public void onTestFailure(ITestResult Result) {
        System.out.println("Name of test method failed: " + Result.getName());
        test = reports.createTest(Result.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel("Name of the fail test case: " + Result.getName(), ExtentColor.RED));

        String screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + Result.getName() + ".png";
        File screenShotFile = new File(screenShotPath);
        if (screenShotFile.exists()) {
            test.fail("Capture screenshot below:" + test.addScreenCaptureFromPath(screenShotPath));
        }
    }

    // When a test case get Skipped, this method is called
    public void onTestSkipped(ITestResult Result) {
        System.out.println("Name of test method Skipped: " + Result.getName());
        test = reports.createTest(Result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skip test case: " + Result.getName(), ExtentColor.ORANGE));
    }

    // When a test case get Started, this method is called
    public void onTestStart(ITestResult Result) {
        System.out.println("Name of test method Started: " + Result.getName());
    }

    // When a test case gets passed, this method is called
    public void onTestSuccess(ITestResult Result) {
        System.out.println("Name of test Successfully  Executed : " + Result.getName());
        test = reports.createTest(Result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel("Name of the Passed test case: " + Result.getName(), ExtentColor.GREEN));
    }
}
