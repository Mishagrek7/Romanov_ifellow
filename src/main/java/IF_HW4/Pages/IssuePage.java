package IF_HW4.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class IssuePage extends BasePage {
    private SelenideElement statusField = $x("//span[@id='status-val']");
    private SelenideElement fixVersionField = $x("//span[@id='fixVersions-field']");

    public String getStatus() {
        return statusField.getText();
    }

    public String getFixVersion() {
        waitForElement(fixVersionField);
        return fixVersionField.getText().trim();
    }

    public void waitForStatus(String expectedStatus) {
        statusField.shouldHave(text(expectedStatus), Duration.ofSeconds(10)); // Правильное ожидание
    }
}