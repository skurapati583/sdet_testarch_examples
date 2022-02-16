package org.demo.account.pages;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.demo.account.common.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_Email_Page {

    private static final String HTTPS_TEMP_MAIL_ORG_EN = "https://temp-mail.org/en/";
    private WebDriver driver;
    private Logger logger = Logger.getLogger(Create_Email_Page.class.getName());

    @FindBy(id = "click-to-copy")
    private WebElement copyButton;

    @FindBy(xpath = "//a[contains(text(), 'is your Twitter verification code')]")
    private WebElement emailText;

    public Create_Email_Page(WebDriver driver) {
        this.driver = driver;
        driver.get(HTTPS_TEMP_MAIL_ORG_EN);
        logger.log(Level.INFO, "Navigated to URL: {0}", HTTPS_TEMP_MAIL_ORG_EN);
        PageFactory.initElements(driver, this);
        CommonFunctions.waitForElementPresent(driver, copyButton);
    }

    public String getNewEmail() {
        CommonFunctions.waitForElemenetClickable(driver, copyButton);
        String email = null;
        copyButton.click();
        logger.log(Level.INFO, "Email found is: {0}", email);
        return email;
    }

    public String getVerificationCode() {
        CommonFunctions.waitForElementPresent(driver, this.emailText);
        return this.emailText.getText().split("\\s")[0];
    }

}
