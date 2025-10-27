package IF_HW3.Tests;

import IF_HW3.Pages.DashboardPage;
import IF_HW3.Pages.LoginPage;
import IF_HW3.Pages.ProjectPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Переход в проект Test")
public class ProjectTest extends BaseTest {

    @Test
    @DisplayName("Тест навигации в проект Test")
    @Description("Проверка успешного перехода в проект Test из дашборда")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("AT3")
    @Tag("TEST_2")
    void navigateToProjectTest() {
        Allure.step("Шаг 1: Авторизация в системе", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            waitForPageLoad();
        });

        Allure.step("Шаг 2: Переход в проект Test", () -> {
            DashboardPage dashboardPage = new DashboardPage();
            ProjectPage projectPage = dashboardPage.openTestProject();
            assertTrue(projectPage.isProjectPageLoaded(),
                    "Страница проекта должна быть загружена");
        });

        System.out.println("ТЕСТ 2 УСПЕШНО: переход в проект Test выполнен");
    }
}