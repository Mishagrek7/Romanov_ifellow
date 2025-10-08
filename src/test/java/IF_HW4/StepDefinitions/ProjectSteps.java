package IF_HW4.StepDefinitions;

import IF_HW4.Pages.DashboardPage;
import IF_HW4.Pages.ProjectPage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {

    private final TestContext testContext;

    public ProjectSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("Пользователь переходит в проект Test")
    public void пользователь_переходит_в_проект_Test() {
        testContext.projectPage = testContext.dashboardPage.openTestProject();
    }

    @Then("Страница проекта успешно загружается")
    public void страница_проекта_успешно_загружается() {
        assertTrue(testContext.projectPage.isProjectPageLoaded());
    }
}