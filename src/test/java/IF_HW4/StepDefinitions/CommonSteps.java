package IF_HW4.StepDefinitions;

import IF_HW4.Pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class CommonSteps {

    private final TestContext testContext;

    // Конструктор для DI
    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Пользователь авторизован в системе")
    public void пользователь_авторизован_в_системе() {
        testContext.loginPage = new LoginPage();
        testContext.dashboardPage = testContext.loginPage.login("AT3", "Qwerty123");
        waitForPageLoad();
        assertTrue(testContext.dashboardPage.isUserLoggedIn());
    }

    @Given("Пользователь находится на странице проекта Test")
    public void пользователь_находится_на_странице_проекта_Test() {
        // Если пользователь еще не авторизован, авторизуем его
        if (testContext.dashboardPage == null) {
            пользователь_авторизован_в_системе();
        }
        testContext.projectPage = testContext.dashboardPage.openTestProject();
        assertTrue(testContext.projectPage.isProjectPageLoaded());
    }

    @When("Пользователь открывает страницу поиска задач")
    public void пользователь_открывает_страницу_поиска_задач() {
        testContext.searchPage = new SearchPage();
        testContext.searchPage.openSearchPage();
    }

    @When("Пользователь фильтрует задачи по текущему исполнителю")
    public void пользователь_фильтрует_задачи_по_текущему_исполнителю() {
        testContext.searchPage.searchMyRecentIssues();
    }

    @When("Пользователь создает новую задачу")
    public void пользователь_создает_новую_задачу() {
        testContext.initialCount = testContext.projectPage.getCurrentIssueCount();
        testContext.createIssuePage = new CreateIssuePage();
        testContext.createIssuePage.openCreateIssueDialog();

        String bugSummary = "Test Bug for Counter " + System.currentTimeMillis();
        testContext.createIssuePage.createBugWithDescription(bugSummary, "Описание", "Окружение");
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}