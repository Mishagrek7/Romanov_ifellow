package IF_HW4.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
    private SelenideElement usernameField = $x("//input[@id='login-form-username']");
    private SelenideElement passwordField = $x("//input[@id='login-form-password']");
    private SelenideElement loginButton = $x("//input[@id='login']");
    private SelenideElement errorMessage = $x("//div[contains(@class, 'aui-message-error')]");

    public DashboardPage login(String username, String password) {
        waitForElement(usernameField);
        setValueWithCheck(usernameField, username);
        setValueWithCheck(passwordField, password);
        clickWithRetry(loginButton);
        return new DashboardPage();
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.is(visible);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isLoginPageDisplayed() {
        return usernameField.is(visible) && passwordField.is(visible);
    }
}