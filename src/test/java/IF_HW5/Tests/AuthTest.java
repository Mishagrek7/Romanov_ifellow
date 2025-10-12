package IF_HW5.Tests;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AuthTest extends BaseTest {

    @Test
    public void testRegistration() throws Exception {
        String jsonContent = authPage.readJsonFile();
        Response response = authPage.register(jsonContent);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testLoginUserNotFound() throws Exception {
        String jsonContent = authPage.readJsonFile();
        String modifiedJson = authPage.modifyJsonUsername(jsonContent, "nonexistentuser");
        Response response = authPage.login(modifiedJson);
        assertEquals(401, response.getStatusCode());
        assertEquals("not found", response.getBody().asString());
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        String jsonContent = authPage.readJsonFile();
        String modifiedJson = authPage.modifyJsonPassword(jsonContent, "wrongpassword");
        Response response = authPage.login(modifiedJson);
        assertEquals(401, response.getStatusCode());
        assertEquals("not right pass", response.getBody().asString());
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String jsonContent = authPage.readJsonFile();
        authPage.register(jsonContent);

        Response response = authPage.login(jsonContent);
        assertEquals(200, response.getStatusCode());

        String responseBody = response.getBody().asString();
        assertTrue(responseBody.startsWith("token : "));

        String token = authPage.extractToken(responseBody);
        UUID uuid = UUID.fromString(token);
        assertNotNull(uuid);
    }

    @Test
    public void testLogoutFailure() {
        Response response = authPage.logoutWithInvalidToken();
        assertEquals(401, response.getStatusCode());
        assertEquals("not found", response.getBody().asString());
    }

    @Test
    public void testLogoutSuccess() throws Exception {
        String jsonContent = authPage.readJsonFile();
        authPage.register(jsonContent);
        Response loginResponse = authPage.login(jsonContent);
        String token = authPage.extractToken(loginResponse.getBody().asString());

        Response response = authPage.logout(token);
        assertEquals(200, response.getStatusCode());
        assertEquals("success logout", response.getBody().asString());
    }
}