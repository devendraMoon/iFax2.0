package TestClasses;

import Base.BaseClass;
import PageClasses.SignUpPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testUtils.ExcelDataProvider;

import java.util.Map;


public class SignUpTest extends BaseClass {
    @Test(description = "Verify sign up flow",
            dataProvider = "ExcelData",
            dataProviderClass = ExcelDataProvider.class)
    @Description("This test verifies Sign up Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Sign up Feature")
    @Parameters("Browser")
    public void verifySignUp(Map<String, String> data) {
        log.info("Test Started: Verify SignUp User flow");

        String signUpEmailID = data.get("emailID");
        String signUpFirstName = data.get("FirstName");
        String signUpLastName = data.get("LastName");
        String signUpPassword = data.get("Password");
        String signUpConfirmPassword = data.get("Password");

        SignUpPage signUpPage = new SignUpPage(getDriver());
        signUpPage.enterSignUpEmail(signUpEmailID);
        signUpPage.clickOnContinuebtn();
        signUpPage.enterFirstName(signUpFirstName);
        signUpPage.enterLastName(signUpLastName);
        signUpPage.enterPasswords(signUpPassword, signUpConfirmPassword);
        signUpPage.clickOnSignUpBtn();
        signUpPage.VerifyText();

    }

}
