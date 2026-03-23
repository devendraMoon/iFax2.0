package TestClasses;

import Base.BaseClass;
import PageClasses.LogInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testUtils.ExcelDataProvider;
import java.util.Map;

public class LoginTest extends BaseClass {

    @Test(description = "Login user Test",
            dataProvider = "ExcelData",
            dataProviderClass = ExcelDataProvider.class)
    @Description("This test verifies login Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Login Feature")
    public void VerifyLogin(Map<String, String> data) {
        log.info("Test Started: Verify Login User flow");

        String loginUserNm = data.get("loginUsername");
        String loginPass = data.get("loginPassword");

        LogInPage lg = new LogInPage(getDriver());
        lg.enterEmail(loginUserNm);
        lg.clickOnContinuebtn();
        lg.enterPassword(loginPass);
        lg.clickOnSignInbtn();

        String TitleText = getDriver().getTitle();

        Assert.assertEquals(TitleText, "Amplify", "Title is not matched:" + TitleText);

        lg.clickOnProfile();
        lg.clickOnLogoutOptn();
    }
}
