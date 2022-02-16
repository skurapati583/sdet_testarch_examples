package org.demo.account.pages;

import org.demo.account.common.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Twitter_Starting_Blank_Page {

    private WebDriver driver;

    @FindBy(xpath = "//div[@data-testid=\"empty_state_header_text\"]/span")
    private WebElement welcomeHeader;

    @FindBy(xpath = "//a[@href='/i/connect_people']")
    private WebElement letsGoButton;

    public Twitter_Starting_Blank_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeHeaderText() {
        CommonFunctions.waitForElementPresent(driver, welcomeHeader);
        return welcomeHeader.getText();
    }

    public boolean isLetsGoButtonPresent() {
        try {
            CommonFunctions.waitForElementPresent(driver, letsGoButton);
            CommonFunctions.waitForElemenetClickable(driver, letsGoButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
