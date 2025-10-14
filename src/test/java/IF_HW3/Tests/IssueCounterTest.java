package IF_HW3.Tests;

import IF_HW3.Pages.CreateIssuePage;
import IF_HW3.Pages.DashboardPage;
import IF_HW3.Pages.LoginPage;
import IF_HW3.Pages.ProjectPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Jira Automation")
@Feature("Счетчик задач")
@Story("Проверка увеличения счетчика задач после создания бага")
public class IssueCounterTest extends BaseTest {

    @Test
    @DisplayName("Тест проверки счетчика задач")
    @Description("Проверка увеличения счетчика задач на 1 после создания нового бага")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("AT3")
    void checkIssueCounterTest() {
        Allure.step("Шаг 1: Авторизация в системе", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            waitForPageLoad();
        });

        int initialIssueCount = Allure.step("Шаг 2: Получение начального значения счетчика",
                () -> {
                    DashboardPage dashboardPage = new DashboardPage();
                    ProjectPage projectPage = dashboardPage.openTestProject();
                    return projectPage.getCurrentIssueCount();
                });

        Allure.step("Шаг 3: Создание новой задачи", () -> {
            CreateIssuePage createIssuePage = new CreateIssuePage();
            createIssuePage.openCreateIssueDialog();

            String bugSummary = "Test Bug for Counter " + System.currentTimeMillis();
            createIssuePage.createBugWithDescription(bugSummary, "Описание", "Окружение");
        });

        int finalIssueCount = Allure.step("Шаг 4: Проверка обновления счетчика",
                () -> {
                    ProjectPage projectPage = new ProjectPage();
                    projectPage.waitForCounterUpdate(initialIssueCount + 1);
                    return projectPage.getCurrentIssueCount();
                });

        Allure.step("Шаг 5: Верификация результата", () -> {
            assertEquals(initialIssueCount + 1, finalIssueCount,
                    "Счетчик задач должен увеличиться на 1");
        });

        System.out.println("ТЕСТ 3 УСПЕШНО: счетчик увеличился с " + initialIssueCount + " до " + finalIssueCount);
    }
}