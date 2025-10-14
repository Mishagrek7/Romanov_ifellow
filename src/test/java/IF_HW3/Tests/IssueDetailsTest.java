package IF_HW3.Tests;

import IF_HW3.Pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Jira Automation")
@Feature("Детали задачи")
@Story("Проверка деталей связанной задачи TEST-121544")
public class IssueDetailsTest extends BaseTest {

    @Test
    @DisplayName("Тест проверки деталей задачи")
    @Description("Создание задачи, поиск и проверка деталей связанной задачи TEST-121544")
    @Severity(SeverityLevel.NORMAL)
    @Owner("AT3")
    void checkIssueDetailsTest() {
        Allure.step("Шаг 1: Авторизация в системе", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            waitForPageLoad();
        });

        Allure.step("Шаг 2: Переход в проект 'Test'", () -> {
            DashboardPage dashboardPage = new DashboardPage();
            dashboardPage.openTestProject();
        });

        int initialCount = Allure.step("Шаг 3: Получение начального счетчика задач",
                () -> new ProjectPage().getCurrentIssueCount());

        Allure.step("Шаг 4: Создание новой задачи", () -> {
            CreateIssuePage createIssuePage = new CreateIssuePage();
            createIssuePage.openCreateIssueDialog();

            String bugSummary = "Test Bug for Details " + System.currentTimeMillis();
            createIssuePage.createBugWithDescription(bugSummary, "Описание", "Окружение");

            ProjectPage projectPage = new ProjectPage();
            projectPage.waitForCounterUpdate(initialCount + 1);
        });

        Allure.step("Шаг 5: Поиск и переход по связанной задаче", () -> {
            SearchPage searchPage = new SearchPage();
            searchPage.openSearchPage();
            searchPage.searchMyRecentIssues();
            searchPage.clickOnLinkedIssue();
        });

        Allure.step("Шаг 6: Проверка деталей задачи TEST-121544", () -> {
            IssuePage issuePage = new IssuePage();

            Allure.step("Проверка статуса задачи", () -> {
                assertEquals("СДЕЛАТЬ", issuePage.getStatus(),
                        "Статус задачи должен быть 'СДЕЛАТЬ'");
            });

            Allure.step("Проверка версии исправления", () -> {
                assertEquals("Version 2.0", issuePage.getFixVersion(),
                        "Fix Version должен быть 'Version 2.0'");
            });
        });

        System.out.println("ТЕСТ 4 УСПЕШНО: детали связанной задачи TEST-121544 проверены");
    }
}