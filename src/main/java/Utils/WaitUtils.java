package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private WebDriver driver;

    // Detect CI environment (GitHub Actions)
    public static final int TIMEOUT = System.getenv("GITHUB_ACTIONS") != null ? 25 : 15;


    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for element to be clickable
    public WebElement waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element to be clickable using By locator
    public WebElement waitForElementToBeClickableBy(By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    // Wait for element to be visible
    public WebElement waitForElementToBeVisible(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be Visibility of All element
    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until((ExpectedConditions.visibilityOfAllElements(element)));
    }


    // Wait for element to be visible BY locator
    public WebElement waitForElementToBeVisibleByLocator(By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for visibility of all elements located by a locator
    public List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Wait for element to be present by locator
    public WebElement waitForPresenceOfElement(By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to be present by locator
    public List<WebElement> waitForPresenceOfAllElementsLocatedBy(By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // Wait for page title
    public boolean waitForTitleContains(String title, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.titleContains(title));
    }

    // Wait for element to disappear
    public boolean waitForElementToDisappear(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
