package config;

import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

@Getter
public final class PropertyConfig {

    private final String baseUrl;

    public PropertyConfig() {
        final Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseUrl = properties.getProperty("base.url");
    }
}
