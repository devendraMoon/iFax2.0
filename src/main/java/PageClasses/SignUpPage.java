package PageClasses;

import Utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class SignUpPage {

    WebDriver driver;
    WaitUtils wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        log.info("Login Page initialized successfully.");
    }

    // Find the Email locator
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement signUpEmail;

    // Find Continue button locator
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement continuebtn;

    // Find the First Name locator
    @FindBy(name = "fname")
    private WebElement signUpFname;

    // Find the Last Name locator
    @FindBy(name = "lname")
    private WebElement signUpLName;

    // Find Password locator
    @FindBy(xpath = "//input[@type='password']")
    private WebElement signUpPass;

    // Find Confirm Password locator
    @FindBy(xpath = "//input[@id='mat-input-4']")
    private WebElement signUpCnfPass;

    // Find Sign Up button locator
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInbtn;

    // Find the 'Verify' text locator ////span[normalize-space()='Verify']/parent::button
    @FindBy(xpath = "//span[normalize-space()='Verify']")
    private WebElement verifyText;

    // --- Actions (Public) ---

    @Step("Enter Sign up email id: {0}")
    public void enterSignUpEmail(String Signupemail) {
        wait.waitForElementToBeVisible(signUpEmail, WaitUtils.TIMEOUT);
        signUpEmail.sendKeys(Signupemail);
        log.info("Enter Email iD:" + Signupemail);
    }

    @Step("Click on the Continue button")
    public void clickOnContinuebtn() {
        wait.waitForElementToBeClickable(continuebtn, WaitUtils.TIMEOUT);
        continuebtn.click();
        log.info("Click on the Continues button.");
    }

    @Step("Enter First Name: {0}")
    public void enterFirstName(String Fname) {
        wait.waitForElementToBeVisible(signUpFname, WaitUtils.TIMEOUT);
        signUpFname.sendKeys(Fname);
        log.info("Enter First Name:" + Fname);
    }

    @Step("Enter Last Name: {0}")
    public void enterLastName(String Lname) {
        wait.waitForElementToBeVisible(signUpLName, WaitUtils.TIMEOUT);
        signUpLName.sendKeys(Lname);
        log.info("Enter Last Name:" + Lname);
    }

    @Step("Enter Password:{0} and Confirm Password:{1}")
    public void enterPasswords(String pass, String cnfPass) {
        wait.waitForElementToBeVisible(signUpPass, WaitUtils.TIMEOUT);
        signUpPass.sendKeys(pass);
        log.info("Enter Password:" + pass);

        wait.waitForElementToBeVisible(signUpCnfPass, WaitUtils.TIMEOUT);
        signUpCnfPass.sendKeys(cnfPass);
        log.info("Enter Confirm password:" + cnfPass);
    }

    @Step("Click on the Sign up button")
    public void clickOnSignUpBtn() {
        wait.waitForElementToBeClickable(signInbtn, WaitUtils.TIMEOUT);
        signInbtn.click();
        log.info("Click on the Sign Up button");
    }

    @Step("Print the Verify button text")
    public void VerifyText() {
        wait.waitForElementToBeVisible(verifyText, WaitUtils.TIMEOUT);
        String Text = verifyText.getText();
        log.info("text is:" + Text);

    }
}
