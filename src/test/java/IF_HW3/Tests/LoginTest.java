package IF_HW3.Tests;

import IF_HW3.Pages.DashboardPage;
import IF_HW3.Pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Jira Automation")
@Feature("Авторизация")
@Story("Успешная авторизация в системе")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Тест авторизации с валидными данными")
    @Description("Проверка успешной авторизации пользователя в системе Jira")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("AT3")
    void authorizationTest() {
        Allure.step("Шаг 1: Авторизация в системе", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            waitForPageLoad();
        });

        Allure.step("Шаг 2: Проверка успешной авторизации", () -> {
            DashboardPage dashboardPage = new DashboardPage();
            assertTrue(dashboardPage.isUserLoggedIn(),
                    "Пользователь должен быть авторизован");
        });

        System.out.println("ТЕСТ 1 УСПЕШНО: авторизация прошла успешно");
    }
}