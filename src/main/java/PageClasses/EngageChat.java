package PageClasses;

import Utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class EngageChat {

    WebDriver driver;
    WaitUtils wait;

    public EngageChat(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        log.info("Engage Page initialized successfully.");
    }

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

    // Click on the new message option
    @FindBy(xpath = "//span[text()='New Message']")
    private WebElement NewMessageOpn;

    // Number text field
    @FindBy(xpath = "//input[@type='text']")
    private WebElement NumberField;

    // Click on the number suggestion list first option
    @FindBy(xpath = "//mat-option[@role='option'][1]")
    private WebElement NumSuggestion;

    // Text message field
    @FindBy(xpath = "//div[@data-placeholder='Write a message...']")
    private WebElement MsgBox;

    // Click on the send button
    @FindBy(xpath = "//span[normalize-space()='send']")
    private WebElement SendBtn;

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

        System.out.println("Available numbers in account: 23");
        System.out.println("Code fetched the numbers: " + NumList.size());

        WebElement selectNumber = NumList.get(22);
        System.out.println("Clicked on this number:" + selectNumber.getText());
        selectNumber.click();
    }

    @Step("Click on the New Button")
    public void ClickOnNewBtn() {
        wait.waitForElementToBeClickable(NewBtn, WaitUtils.TIMEOUT);
        NewBtn.click();
        log.info("Click on the New Button");
    }

    @Step("Click on the new message option")
    public void ClickOnNewMsgOpn(){
        wait.waitForElementToBeClickable(NewMessageOpn, WaitUtils.TIMEOUT);
        NewMessageOpn.click();
        log.info("Click on the new message option");
    }

    @Step("Enter number in number field:{0}")
    public void EnterNumField(String number){
        wait.waitForElementToBeVisible(NumberField, WaitUtils.TIMEOUT);
        NumberField.sendKeys(number);
        log.info("Enter number in number field: "+number);

        wait.waitForElementToBeVisible(NumSuggestion, WaitUtils.TIMEOUT);
        NumSuggestion.click();
        log.info("Click on the suggested number from the number field....");
    }

    @Step("Message sent Successfully: {0}")
    public void SendMsg(String message){
        wait.waitForElementToBeClickable(MsgBox, WaitUtils.TIMEOUT);
        MsgBox.sendKeys(message);
        log.info("Message Enter in message text box: "+message);

        wait.waitForElementToBeVisible(SendBtn, WaitUtils.TIMEOUT);
        SendBtn.click();
        log.info("Message successfully send to provided number: "+message);
    }
}

// chat list
//app-chat-log-list[contains(@class, 'h-100') and contains(@class, 'scroll')]
