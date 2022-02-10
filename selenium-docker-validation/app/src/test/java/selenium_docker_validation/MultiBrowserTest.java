package selenium_docker_validation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiBrowserTest {

    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String CHROME = "chrome";
    private static final String SELENIUM_GRID_URL = "http://localhost:4444";
    Logger logger = Logger.getLogger(MultiBrowserTest.class.getName());
    WebDriver driver = null;

    @ParameterizedTest
    @ValueSource(strings = { CHROME, EDGE, FIREFOX })
    void multiBrowserTest(String browserName) throws MalformedURLException {
        logger.info("Current browser is: " + browserName);

        switch (browserName) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), chromeOptions);
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), edgeOptions);
                break;
            default:
                FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
                break;
        }

        assumeThat(driver).isNotNull();

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");

        driver.quit();
    }
}
