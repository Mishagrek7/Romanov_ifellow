package IF_HW3.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public abstract class BasePage {

    protected void waitForElement(SelenideElement element) {
        element.shouldBe(visible, Duration.ofSeconds(10));
    }

    protected void clickWithRetry(SelenideElement element) {
        element.shouldBe(interactable, Duration.ofSeconds(10)).click();
    }

    protected void setValueWithCheck(SelenideElement element, String value) {
        element.shouldBe(visible).clear();
        element.setValue(value);
        element.shouldHave(value(value));
    }

    protected void setPasswordSafely(SelenideElement passwordField, String password) {
        passwordField.shouldBe(visible).clear();

        executeJavaScript(
                "arguments[0].value = arguments[1]",
                passwordField,
                password
        );

        passwordField.shouldBe(enabled);
    }

    protected void setPasswordWithActions(SelenideElement passwordField, String password) {
        passwordField.shouldBe(visible).click();

        actions()
                .sendKeys(password)
                .perform();
    }
}