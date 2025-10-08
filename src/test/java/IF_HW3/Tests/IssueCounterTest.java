package IF_HW3.Tests;

import IF_HW3.Pages.CreateIssuePage;
import IF_HW3.Pages.DashboardPage;
import IF_HW3.Pages.LoginPage;
import IF_HW3.Pages.ProjectPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IssueCounterTest extends BaseTest {

    @Test
    void checkIssueCounterTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(username, password);
        waitForPageLoad();

        ProjectPage projectPage = dashboardPage.openTestProject();

        int initialIssueCount = projectPage.getCurrentIssueCount();

        CreateIssuePage createIssuePage = new CreateIssuePage();
        createIssuePage.openCreateIssueDialog();

        String bugSummary = "Test Bug for Counter " + System.currentTimeMillis();
        createIssuePage.createBugWithDescription(bugSummary, "Описание", "Окружение");

        projectPage.waitForCounterUpdate(initialIssueCount + 1);
        int finalIssueCount = projectPage.getCurrentIssueCount();
        assertEquals(initialIssueCount + 1, finalIssueCount);

        System.out.println("ТЕСТ 3 УСПЕШНО: счетчик увеличился с " + initialIssueCount + " до " + finalIssueCount);
    }
}