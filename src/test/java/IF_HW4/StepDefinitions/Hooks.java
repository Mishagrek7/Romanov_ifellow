package IF_HW4.StepDefinitions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        // Открываем браузер перед каждым сценарием
        Selenide.open("https://edujira.ifellow.ru");
        Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            Selenide.screenshot("failed_" + System.currentTimeMillis());
        }
        // Закрываем браузер после каждого сценария
        Selenide.closeWebDriver();
    }
}