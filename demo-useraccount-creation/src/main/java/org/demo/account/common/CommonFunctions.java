package org.demo.account.common;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {

    private CommonFunctions() {
    }

    public static void waitForElementPresent(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementNotPresent(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElemenetClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void enterCommand(WebDriver driver, WebElement element, Keys command, String extra) {
        waitForElementPresent(driver, element);
        Actions actionsBuilder = new Actions(driver);
        actionsBuilder.moveToElement(element).click(element).keyDown(command).sendKeys(extra).keyUp(command);
        actionsBuilder.build().perform();
    }

}
