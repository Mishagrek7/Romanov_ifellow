package IF_HW3.Pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
    private final SelenideElement usernameField = $x("//input[@id='login-form-username']");
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']");
    private final SelenideElement loginButton = $x("//input[@id='login']");
    public DashboardPage login(String username, String password) {
        waitForElement(usernameField);
        setValueWithCheck(usernameField, username);
        setValueWithCheck(passwordField, password);
        clickWithRetry(loginButton);
        return new DashboardPage();
    }

}