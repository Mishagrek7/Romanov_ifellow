package IF_HW4.StepDefinitions;

import IF_HW4.Pages.LoginPage;
import IF_HW4.Pages.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final TestContext testContext;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Пользователь находится на странице логина")
    public void пользователь_находится_на_странице_логина() {
        testContext.loginPage = new LoginPage();
    }

    @When("Пользователь вводит корректные учетные данные")
    public void пользователь_вводит_корректные_учетные_данные() {
        testContext.dashboardPage = testContext.loginPage.login("AT3", "Qwerty123");
        waitForPageLoad();
    }

    @Then("Пользователь успешно авторизуется в системе")
    public void пользователь_успешно_авторизуется_в_системе() {
        assertTrue(testContext.dashboardPage.isUserLoggedIn());
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}