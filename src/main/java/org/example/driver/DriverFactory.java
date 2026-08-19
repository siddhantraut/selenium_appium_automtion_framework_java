package org.example.driver;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.example.utils.ConfigurationManager;
import org.example.capabilities.CapabilityFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.URL;
import java.time.Duration;

/**
 * Driver Factory class to create and return WebDriver and Appium Driver instances
 */
public class DriverFactory {

    private static final String APPIUM_SERVER_URL = ConfigurationManager.getProperty("APPIUM_SERVER_URL", "http://127.0.0.1:4723");

    /**
     * Get WebDriver instance for web applications
     * @return WebDriver instance
     */
    public static WebDriver getWebDriver() {
        String browser = ConfigurationManager.getProperty("BROWSER", "chrome").toLowerCase();
        WebDriver driver = null;

        switch (browser) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":
                driver = createFirefoxDriver();
                break;
            case "ie":
            case "internet explorer":
                driver = createInternetExplorerDriver();
                break;
            case "safari":
                driver = createSafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigurationManager.getPropertyAsInt("IMPLICIT_WAIT")));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }

        return driver;
    }



    /**
     * Create Chrome WebDriver
     * @return ChromeDriver instance
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = (ChromeOptions) CapabilityFactory.getChromeCapabilities();
        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver
     * @return FirefoxDriver instance
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = (FirefoxOptions) CapabilityFactory.getFirefoxCapabilities();
        return new FirefoxDriver(options);
    }

    /**
     * Create Internet Explorer WebDriver
     * @return InternetExplorerDriver instance
     */
    private static WebDriver createInternetExplorerDriver() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions options = (InternetExplorerOptions) CapabilityFactory.getInternetExplorerCapabilities();
        return new InternetExplorerDriver(options);
    }
    /**
     * Create Safari WebDriver
     * @return Safari instance
     */

    private static WebDriver createSafariDriver() {
        SafariOptions options = (SafariOptions) CapabilityFactory.getSafariCapabilities();
        return new SafariDriver(options);
    }

    /**
     * Get AppiumDriver instance for mobile applications
     * @return AppiumDriver instance
     */
    public static AppiumDriver getMobileDriver() {
        String mobileOS = ConfigurationManager.getProperty("MOBILE_OS", "android").toLowerCase();
        String appType = ConfigurationManager.getProperty("APP_MOBILE_TYPE", "native").toLowerCase();

        AppiumDriver driver = null;

        try {
            URL serverURL = new URL(APPIUM_SERVER_URL);

            if ("android".equals(mobileOS)) {
                driver = createAndroidDriver(serverURL, appType);
            } else if ("ios".equals(mobileOS)) {
                driver = createIOSDriver(serverURL, appType);
            } else {
                throw new IllegalArgumentException("Mobile OS not supported: " + mobileOS);
            }

            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigurationManager.getPropertyAsInt("IMPLICIT_WAIT")));
            }

            return driver;
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + APPIUM_SERVER_URL, e);
        }
    }

    /**
     * Create Android Driver
     * @param serverURL Appium server URL
     * @param appType Native or Web
     * @return AndroidDriver instance
     */
    private static AndroidDriver createAndroidDriver(java.net.URL serverURL, String appType) {
        return new AndroidDriver(serverURL, CapabilityFactory.getAndroidNativeCapabilities());
    }

    /**
     * Create iOS Driver
     * @param serverURL Appium server URL
     * @param appType Native or Web
     * @return IOSDriver instance
     */
    private static IOSDriver createIOSDriver(java.net.URL serverURL, String appType) {
        return new IOSDriver(serverURL, CapabilityFactory.getIOSNativeCapabilities());
    }

    /**
     * Get driver based on APP_TYPE from configuration
     * @return WebDriver or AppiumDriver instance
     */
    public static Object getDriver() {
        String appType = ConfigurationManager.getProperty("APP_TYPE", "web").toLowerCase();

        if ("mobile".equals(appType)) {
            return getMobileDriver();
        } else {
            return getWebDriver();
        }
    }

    /**
     * Close the driver
     * @param driver WebDriver instance
     */
    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Close the mobile driver
     * @param driver AppiumDriver instance
     */
    public static void closeMobileDriver(AppiumDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}

