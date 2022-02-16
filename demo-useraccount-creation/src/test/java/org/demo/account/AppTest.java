package org.demo.account;

import java.util.logging.Logger;

import org.demo.account.model.NewUserModel;
import org.demo.account.pages.Create_Email_Page;
import org.demo.account.pages.Twitter_Registration_Page;
import org.demo.account.pages.Twitter_Registration_Popup_page;
import org.demo.account.pages.Twitter_Starting_Blank_Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class AppTest {

    private static final String WELCOME_TO_TWITTER = "Welcome to Twitter!";
    Logger logger = Logger.getLogger(AppTest.class.getName());

    @Test
    public void testTwitterAccountCreation(ChromeDriver driver) {

        driver.manage().window().maximize();

        Create_Email_Page createEmailPage = new Create_Email_Page(driver);

        logger.info("Current page: Create Email Page");
        ;

        NewUserModel user = new NewUserModel(createEmailPage.getNewEmail());

        String emailCreationWindow = driver.getWindowHandle();

        logger.info("Current Window handle is: " + emailCreationWindow);

        driver.switchTo().newWindow(WindowType.TAB);

        logger.info("Switching to new tab.");

        String twitterUserCreationWindow = driver.getWindowHandle();

        logger.info("The new handle name is: " + twitterUserCreationWindow);

        Twitter_Registration_Popup_page registrationPopup = new Twitter_Registration_Page(driver)
                .clickOnSignUpByEmailButton()
                .enterRegistrationFields(user);

        driver.switchTo().window(emailCreationWindow);

        String verificationCode = createEmailPage.getVerificationCode();

        driver.switchTo().window(twitterUserCreationWindow);

        Twitter_Starting_Blank_Page homePage = registrationPopup
                .enterVerificationCode(verificationCode)
                .clickNextButton()
                .enterNewPassword()
                .clickNextButton()
                .skipBanner();

        assertThat(homePage.getWelcomeHeaderText()).isEqualTo(WELCOME_TO_TWITTER);

        assertThat(homePage.isLetsGoButtonPresent()).isTrue();

    }

}
