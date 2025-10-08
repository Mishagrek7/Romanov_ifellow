package IF_HW5.Tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class ReqresTest extends BaseTest {

    @Test
    public void testCreateUserWithRealJsonFile() throws IOException {
        String baseUrl = getProperty("reqres.base.url");
        String apiKey = getProperty("reqres.api.key");
        String userJob = getProperty("user.job");

        // 1. Читаем реальный JSON файл
        String jsonFilePath = "src/test/resources/user-data.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        System.out.println("Исходный JSON из файла: " + jsonContent);

        // 2. Парсим JSON и модифицируем его
        JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();

        // Проверяем исходные данные
        String originalName = jsonObject.get("name").getAsString();
        assertEquals("Potato", originalName);

        // Меняем name "Potato" → "Tomato"
        jsonObject.addProperty("name", "Tomato");

        // Добавляем поле job
        jsonObject.addProperty("job", userJob);

        String modifiedJson = jsonObject.toString();
        System.out.println("Модифицированный JSON: " + modifiedJson);

        // 3. Проверяем модифицированные данные
        assertTrue("Должен содержать Tomato", modifiedJson.contains("Tomato"));
        assertTrue("Должен содержать Eat market", modifiedJson.contains("Eat market"));

        // 4. Отправляем POST запрос с модифицированным JSON
        Response response = given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .body(modifiedJson)
                .when()
                .post("/users");

        // 5. Проверяем статус 201
        assertEquals(201, response.getStatusCode());

        // 6. Проверяем данные в ответе
        response.then()
                .body("name", org.hamcrest.Matchers.equalTo("Tomato"))
                .body("job", org.hamcrest.Matchers.equalTo("Eat market"));

        // 7. Проверяем что ID и дата не null
        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("createdAt"));

        System.out.println("Пользователь создан из JSON файла: " + response.jsonPath().getString("id"));
    }

    @Test
    public void testCreateAndVerifyUserData() throws IOException {
        String baseUrl = getProperty("reqres.base.url");
        String apiKey = getProperty("reqres.api.key");
        String userJob = getProperty("user.job");

        String jsonFilePath = "src/test/resources/user-data.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        jsonObject.addProperty("name", "Tomato Workflow");
        jsonObject.addProperty("job", userJob);

        String modifiedJson = jsonObject.toString();

        Response createResponse = given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .body(modifiedJson)
                .when()
                .post("/users");

        assertEquals(201, createResponse.getStatusCode());

        String createdUserId = createResponse.jsonPath().getString("id");
        String createdUserName = createResponse.jsonPath().getString("name");
        String createdUserJob = createResponse.jsonPath().getString("job");

        assertNotNull("ID созданного пользователя не должен быть null", createdUserId);
        assertEquals("Tomato Workflow", createdUserName);
        assertEquals("Eat market", createdUserJob);

        System.out.println("Создан пользователь с ID: " + createdUserId);

        assertNotNull("createdAt не должен быть null", createResponse.jsonPath().getString("createdAt"));

        createResponse.then()
                .body("id", org.hamcrest.Matchers.notNullValue())
                .body("name", org.hamcrest.Matchers.equalTo("Tomato Workflow"))
                .body("job", org.hamcrest.Matchers.equalTo("Eat market"))
                .body("createdAt", org.hamcrest.Matchers.notNullValue());

        System.out.println("Данные пользователя корректны:");
        System.out.println("   - ID: " + createdUserId);
        System.out.println("   - Name: " + createdUserName);
        System.out.println("   - Job: " + createdUserJob);
        System.out.println("   - Created: " + createResponse.jsonPath().getString("createdAt"));
    }

    @Test
    public void testGetExistingUser() {
        String baseUrl = getProperty("reqres.base.url");
        String apiKey = getProperty("reqres.api.key");

        Response response = given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/users/2");

        assertEquals(200, response.getStatusCode());

        response.then()
                .body("data.id", org.hamcrest.Matchers.equalTo(2))
                .body("data.email", org.hamcrest.Matchers.equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", org.hamcrest.Matchers.equalTo("Janet"))
                .body("data.last_name", org.hamcrest.Matchers.equalTo("Weaver"))
                .body("data.avatar", org.hamcrest.Matchers.notNullValue());

        System.out.println("Получен существующий пользователь: Janet Weaver");
    }

    @Test
    public void testUserNotFoundScenario() {
        String baseUrl = getProperty("reqres.base.url");
        String apiKey = getProperty("reqres.api.key");

        Response response = given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/users/9999"); // Несуществующий ID

        assertEquals(404, response.getStatusCode());

        System.out.println("Сценарий 'пользователь не найден' работает корректно");
    }

    @Test
    public void testGetUsersList() {
        String baseUrl = getProperty("reqres.base.url");
        String apiKey = getProperty("reqres.api.key");

        Response response = given()
                .baseUri(baseUrl)
                .header("x-api-key", apiKey)
                .when()
                .get("/users?page=2");

        assertEquals(200, response.getStatusCode());

        response.then()
                .body("page", org.hamcrest.Matchers.equalTo(2))
                .body("data", org.hamcrest.Matchers.not(org.hamcrest.Matchers.empty()))
                .body("data.size()", org.hamcrest.Matchers.greaterThan(0))
                .body("data[0].id", org.hamcrest.Matchers.notNullValue())
                .body("data[0].email", org.hamcrest.Matchers.notNullValue())
                .body("data[0].first_name", org.hamcrest.Matchers.notNullValue())
                .body("data[0].last_name", org.hamcrest.Matchers.notNullValue());

        int usersCount = response.jsonPath().getInt("data.size()");
        System.out.println("Получен список пользователей: " + usersCount + " пользователей на странице 2");
    }
}