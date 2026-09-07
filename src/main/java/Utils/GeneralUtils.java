package Utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class GeneralUtils {

    protected WebDriver driver;
    protected WaitUtils wait;

    public GeneralUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
    }

    // Generic Method: For All dropdown
    public void selectDropdown(By dropdownLocator, String optionToSelect) {

        WebElement dropdown = wait.waitForElementToBeClickableBy(
                dropdownLocator,
                WaitUtils.TIMEOUT
        );

        dropdown.click();

        String optionXpath =
                "//mat-option[.//span[normalize-space()='" +
                        optionToSelect + "']]";

        WebElement option = wait.waitForElementToBeClickableBy(
                By.xpath(optionXpath),
                WaitUtils.TIMEOUT
        );

        option.click();

        log.info("Selected option: " + optionToSelect);
    }

    public void CheckBox(String checkBoxLabel){

    }
}