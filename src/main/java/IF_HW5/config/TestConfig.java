package IF_HW5.config;

import IF_HW5.pages.AuthApiPage;
import IF_HW5.pages.RickAndMortyApiPage;
import io.qameta.allure.Allure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static TestConfig instance;
    private Properties properties;
    private AuthApiPage authPage;
    private RickAndMortyApiPage rickAndMortyPage;

    private TestConfig() {
        try {
            initialize();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize TestConfig", e);
        }
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
        }
        return instance;
    }

    private void initialize() throws IOException {
        Allure.step("Инициализация тестовой конфигурации");

        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new IOException("config.properties not found");
        }

        authPage = new AuthApiPage(
                getProperty("register.url"),
                getProperty("login.url"),
                getProperty("logout.url")
        );
        rickAndMortyPage = new RickAndMortyApiPage(getProperty("rickandmorty.api.url"));

        Allure.step("Тестовая конфигурация инициализирована");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public AuthApiPage getAuthPage() {
        return authPage;
    }

    public RickAndMortyApiPage getRickAndMortyPage() {
        return rickAndMortyPage;
    }
}