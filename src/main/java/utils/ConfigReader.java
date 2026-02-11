package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read configuration properties
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    /**
     * Get property value by key
     * @param key property key
     * @return property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get base URL for UI tests
     * @return base URL
     */
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    /**
     * Get API base URL
     * @return API base URL
     */
    public static String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }

    /**
     * Get browser name
     * @return browser name
     */
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    /**
     * Get implicit wait time
     * @return implicit wait in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    /**
     * Get explicit wait time
     * @return explicit wait in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    /**
     * Check if running in headless mode
     * @return true if headless, false otherwise
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }
}