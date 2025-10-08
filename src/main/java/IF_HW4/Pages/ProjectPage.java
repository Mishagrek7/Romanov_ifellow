package IF_HW4.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage extends BasePage {
    private SelenideElement issueCounter = $x("//div[@class='showing']/span");

    public String getIssueCounterText() {
        waitForElement(issueCounter);
        return issueCounter.getText();
    }

    public int getCurrentIssueCount() {
        String counterText = getIssueCounterText();
        Pattern pattern = Pattern.compile("из (\\d+)");
        Matcher matcher = pattern.matcher(counterText);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Не удалось распарсить счетчик задач: " + counterText);
    }

    public void waitForCounterUpdate(int expectedCount) {
        for (int i = 0; i < 10; i++) {
            refresh();
            issueCounter.shouldBe(visible, Duration.ofSeconds(5));

            if (getCurrentIssueCount() == expectedCount) {
                return;
            }
        }
        throw new RuntimeException("Счетчик не обновился за 10 попыток");
    }

    public boolean isProjectPageLoaded() {
        return issueCounter.is(visible);
    }
}