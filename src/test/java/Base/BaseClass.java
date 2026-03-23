package Base;

import Utils.AllureUtils;
import Utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseClass {


    // ThreadLocal driver (safe for parallel execution)
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Logger instance for Log4j2
    public static Logger log = LogManager.getLogger(BaseClass.class);


    ConfigReader configReader;

    {
        try {
            configReader = new ConfigReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    @Parameters("Browser")
    public void setup(String browser, Method method) {
        String URL = configReader.getUrl();
        // Use browser parameter from TestNG, fallback to config if not provided
        String browserToUse = (browser != null && !browser.isEmpty()) ? browser : configReader.getBrowser();

        // Log test start clearly
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        long threadId = Thread.currentThread().getId();

        log.info("===== START TEST ===== Class: {} | Method: {} | Thread: {} =====",
                className, methodName, threadId);

        WebDriver webDriver = initializeBrowser(browserToUse);   // local variable

        driver.set(webDriver);   //  store driver in ThreadLocal

        getDriver().manage().window().maximize();
        log.info("Browser window maximized.");

        getDriver().get(URL);
        log.info("Navigated to URL: " + URL);
    }

    private WebDriver initializeBrowser(String browser) {

        // Check if headless mode is enabled via Jenkins or Maven
        // Detect if running in CI (GitHub Actions)
        boolean isCI = System.getenv("GITHUB_ACTIONS") != null;

        // Headless flag from Maven or CI environment
        boolean isHeadless = isCI || System.getProperty("headless", "false").equalsIgnoreCase("true");

        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                // Required flags for CI (GitHub Actions)
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                webDriver = new ChromeDriver(chromeOptions);
                log.info("Chrome browser launched. Headless: " + isHeadless);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }

                webDriver = new FirefoxDriver(firefoxOptions);
                log.info("Firefox browser launched. Headless: " + isHeadless);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return webDriver;
    }

    @AfterMethod
    public void CloseBrowser(ITestResult result) {

        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        long threadId = Thread.currentThread().getId();

        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.attachScreenshot(getDriver());
            log.error("Test FAILED: {}.{} | Thread: {}", className, methodName, threadId);
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            log.info("Test PASSED: {}.{} | Thread: {}", className, methodName, threadId);
        } else if (ITestResult.SKIP == result.getStatus()) {
            log.warn("Test SKIPPED: {}.{} | Thread: {}", className, methodName, threadId);
        }

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); //  remove ThreadLocal reference
            log.info("Browser closed. END TEST: {}.{} | Thread: {}", className, methodName, threadId);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();   // always use this in tests/POMs
    }
}
