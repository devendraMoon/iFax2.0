package TestClasses;

import Base.BaseClass;
import PageClasses.EngageModulePage;
import PageClasses.LogInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import testUtils.ExcelDataProvider;

import java.util.Map;

public class EngageTabTest extends BaseClass {

    @Test(description = "Engage Tab",
            dataProvider = "ExcelData",
            dataProviderClass = ExcelDataProvider.class)
    @Description("This test verifies Engage Calling Feature")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Engage Feature")
    public void VerifyEngageTab(Map<String, String> data) throws InterruptedException {
        log.info("Test Started: Invoke Engage tab");

        // Caller login - Chrome
        String loginUserNm = data.get("loginUsername");
        String loginPass = data.get("loginPassword");

        LogInPage lg = new LogInPage(getDriver());
        lg.enterEmail(loginUserNm);
        lg.clickOnContinuebtn();
        lg.enterPassword(loginPass);
        lg.clickOnSignInbtn();

        EngageModulePage engage = new EngageModulePage(getDriver());
        engage.ClickOnMenuIcon();
        engage.clickOnEngageTab();
        engage.GetNumList();

        // Receiver login - Firefox
        WebDriver receiverDriver = createAdditionalBrowser("firefox");

        LogInPage ReceiverLg =new LogInPage(getReceiverDriver());

        String ReceiverLogin = data.get("receiverUsername");
        String ReceiverPass = data.get("receiverPassword");

        ReceiverLg.enterEmail(ReceiverLogin);
        ReceiverLg.clickOnContinuebtn();
        ReceiverLg.enterPassword(ReceiverPass);
        ReceiverLg.clickOnSignInbtn();

        EngageModulePage receiverEngage = new EngageModulePage(receiverDriver);
        receiverEngage.ClickOnMenuIcon();
        receiverEngage.clickOnEngageTab();

        //  SENDER INITIATES CALL
        engage.ClickOnNewBtn();
        engage.PerformTheCallAction("+12514189393");

        // RECEIVER ACCEPTS CALL
        receiverEngage.ReceivePopup();
        receiverEngage.hangUpCall();
    }
}
