package IF_HW4.StepDefinitions;

import IF_HW4.Pages.*;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class CreateBugSteps {

    private final TestContext testContext;

    public CreateBugSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("Пользователь создает баг с описанием и окружением")
    public void пользователь_создает_баг_с_описанием_и_окружением() {
        testContext.initialCount = testContext.projectPage.getCurrentIssueCount();
        testContext.createIssuePage = new CreateIssuePage();
        testContext.createIssuePage.openCreateIssueDialog();

        String bugSummary = "Test Bug Full Flow " + System.currentTimeMillis();
        String description = "Описание бага: проверка полного цикла работы с задачей";
        String environment = "Окружение: Windows 10, Chrome последней версии";

        testContext.createIssuePage.createBugWithDescription(bugSummary, description, environment);
        testContext.projectPage.waitForCounterUpdate(testContext.initialCount + 1);
    }

    @When("Пользователь возвращается к списку своих задач")
    public void пользователь_возвращается_к_списку_своих_задач() {
        testContext.searchPage.openSearchPage();
        testContext.searchPage.searchMyRecentIssues();
        testContext.issuePage = new IssuePage();
    }

    @When("Пользователь перемещает задачу в статус {string}")
    public void пользователь_перемещает_задачу_в_статус(String status) {
        assertEquals("СДЕЛАТЬ", testContext.issuePage.getStatus());
        testContext.searchPage.moveToDone();
    }

    @Then("Статус задачи изменяется на {string}")
    public void статус_задачи_изменяется_на(String expectedStatus) {
        testContext.issuePage.waitForStatus(expectedStatus);
        assertEquals(expectedStatus, testContext.issuePage.getStatus());
    }
}