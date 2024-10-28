package common;

import java.util.Properties;

public class Configuration {
    private static Properties properties = new Properties();

    static {
        // Load properties from a file or environment variables
        // properties.load(...)
    }

    public static int getHttpSocketTimeoutApi() {
        return Integer.parseInt(properties.getProperty("http.socket.timeout", "30000"));
    }

    public static int getHttpConnectionTimeoutApi() {
        return Integer.parseInt(properties.getProperty("http.connection.timeout", "30000"));
    }

    // Add other methods as needed
}

