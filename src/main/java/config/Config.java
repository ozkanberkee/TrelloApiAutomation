package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiKey() {
        return props.getProperty("KEY");
    }

    public static String getToken() {
        return props.getProperty("TOKEN");
    }
}
