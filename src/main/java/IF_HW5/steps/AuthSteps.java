package IF_HW5.steps;

import IF_HW5.config.TestConfig;
import IF_HW5.pages.AuthApiPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.qameta.allure.Allure;

import java.util.UUID;

import static org.junit.Assert.*;

public class AuthSteps {

    private Response response;
    private String token;
    private String jsonContent;
    private final AuthApiPage authPage;

    public AuthSteps() {
        this.authPage = TestConfig.getInstance().getAuthPage();
    }

    @Given("пользователь загружает credentials из JSON файла")
    public void loadCredentialsFromJson() throws Exception {
        jsonContent = authPage.readJsonFile();
        Allure.step("Загружены credentials из JSON файла");
    }

    @When("пользователь регистрируется с этими credentials")
    public void registerWithCredentials() {
        response = authPage.register(jsonContent);
        Allure.step("Выполнена регистрация с статусом: " + response.getStatusCode());
    }

    @When("пользователь логинится с этими credentials")
    public void loginWithCredentials() {
        response = authPage.login(jsonContent);
        Allure.step("Выполнен логин с статусом: " + response.getStatusCode());

        // Сохраняем токен сразу после успешного логина
        if (response.getStatusCode() == 200) {
            String responseBody = response.getBody().asString();
            if (responseBody.startsWith("token : ")) {
                token = authPage.extractToken(responseBody);
                Allure.step("Токен автоматически сохранен после логина: " + token);
            }
        }
    }

    @When("пользователь логинится с неверным именем пользователя")
    public void loginWithWrongUsername() throws Exception {
        String modifiedJson = authPage.modifyJsonUsername(jsonContent, "nonexistentuser");
        response = authPage.login(modifiedJson);
        Allure.step("Выполнен логин с неверным именем пользователя");
    }

    @When("пользователь логинится с неверным паролем")
    public void loginWithWrongPassword() throws Exception {
        String modifiedJson = authPage.modifyJsonPassword(jsonContent, "wrongpassword");
        response = authPage.login(modifiedJson);
        Allure.step("Выполнен логин с неверным паролем");
    }

    @When("пользователь разлогинивается с валидным токеном")
    public void logoutWithValidToken() {
        assertNotNull("Токен должен быть сохранен перед logout", token);
        response = authPage.logout(token);
        Allure.step("Выполнен logout с валидным токеном");
    }

    @When("пользователь разлогинивается с невалидным токеном")
    public void logoutWithInvalidToken() {
        response = authPage.logoutWithInvalidToken();
        Allure.step("Выполнен logout с невалидным токеном");
    }

    @Then("ответ имеет статус код {int}")
    public void responseHasStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
        Allure.step("Проверен статус код: " + expectedStatusCode);
    }

    @Then("тело ответа содержит {string}")
    public void responseBodyContains(String expectedBody) {
        assertEquals(expectedBody, response.getBody().asString());
        Allure.step("Проверено тело ответа: " + expectedBody);
    }

    @Then("в ответе есть валидный UUID токен")
    public void responseHasValidToken() {
        String responseBody = response.getBody().asString();
        assertTrue("Response should start with 'token : '", responseBody.startsWith("token : "));

        token = authPage.extractToken(responseBody);
        UUID uuid = UUID.fromString(token);
        assertNotNull(uuid);
        Allure.step("Получен валидный UUID токен: " + token);
    }

    @Then("токен сохранен для последующих запросов")
    public void tokenSavedForFutureRequests() {
        assertNotNull("Токен должен быть сохранен", token);
        Allure.step("Токен сохранен для последующих запросов: " + token);
    }

    @When("токен сохранен из ответа")
    public void saveTokenFromResponse() {
        String responseBody = response.getBody().asString();
        if (responseBody.startsWith("token : ")) {
            token = authPage.extractToken(responseBody);
            Allure.step("Токен явно сохранен из ответа: " + token);
        } else {
            throw new AssertionError("В ответе нет токена для сохранения");
        }
    }
}