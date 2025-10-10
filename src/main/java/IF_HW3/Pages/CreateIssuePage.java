package IF_HW3.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateIssuePage extends BasePage {
    private final SelenideElement createButton = $x("//a[@id='create_link']");
    private final SelenideElement summaryField = $x("//input[@id='summary']");
    private final SelenideElement submitButton = $x("//input[@id='create-issue-submit']");

    private final SelenideElement descriptionFrame = $x("//div[@id='description-wiki-edit']//iframe[@id='mce_0_ifr']");
    private final SelenideElement descriptionField = $x("//body[@id='tinymce' and @data-id='mce_0' and @contenteditable='true']");
    private final SelenideElement environmentFrame = $x("//div[@id='environment-wiki-edit']//iframe[@id='mce_6_ifr']");
    private final SelenideElement environmentField = $x("//body[@id='tinymce' and @data-id='mce_6' and @contenteditable='true']");

    private final SelenideElement assignToMeButton = $x("//button[@id='assign-to-me-trigger']");
    private final SelenideElement fixVersionsField = $x("//select[@id='fixVersions']");
    private final SelenideElement issueLinksField = $x("//textarea[@id='issuelinks-issues-textarea']");
    private final SelenideElement successMessage = $x("//div[contains(@class, 'aui-message-success')]");

    public void openCreateIssueDialog() {
        clickWithRetry(createButton);
        summaryField.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void createBugWithDescription(String summary, String description, String environment) {
        setValueWithCheck(summaryField, summary);

        switchTo().frame(descriptionFrame);
        descriptionField.shouldBe(visible).setValue(description);
        switchTo().defaultContent();

        switchTo().frame(environmentFrame);
        environmentField.shouldBe(visible).setValue(environment);
        switchTo().defaultContent();

        fixVersionsField.selectOptionContainingText("Version 2.0");
        clickWithRetry(assignToMeButton);

        setValueWithCheck(issueLinksField, "TEST-121544");
        issueLinksField.pressEnter();

        clickWithRetry(submitButton);
        successMessage.shouldBe(visible, Duration.ofSeconds(10)); // Ждем сообщения об успехе
    }
}