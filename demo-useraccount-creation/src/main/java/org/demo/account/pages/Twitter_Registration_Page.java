package org.demo.account.pages;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.demo.account.common.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Twitter_Registration_Page {

    private static final String HTTPS_TWITTER_COM = "https://twitter.com/";
    private WebDriver driver;
    private Logger logger = Logger.getLogger(Twitter_Registration_Page.class.getName());

    @FindBy(xpath = "//span[contains(text(), \"Sign up\") and contains(text(), \"email\")]/ancestor::a[contains(@href, \"signup\")]")
    private WebElement signUpButton;

    public Twitter_Registration_Page(WebDriver driver) {
        this.driver = driver;
        driver.get(HTTPS_TWITTER_COM);
        logger.log(Level.INFO, "Navigated to URL: {0}", HTTPS_TWITTER_COM);
        PageFactory.initElements(driver, this);
    }

    public Twitter_Registration_Popup_page clickOnSignUpByEmailButton() {
        CommonFunctions.waitForElementPresent(driver, signUpButton);
        CommonFunctions.waitForElemenetClickable(driver, signUpButton);
        signUpButton.click();
        logger.info("Clicked on SignUp button.");
        return new Twitter_Registration_Popup_page(driver);
    }

}
