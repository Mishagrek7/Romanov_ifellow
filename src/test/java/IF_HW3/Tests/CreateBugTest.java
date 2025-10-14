package IF_HW3.Tests;

import IF_HW3.Pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Jira Automation")
@Feature("Полный цикл работы с задачей")
@Story("Создание, проверка и закрытие бага")
public class CreateBugTest extends BaseTest {

    @Test
    @DisplayName("Тест полного цикла создания и закрытия бага")
    @Description("Создание бага, проверка счетчика, переход по связанным задачам и закрытие")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("AT3")
    void createBugAndCloseTest() {
        Allure.step("Шаг 1: Авторизация в системе", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            waitForPageLoad();
        });

        int initialCount = Allure.step("Шаг 2: Получение начального счетчика задач",
                () -> {
                    DashboardPage dashboardPage = new DashboardPage();
                    ProjectPage projectPage = dashboardPage.openTestProject();
                    return projectPage.getCurrentIssueCount();
                });

        Allure.step("Шаг 3: Создание новой задачи с описанием", () -> {
            CreateIssuePage createIssuePage = new CreateIssuePage();
            createIssuePage.openCreateIssueDialog();

            String bugSummary = "Test Bug Full Flow " + System.currentTimeMillis();
            String description = "Описание бага: проверка полного цикла работы с задачей";
            String environment = "Окружение: Windows 10, Chrome последней версии";

            createIssuePage.createBugWithDescription(bugSummary, description, environment);

            ProjectPage projectPage = new ProjectPage();
            projectPage.waitForCounterUpdate(initialCount + 1);
        });

        Allure.step("Шаг 4: Поиск и переход по связанной задаче", () -> {
            SearchPage searchPage = new SearchPage();
            searchPage.openSearchPage();
            searchPage.searchMyRecentIssues();
            searchPage.clickOnLinkedIssue();
        });

        Allure.step("Шаг 5: Проверка деталей задачи TEST-121544", () -> {
            IssuePage issuePage = new IssuePage();

            Allure.step("Проверка статуса", () -> {
                assertEquals("СДЕЛАТЬ", issuePage.getStatus());
            });

            Allure.step("Проверка версии", () -> {
                assertEquals("Version 2.0", issuePage.getFixVersion());
            });
        });

        Allure.step("Шаг 6: Возврат к созданной задаче и закрытие", () -> {
            SearchPage searchPage = new SearchPage();
            searchPage.openSearchPage();
            searchPage.searchMyRecentIssues();

            IssuePage issuePage = new IssuePage();

            Allure.step("Проверка начального статуса", () -> {
                assertEquals("СДЕЛАТЬ", issuePage.getStatus());
            });

            Allure.step("Закрытие задачи", () -> {
                searchPage.moveToDone();
                issuePage.waitForStatus("ГОТОВО");
            });

            Allure.step("Проверка финального статуса", () -> {
                assertEquals("ГОТОВО", issuePage.getStatus());
            });
        });

        System.out.println("ТЕСТ 5 УСПЕШНО: полный цикл работы с задачей завершен");
    }
}