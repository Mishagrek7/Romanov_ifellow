package IF_HW3.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage extends BasePage {
    private final SelenideElement projectsMenu = $x("//a[@id='browse_link']");
    private final SelenideElement testProjectLink = $x("//a[@id='admin_main_proj_link_lnk']");
    private final SelenideElement userMenu = $x("//a[@id='header-details-user-fullname']");

    public ProjectPage openTestProject() {
        clickWithRetry(projectsMenu);
        clickWithRetry(testProjectLink);
        return new ProjectPage();
    }

    public boolean isUserLoggedIn() {
        return userMenu.is(visible);
    }

}