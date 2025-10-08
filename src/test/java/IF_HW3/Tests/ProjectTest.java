package IF_HW3.Tests;

import IF_HW3.Pages.DashboardPage;
import IF_HW3.Pages.LoginPage;
import IF_HW3.Pages.ProjectPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest extends BaseTest {

    @Test
    void navigateToProjectTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(username, password);
        waitForPageLoad();

        ProjectPage projectPage = dashboardPage.openTestProject();

        assertTrue(projectPage.isProjectPageLoaded());

        System.out.println("ТЕСТ 2 УСПЕШНО: переход в проект Test выполнен");
    }
}