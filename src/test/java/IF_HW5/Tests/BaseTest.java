package IF_HW5.Tests;

import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    protected Properties properties;

    @Before
    public void setUp() throws IOException {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(inputStream);
    }

    protected String getProperty(String key) {
        return properties.getProperty(key);
    }
}