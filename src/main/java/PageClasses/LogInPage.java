package PageClasses;

import Utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class LogInPage {

    WebDriver driver;
    WaitUtils wait;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        log.info("Login Page initialized successfully.");
    }

    // Find the Email locator
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement LoginEmail;

    // Find Continue button locator
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement continuebtn;

    // Find Password locator
    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    // Find Sign in button locator
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInbtn;

    // Logout flow: Find Login user icon element for logout
    @FindBy(xpath = "//div[@class='profile-header']")
    private WebElement profileICon;

    // Find logout option element
    @FindBy(xpath = "//span[normalize-space()='Sign Out']")
    private WebElement logoutOpn;

    // For the Invalid flow
    @FindBy(xpath = "//input[@type='password']")
    private List<WebElement> passwordField;


    // --- Actions (Public) ---

    @Step("Enter Email Id: {0}")
    public void enterEmail(String emailId) {
        wait.waitForElementToBeVisible(LoginEmail, WaitUtils.TIMEOUT);
        LoginEmail.sendKeys(emailId);
        log.info("Enter Email iD:" + emailId);
    }

    @Step("Click on the Continue button")
    public void clickOnContinuebtn() {
        wait.waitForElementToBeClickable(continuebtn, WaitUtils.TIMEOUT);
        continuebtn.click();
        log.info("Click on the Continues button.");
    }

    @Step("Enter Password:{0}")
    public void enterPassword(String pass) {
        wait.waitForElementToBeVisible(password, WaitUtils.TIMEOUT);
        password.sendKeys(pass);
        log.info("Entered Password:" + pass);
    }

    @Step("Click on the Sign In button")
    public void clickOnSignInbtn() {
        wait.waitForElementToBeClickable(signInbtn, WaitUtils.TIMEOUT);
        signInbtn.click();
        log.info("Click on the Sign In button.");
    }

    @Step("Click on the Profile icon")
    public void clickOnProfile() {
        wait.waitForElementToBeClickable(profileICon, WaitUtils.TIMEOUT);
        profileICon.click();
        log.info("Click on the Profile icon");
    }

    @Step("Click on the Logout option")
    public void clickOnLogoutOptn() {
        wait.waitForElementToBeClickable(logoutOpn, WaitUtils.TIMEOUT);
        logoutOpn.click();
        log.info("Click on the Logout option");
    }

    public void InvalidCreads(String emailID){
        LoginEmail.clear();
        LoginEmail.sendKeys(emailID);

        signInbtn.click();
    }

    public boolean isPasswordPageIsDisplayed(){
        return passwordField.size()>0;
    }

}
