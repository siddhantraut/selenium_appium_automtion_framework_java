package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to load and manage configuration properties
 */
public class ConfigurationManager {
    private static Properties properties;
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from config.properties file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value by key with default value
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get property as integer
     * @param key Property key
     * @return Integer value
     */
    public static int getPropertyAsInt(String key) {
        String value = getProperty(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    /**
     * Get property as boolean
     * @param key Property key
     * @return Boolean value
     */
    public static boolean getPropertyAsBoolean(String key) {
        String value = getProperty(key);
        return value != null && value.equalsIgnoreCase("true");
    }
}

