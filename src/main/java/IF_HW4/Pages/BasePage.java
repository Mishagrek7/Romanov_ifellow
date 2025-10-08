package IF_HW4.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

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
}