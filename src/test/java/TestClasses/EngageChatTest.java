package TestClasses;

import Base.BaseClass;
import PageClasses.EngageChat;
import PageClasses.EngageModulePage;
import PageClasses.LogInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import testUtils.ExcelDataProvider;

import java.util.Map;

public class EngageChatTest extends BaseClass {

    @Test(description = "Engage Chat Tab",
            dataProvider = "ExcelData",
            dataProviderClass = ExcelDataProvider.class)
    @Description("This test verifies Engage Chat Feature")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Engage Chat Feature")
    public void VerifyEngageChat(Map<String, String> data) throws InterruptedException {
        log.info("Test Started: Invoke Engage tab");

        // User login
        String loginUserNm = data.get("loginUsername");
        String loginPass = data.get("loginPassword");

        LogInPage lg = new LogInPage(getDriver());
        lg.enterEmail(loginUserNm);
        lg.clickOnContinuebtn();
        lg.enterPassword(loginPass);
        lg.clickOnSignInbtn();

        String message = data.get("dummyMessage");

        EngageChat chat = new EngageChat(getDriver());
        chat.clickOnEngageTab();
        chat.GetNumList();
        chat.GetNumList();
        chat.ClickOnNewBtn();
        chat.ClickOnNewMsgOpn();
        chat.EnterNumField("+12044005432");
        chat.SendMsg(message);

    }
}
