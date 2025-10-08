package IF_HW3.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateIssuePage extends BasePage {
    private SelenideElement createButton = $x("//a[@id='create_link']");
    private SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");
    private SelenideElement summaryField = $x("//input[@id='summary']");
    private SelenideElement submitButton = $x("//input[@id='create-issue-submit']");

    private SelenideElement descriptionFrame = $x("//div[@id='description-wiki-edit']//iframe[@id='mce_0_ifr']");
    private SelenideElement descriptionField = $x("//body[@id='tinymce' and @data-id='mce_0' and @contenteditable='true']");
    private SelenideElement environmentFrame = $x("//div[@id='environment-wiki-edit']//iframe[@id='mce_6_ifr']");
    private SelenideElement environmentField = $x("//body[@id='tinymce' and @data-id='mce_6' and @contenteditable='true']");

    private SelenideElement assignToMeButton = $x("//button[@id='assign-to-me-trigger']");
    private SelenideElement fixVersionsField = $x("//select[@id='fixVersions']");
    private SelenideElement issueLinksField = $x("//textarea[@id='issuelinks-issues-textarea']");
    private SelenideElement successMessage = $x("//div[contains(@class, 'aui-message-success')]");

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