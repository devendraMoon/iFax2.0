package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureUtils {


    /**
     * Attach a plain text log into Allure report.
     */
    @Attachment(value = "{0}", type = "text/plain")
    public static String logInfo(String message) {
        return message;
    }

    /**
     * Attach screenshot to Allure report using Allure.addAttachment.
     * This version works with void return type.
     *
     * @param driver WebDriver instance
     * @param name   Screenshot name in Allure report
     */
    public static void attachScreenshot(WebDriver driver, String name) {
        if (driver == null) {
            logInfo("WebDriver is null. Screenshot not captured.");
            return;
        }

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshotBytes));
    }

    /**
     * Optional simple screenshot method with default name.
     * Uses void return type compatible with most IDEs.
     */
    public static void attachScreenshot(WebDriver driver) {
        attachScreenshot(driver, "Screenshot_" + System.currentTimeMillis());
    }
}
