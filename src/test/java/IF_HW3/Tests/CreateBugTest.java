package IF_HW3.Tests;

import IF_HW3.Pages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBugTest extends BaseTest {

    @Test
    void createBugAndCloseTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(username, password);
        waitForPageLoad();

        ProjectPage projectPage = dashboardPage.openTestProject();
        int initialCount = projectPage.getCurrentIssueCount();

        CreateIssuePage createIssuePage = new CreateIssuePage();
        createIssuePage.openCreateIssueDialog();

        String bugSummary = "Test Bug Full Flow " + System.currentTimeMillis();
        String description = "Описание бага: проверка полного цикла работы с задачей";
        String environment = "Окружение: Windows 10, Chrome последней версии";

        createIssuePage.createBugWithDescription(bugSummary, description, environment);
        projectPage.waitForCounterUpdate(initialCount + 1);

        SearchPage searchPage = new SearchPage();
        searchPage.openSearchPage();
        searchPage.searchMyRecentIssues();

        searchPage.clickOnLinkedIssue(); // Переходим в TEST-121544

        // Проверяем TEST-121544
        IssuePage issuePage = new IssuePage();
        assertEquals("СДЕЛАТЬ", issuePage.getStatus());
        assertEquals("Version 2.0", issuePage.getFixVersion());

        // Возвращаемся к созданной задаче
        searchPage.openSearchPage();
        searchPage.searchMyRecentIssues();

        // ТЕСТ 5: Закрываем созданную задачу
        assertEquals("СДЕЛАТЬ", issuePage.getStatus());
        searchPage.moveToDone();
        issuePage.waitForStatus("ГОТОВО");
        assertEquals("ГОТОВО", issuePage.getStatus());

        System.out.println("ТЕСТ 5 УСПЕШНО");
    }
}