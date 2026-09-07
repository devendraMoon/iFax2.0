package TestClasses;

import Base.BaseClass;
import PageClasses.LogInPage;
import PageClasses.SettingPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import testUtils.ExcelDataProvider;

import java.util.Map;

public class SettingTabTest extends BaseClass {

    @Test(description = "Setting Page",
            dataProvider = "ExcelData",
            dataProviderClass = ExcelDataProvider.class)
    @Description("This test verifies Setting Feature")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Setting Feature")
    public void VerifySettingPage(Map<String, String> data) throws InterruptedException {
        log.info("Test Started: Invoke Setting Page");

        // User login
        String loginUserNm = data.get("loginUsername");
        String loginPass = data.get("loginPassword");

        LogInPage lg = new LogInPage(getDriver());
        lg.enterEmail(loginUserNm);
        lg.clickOnContinuebtn();
        lg.enterPassword(loginPass);
        lg.clickOnSignInbtn();

        SettingPage st = new SettingPage(getDriver());
        st.ClickOnSettingOpn();
        st.selectIndustry("Education & Administration");
        st.selectOrganizationSize("501-1000 employees");
        st.selectCountryName("Austria");
    }
}
