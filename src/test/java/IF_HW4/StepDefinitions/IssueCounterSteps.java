package IF_HW4.StepDefinitions;

import IF_HW4.Pages.ProjectPage;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class IssueCounterSteps {

    private final TestContext testContext;

    public IssueCounterSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("Счетчик задач увеличивается на {int}")
    public void счетчик_задач_увеличивается_на(int expectedIncrease) {
        testContext.projectPage.waitForCounterUpdate(testContext.initialCount + expectedIncrease);
        int finalIssueCount = testContext.projectPage.getCurrentIssueCount();
        assertEquals(testContext.initialCount + expectedIncrease, finalIssueCount);
    }
}