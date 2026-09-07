package PageClasses;

import Utils.GeneralUtils;
import Utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class SettingPage extends GeneralUtils {

    WebDriver driver;
    WaitUtils wait;

    public SettingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        log.info("Setting Page initialized successfully.");
    }

    @FindBy(xpath = "//mat-icon[text()='settings']")
    private WebElement SettingBtn;

    @FindBy(xpath = "//mat-list-item[contains(., 'Team & SSO')]")
    private WebElement TeamNSSOOptn;


    @Step("Click on the setting button")
    public void ClickOnSettingOpn() {
        wait.waitForElementToBeVisible(SettingBtn, WaitUtils.TIMEOUT);
        SettingBtn.click();
        log.info("Click on the setting button");
    }

    @Step("Select the Industry dropdown value: {0}")
    public void selectIndustry(String industry)  {
        selectDropdown(By.xpath("//mat-select[@placeholder='Select Industry']"),industry);
        log.info("Industry dropdown selected: " + industry);
    }

    @Step("Select the Organization Size dropdown value: {0}")
    public void selectOrganizationSize(String organizationSize) {
        selectDropdown(By.xpath("//mat-select[@placeholder='Organization Size']"),organizationSize);
        log.info("Organization Size dropdown selected: " + organizationSize);
    }

     @Step("Select the Country name from dropdown value: {0}")
    public void selectCountryName(String country) {
        selectDropdown(By.xpath("//mat-select[@placeholder='Select Country']"),country);
        log.info("Selected country from dropdown:"+ country);
    }

    @Step("Click on the Team and SSO option From Settings page.")
    public void ClickOnTeamAndSSSo(){
        wait.waitForElementToBeVisible(TeamNSSOOptn,WaitUtils.TIMEOUT);
        TeamNSSOOptn.click();
        log.info("Click on the Team and SSO option From Settings page.");
    }

}
