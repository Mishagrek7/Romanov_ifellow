package IF_HW4.Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage {
    private SelenideElement searchMenu = $x("//a[@id='find_link']");
    private SelenideElement searchForIssues = $x("//a[@id='issues_new_search_link_lnk']");
    private SelenideElement assigneeField = $x("//div[@data-id='assignee']");
    private SelenideElement currentUserLink = $x("//label[@data-descriptor-title='Текущий пользователь']");
    private SelenideElement businessProcess = $x("//a[@id='opsbar-transitions_more']");
    private SelenideElement doneButton = $x("//span[text()='Выполнено']/..");
    private SelenideElement searchResults = $x("//*[@id=\"main\"]//ol"); // Ждем появления результатов

    public void openSearchPage() {
        clickWithRetry(searchMenu);
        clickWithRetry(searchForIssues);
        searchResults.shouldBe(visible, Duration.ofSeconds(10)); // Ждем загрузки страницы поиска
    }

    public void searchMyRecentIssues() {
        clickWithRetry(assigneeField);
        clickWithRetry(currentUserLink);
        searchResults.shouldBe(visible, Duration.ofSeconds(10)); // Ждем обновления результатов
    }

    public void clickOnSpecificIssue() {
        $x("//a[@data-issue-key='TEST-121544']").click();
        $x("//span[@id='status-val']").shouldBe(visible, Duration.ofSeconds(10)); // Ждем загрузки страницы задачи
    }

    public void moveToDone() {
        clickWithRetry(businessProcess);
        clickWithRetry(doneButton);
        $x("//span[@id='status-val']").shouldHave(text("ГОТОВО"), Duration.ofSeconds(10)); // Ждем изменения статуса
    }

    public void clickOnLinkedIssue() {
        // Кликаем на связанную задачу TEST-121544 в блоке "Связи запроса"
        $x("//div[@id='linkingmodule']//a[@data-issue-key='TEST-121544']").click();
        sleep(3000);
    }
}