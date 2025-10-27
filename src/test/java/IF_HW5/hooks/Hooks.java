package IF_HW5.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.qameta.allure.Allure;

public class Hooks {

    @Before
    public void beforeScenario() {
        Allure.step("Начало сценария");
    }

    @After
    public void afterScenario() {
        Allure.step("Завершение сценария");
    }
}