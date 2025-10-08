package IF_HW3.Tests;

import IF_HW3.Pages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IssueDetailsTest extends BaseTest {

    @Test
    void checkIssueDetailsTest() {
        // Шаг 1: Авторизация
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(username, password);
        waitForPageLoad();

        // Шаг 2: Перейти в проект "Test"
        ProjectPage projectPage = dashboardPage.openTestProject();

        // Шаг 3: Создать задачу
        int initialCount = projectPage.getCurrentIssueCount();
        CreateIssuePage createIssuePage = new CreateIssuePage();
        createIssuePage.openCreateIssueDialog();

        String bugSummary = "Test Bug for Details " + System.currentTimeMillis();
        createIssuePage.createBugWithDescription(bugSummary, "Описание", "Окружение");
        projectPage.waitForCounterUpdate(initialCount + 1);

        // Шаг 4: Найти созданную задачу и перейти в связанную задачу TEST-121544
        SearchPage searchPage = new SearchPage();
        searchPage.openSearchPage();
        searchPage.searchMyRecentIssues();
        searchPage.clickOnLinkedIssue(); // Кликаем на созданную задачу

        // Шаг 5: Проверить детали связанной задачи TEST-121544
        IssuePage issuePage = new IssuePage();
        assertEquals("СДЕЛАТЬ", issuePage.getStatus());
        assertEquals("Version 2.0", issuePage.getFixVersion());

        System.out.println("ТЕСТ 4 УСПЕШНО: детали связанной задачи TEST-121544 проверены");
    }
}