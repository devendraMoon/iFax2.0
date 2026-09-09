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

    @Test( priority = 1,
            description = "Login user Test",
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


    @Test(enabled = false,
            priority = 2,
            description = "Invalid test",
            dataProvider = "ExcelData",
            dataProviderClass = ExcelDataProvider.class)
    public void VerifyLoginWithInvalidData(Map<String, String> data) {
        log.info("Test Started: Verify Login with invalid Credentials");

        LogInPage log1 = new LogInPage(getDriver());

        // Get email from current row provided by DataProvider
        String email = data.get("Email");

        // Enter email and click continue
        log1.InvalidCreads(email);


        // Check if password field is displayed (valid email)
        if (log1.isPasswordPageIsDisplayed()) {
            System.out.println("Valid Email: " + email);
        } else {
            System.out.println("Invalid Email: " + email);
        }
   }
}
