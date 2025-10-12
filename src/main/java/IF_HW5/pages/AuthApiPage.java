package IF_HW5.pages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class AuthApiPage {

    private String registerUrl;
    private String loginUrl;
    private String logoutUrl;

    public AuthApiPage(String registerUrl, String loginUrl, String logoutUrl) {
        this.registerUrl = registerUrl;
        this.loginUrl = loginUrl;
        this.logoutUrl = logoutUrl;
    }

    public Response register(String jsonContent) {
        return given()
                .baseUri(registerUrl)
                .header("Content-Type", "application/json")
                .body(jsonContent)
                .when()
                .post("");
    }

    public Response login(String jsonContent) {
        return given()
                .baseUri(loginUrl)
                .header("Content-Type", "application/json")
                .body(jsonContent)
                .when()
                .post("");
    }

    public Response logout(String token) {
        return given()
                .baseUri(logoutUrl)
                .header("Authorization", token)
                .when()
                .get("");
    }

    public Response logoutWithInvalidToken() {
        String randomValidUUID = UUID.randomUUID().toString();
        return given()
                .baseUri(logoutUrl)
                .header("Authorization", randomValidUUID)
                .when()
                .get("");
    }

    public String readJsonFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/user-credentials.json")));
    }

    public String modifyJsonUsername(String jsonContent, String newUsername) {
        JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        jsonObject.addProperty("username", newUsername);
        return jsonObject.toString();
    }

    public String modifyJsonPassword(String jsonContent, String newPassword) {
        JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        jsonObject.addProperty("password", newPassword);
        return jsonObject.toString();
    }

    public String extractToken(String responseBody) {
        return responseBody.substring("token : ".length()).trim();
    }
}