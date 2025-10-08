package IF_HW4.Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import java.io.InputStream;
import java.util.Properties;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    protected static String baseUrl;
    protected static String username;
    protected static String password;

    @BeforeAll
    public static void setupAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        loadConfig();

        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    private static void loadConfig() {
        try (InputStream input = BaseTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            baseUrl = prop.getProperty("base.url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    protected void waitForPageLoad() {
        $x("//a[@id='browse_link']").shouldBe(visible, Duration.ofSeconds(10));
    }

    // Метод для открытия браузера
    protected void openBrowser() {
        open(baseUrl);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}