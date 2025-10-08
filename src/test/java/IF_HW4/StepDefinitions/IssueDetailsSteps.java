package IF_HW4.StepDefinitions;

import IF_HW4.Pages.*;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.jupiter.api.Assertions.*;

public class IssueDetailsSteps {

    private final TestContext testContext;

    public IssueDetailsSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("Пользователь создает баг для проверки деталей")
    public void пользователь_создает_баг_для_проверки_деталей() {
        testContext.initialCount = testContext.projectPage.getCurrentIssueCount();
        testContext.createIssuePage = new CreateIssuePage();
        testContext.createIssuePage.openCreateIssueDialog();

        String bugSummary = "Test Bug for Details " + System.currentTimeMillis();
        testContext.createIssuePage.createBugWithDescription(bugSummary, "Описание", "Окружение");
        testContext.projectPage.waitForCounterUpdate(testContext.initialCount + 1);
    }

    @When("Пользователь переходит в связанную задачу {string}")
    public void пользователь_переходит_в_связанную_задачу(String issueKey) {
        testContext.searchPage.clickOnLinkedIssue();
        testContext.issuePage = new IssuePage();
    }

    @Then("Статус задачи {string}")
    public void статус_задачи(String expectedStatus) {
        assertEquals(expectedStatus, testContext.issuePage.getStatus());
    }

    @And("Версия исправления {string}")
    public void версия_исправления(String expectedVersion) {
        assertEquals(expectedVersion, testContext.issuePage.getFixVersion());
    }

    @Then("Статус связанной задачи {string}")
    public void статус_связанной_задачи(String expectedStatus) {
        assertEquals(expectedStatus, testContext.issuePage.getStatus());
    }

    @And("Версия исправления связанной задачи {string}")
    public void версия_исправления_связанной_задачи(String expectedVersion) {
        assertEquals(expectedVersion, testContext.issuePage.getFixVersion());
    }
}