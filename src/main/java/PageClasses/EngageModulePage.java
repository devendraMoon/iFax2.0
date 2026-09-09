package PageClasses;

import Utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class EngageModulePage {
    WebDriver driver;
    WaitUtils wait;

    public EngageModulePage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        log.info("Engage Page initialized successfully.");
    }

    private By MenuIcon = By.xpath("//button[contains(@class, 'xs-toggle-menu')]");

    // Click On Enable Notification button//
    @FindBy(xpath = "//span[normalize-space()='Enable Notifications']")
    private WebElement NotificationBtn;

    // Click on Engage tab
    @FindBy(xpath = "//button[contains(., 'Engage')]")
    private WebElement EngageTab;

    //  Fetch the number list
    @FindBy(css = ".primary-numbers-list .primary-number-item")
    private List<WebElement> NumList;

    // Scroll to the last number
    @FindBy(css = ("mat-list.primary-numbers-list"))
    private WebElement NumListScroll;

    // Click on the new button
    @FindBy(xpath = "//span[normalize-space()='New']")
    private WebElement NewBtn;

    // Click on the New Call Option
    @FindBy(xpath = "//span[normalize-space()='New Call']")
    private WebElement NewCallBtn;

    // Enter a phone number
    @FindBy(xpath = "//input[@placeholder='Enter number or name']")
    private WebElement EnterPhoneNum;

    // Click on call icon to initiate the call
    @FindBy(xpath = "//button[contains(@class, 'call-btn')]")
    private WebElement ClickOnCallIcon;

    // Incoming Call receive popup - on Receiver side
    @FindBy(xpath = "//div[contains(@class, 'incoming-call')]//mat-dialog-container")
    private WebElement CallReceivePopup;

    // Click on accept button from receiver popup
    @FindBy(xpath = "//mat-dialog-container[@id='floating-call-popup']//button[contains(., 'Accept')]")
    private WebElement AcceptBtn;

    // Call duration
    @FindBy(xpath = "//mat-dialog-container[@id='floating-call-popup']//h2/span[contains(@class, 'f-18')]")
    private WebElement CallDuration;

    // Click on End call button from the receiver side
    @FindBy(xpath = "//button[contains(@class, 'btn-end-call')]")
    private WebElement EndCallBtn;


    // Methods

    @Step("Click on the Enable Notification Button")
    public void ClickOnNotificationBtn() {
        wait.waitForElementToBeVisible(NotificationBtn, WaitUtils.TIMEOUT);
        NotificationBtn.click();
        log.info("Click on the Enable Notification Button");
    }

    @Step("Click on the Menu Icon when required")
    public void ClickOnMenuIcon() {

        List<WebElement> menuIcons =
                driver.findElements(MenuIcon);

        if (!menuIcons.isEmpty()
                && menuIcons.get(0).isDisplayed()
                && menuIcons.get(0).isEnabled()) {

            menuIcons.get(0).click();

            log.info("Menu icon is displayed. Clicked on Menu icon.");

        } else {

            log.info(
                    "Menu icon is not displayed. Side menu is already available."
            );
        }
    }

    @Step("Click on the EngageTab")
    public void clickOnEngageTab() {
        wait.waitForElementToBeClickable(EngageTab, WaitUtils.TIMEOUT);
        EngageTab.click();
        log.info("Click on the EngageTab");
    }

    @Step("Fetch the Contact number & click on the any one number")
    public void GetNumList() throws InterruptedException {
        wait.waitForVisibilityOfAllElements(NumList, WaitUtils.TIMEOUT);
        if (NumList.isEmpty()) {
            throw new RuntimeException("No number found: The Inbox list is empty.");
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", NumListScroll);

        Thread.sleep(5000);
        System.out.println("Available number in engage:" + NumList);

        for (int i = 0; i < NumList.size(); i++) {

            System.out.println(
                    "Number " + (i + 1) + ": " +
                            NumList.get(i).getText().replace("\n", " ")
            );
        }

        System.out.println("Available numbers in account: 4");
        System.out.println("Code fetched the numbers: " + NumList.size());

        WebElement selectNumber = NumList.get(0);
        System.out.println("Clicked on this number:" + selectNumber.getText());
        selectNumber.click();
    }

    @Step("Click on the New Button")
    public void ClickOnNewBtn() {
        wait.waitForElementToBeClickable(NewBtn, WaitUtils.TIMEOUT);
        NewBtn.click();
        log.info("Click on the New Button");
    }

    @Step("Click on the new Call option & Enter number to call:{0}")
    public void PerformTheCallAction(String number) throws InterruptedException {
        wait.waitForElementToBeClickable(NewCallBtn, WaitUtils.TIMEOUT);
        NewCallBtn.click();
        log.info("Click on the New Call Button");

        wait.waitForElementToBeClickable(EnterPhoneNum, WaitUtils.TIMEOUT);
        EnterPhoneNum.sendKeys(number);
        log.info("Enter Number for the call: " + number);


        Thread.sleep(3000);
        wait.waitForElementToBeVisible(ClickOnCallIcon, WaitUtils.TIMEOUT);
        ClickOnCallIcon.click();
        log.info("Click on the call icon to make a call");
    }


    @Step("Accept the call on the receiver side")
    public void ReceivePopup() {

        log.info("Waiting for incoming call banner on receiver side");

        wait.waitForElementToBeVisible(CallReceivePopup, WaitUtils.TIMEOUT);

        log.info("Incoming call banner is displayed");

        wait.waitForElementToBeVisible(AcceptBtn, WaitUtils.TIMEOUT);

        AcceptBtn.click();

        log.info(
                "Clicked on the Accept button from the incoming call banner"
        );
    }

    @Step("End the call automatically after 30 seconds")
    public void hangUpCall() throws InterruptedException {

        final int MAX_DURATION = 30;

        wait.waitForElementToBeVisible(CallDuration, WaitUtils.TIMEOUT);

        while (true) {
            String durationText = CallDuration.getText().trim();

            log.info("Current call duration: {}", durationText);

            int currentSeconds = convertDurationToSeconds(durationText);

            if (currentSeconds >= MAX_DURATION) {

                log.info("Call duration reached {} seconds. Hanging up.", currentSeconds);

                wait.waitForElementToBeClickable(EndCallBtn, WaitUtils.TIMEOUT);

                EndCallBtn.click();

                log.info("Call ended successfully.");

                break;
            }

            Thread.sleep(500);
        }
    }

    private int convertDurationToSeconds(String duration) {

        String[] timeParts = duration.split(":");

        int minutes = Integer.parseInt(timeParts[0]);

        int seconds = Integer.parseInt(timeParts[1]);

        return (minutes * 60) + seconds;
    }
}