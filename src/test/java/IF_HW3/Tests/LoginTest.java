package IF_HW3.Tests;

import IF_HW3.Pages.DashboardPage;
import IF_HW3.Pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    void authorizationTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(username, password);
        waitForPageLoad();

        assertTrue(dashboardPage.isUserLoggedIn());

        System.out.println("ТЕСТ 1 УСПЕШНО: авторизация прошла успешно");
    }
}