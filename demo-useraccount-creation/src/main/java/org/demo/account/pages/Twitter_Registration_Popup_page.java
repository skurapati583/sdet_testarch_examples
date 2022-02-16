package org.demo.account.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.demo.account.common.CommonFunctions;
import org.demo.account.model.NewUserModel;
import org.demo.account.model.PasswordGenerator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Twitter_Registration_Popup_page {

    private WebDriver driver;
    private Logger logger = Logger.getLogger(Twitter_Registration_Popup_page.class.getName());

    @FindBy(name = "name")
    private WebElement nameTextBox;

    @FindBy(xpath = "//span[text()='Use email instead']/parent::div")
    private WebElement emailOrPhoneLink;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(xpath = "(//select[contains(@id, \"SELECTOR\")])[last()-2]")
    private WebElement monthDropDown;

    @FindBy(xpath = "(//select[contains(@id, \"SELECTOR\")])[last()-1]")
    private WebElement dayDropDown;

    @FindBy(xpath = "(//select[contains(@id, \"SELECTOR\")])[last()]")
    private WebElement yearDropDown;

    @FindBy(xpath = "//span[text()='Next']/ancestor::div[@role='button']")
    private WebElement nextButton;

    @FindBy(xpath = "//span[text()='Sign up']/ancestor::div[@role='button']")
    private WebElement signUpButton;

    @FindBy(name = "verfication_code")
    private WebElement verificationCodeTextBox;

    @FindBy(name = "password")
    private WebElement newPasswordTextBox;

    @FindBy(xpath = "//div[@role='button']//span[text()='Skip for now']")
    private WebElement skipNowElement;

    public Twitter_Registration_Popup_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Twitter_Registration_Popup_page enterRegistrationFields(NewUserModel user) {
        CommonFunctions.waitForElementPresent(driver, nameTextBox);
        nameTextBox.sendKeys(user.name);
        logger.log(Level.INFO, "Entered username: {0} ", user.name);
        emailOrPhoneLink.click();
        logger.info("Clicked on email or phone hyperlink.");
        CommonFunctions.enterCommand(driver, emailTextBox, Keys.COMMAND, "v");
        user.email = emailTextBox.getAttribute("value");
        logger.log(Level.INFO, "Entered email: {0} ", user.email);
        selectDropDownValue(monthDropDown, user.month);
        selectDropDownValue(dayDropDown, user.day);
        selectDropDownValue(yearDropDown, user.year);
        nextButton.click();
        logger.info("Click on next button.");
        nextButton.click();
        logger.info("Click on next button.");
        clickSignUpButton();
        return new Twitter_Registration_Popup_page(driver);
    }

    public Twitter_Registration_Popup_page enterVerificationCode(String code) {
        CommonFunctions.waitForElementPresent(driver, verificationCodeTextBox);
        verificationCodeTextBox.sendKeys(code);
        logger.log(Level.INFO, "Entered verification code: {0}", code);
        return new Twitter_Registration_Popup_page(driver);
    }

    private void selectDropDownValue(WebElement dropdown, String value) {
        CommonFunctions.waitForElementPresent(driver, dropdown);
        CommonFunctions.waitForElemenetClickable(driver, dropdown);
        Select selectDropDown = new Select(dropdown);
        selectDropDown.selectByVisibleText(value);
        logger.log(Level.INFO, "Selected dropdown value: {0}", value);
    }

    public void clickSignUpButton() {
        CommonFunctions.waitForElementPresent(driver, signUpButton);
        CommonFunctions.waitForElemenetClickable(driver, signUpButton);
        signUpButton.click();
        logger.info("Clicked on sign up button.");
    }

    public Twitter_Registration_Popup_page clickNextButton() {
        CommonFunctions.waitForElementPresent(driver, nextButton);
        CommonFunctions.waitForElemenetClickable(driver, nextButton);
        nextButton.click();
        logger.info("Clicked on next button.");
        return new Twitter_Registration_Popup_page(driver);
    }

    public Twitter_Registration_Popup_page enterNewPassword() {
        CommonFunctions.waitForElementPresent(driver, newPasswordTextBox);
        newPasswordTextBox.sendKeys(new String(PasswordGenerator.generateNewPassword()));
        return new Twitter_Registration_Popup_page(driver);
    }

    public Twitter_Starting_Blank_Page skipBanner() {
        CommonFunctions.waitForElementPresent(driver, skipNowElement);
        CommonFunctions.waitForElemenetClickable(driver, skipNowElement);
        skipNowElement.click();
        return new Twitter_Starting_Blank_Page(driver);
    }
}