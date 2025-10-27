package IF_HW5.Tests;

import IF_HW5.pages.AuthApiPage;
import IF_HW5.pages.RickAndMortyApiPage;
import io.qameta.allure.Allure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    protected Properties properties;
    protected AuthApiPage authPage;
    protected RickAndMortyApiPage rickAndMortyPage;

    public void setUp() throws IOException {
        Allure.step("Инициализация тестовой среды");

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

        Allure.step("Тестовая среда инициализирована");
    }

    protected String getProperty(String key) {
        return properties.getProperty(key);
    }
}