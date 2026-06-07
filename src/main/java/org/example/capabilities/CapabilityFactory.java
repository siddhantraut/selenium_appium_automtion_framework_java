package org.example.capabilities;

import org.example.utils.ConfigurationManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import io.appium.java_client.remote.options.BaseOptions;

/**
 * Capabilities class to create and manage desired capabilities for web and mobile drivers
 */
public class CapabilityFactory {

    /**
     * Create capabilities for Chrome browser
     * @return ChromeOptions
     */
    public static ChromeOptions getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }

    /**
     * Create capabilities for Firefox browser
     * @return FirefoxOptions
     */
    public static FirefoxOptions getFirefoxCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        return options;
    }

    /**
     * Create capabilities for Internet Explorer browser
     * @return InternetExplorerOptions
     */
    public static InternetExplorerOptions getInternetExplorerCapabilities() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("elementScrollBehavior", 1);
        options.setCapability("ignoreZoomSetting", true);
        return options;
    }

    /**
     * Get web browser capabilities based on configuration
     * @return Browser options/capabilities
     */
    public static Object getWebCapabilities() {
        String browser = ConfigurationManager.getProperty("BROWSER", "chrome").toLowerCase();

        switch (browser) {
            case "firefox":
                return getFirefoxCapabilities();
            case "ie":
            case "internet explorer":
                return getInternetExplorerCapabilities();
            case "chrome":
            default:
                return getChromeCapabilities();
        }
    }

    /**
     * Create capabilities for Android native application
     * @return BaseOptions for Appium
     */
    public static BaseOptions<?> getAndroidNativeCapabilities() {
        BaseOptions<?> options = new BaseOptions<>();
        options.setCapability("deviceName", ConfigurationManager.getProperty("ANDROID_DEVICE_NAME"));
        options.setCapability("platformVersion", ConfigurationManager.getProperty("ANDROID_PLATFORM_VERSION"));
        options.setCapability("platformName", "Android");
        options.setCapability("automationName", ConfigurationManager.getProperty("ANDROID_AUTOMATION_NAME", "UiAutomator2"));
       // options.setCapability("app", ConfigurationManager.getProperty("ANDROID_APP_PATH"));

        // Set app package and activity if provided
        String appPackage = ConfigurationManager.getProperty("ANDROID_APP_PACKAGE");
        String appActivity = ConfigurationManager.getProperty("ANDROID_APP_ACTIVITY");
        if (appPackage != null && !appPackage.isEmpty()) {
            options.setCapability("appPackage", appPackage);
        }
        if (appActivity != null && !appActivity.isEmpty()) {
            options.setCapability("appActivity", appActivity);
        }

        return options;
    }

    /**
     * Create capabilities for Android web application
     * @return BaseOptions for Appium
     */
    public static BaseOptions<?> getAndroidWebCapabilities() {
        BaseOptions<?> options = new BaseOptions<>();
        options.setCapability("deviceName", ConfigurationManager.getProperty("ANDROID_DEVICE_NAME"));
        options.setCapability("platformVersion", ConfigurationManager.getProperty("ANDROID_PLATFORM_VERSION"));
        options.setCapability("platformName", "Android");
        options.setCapability("automationName", ConfigurationManager.getProperty("ANDROID_AUTOMATION_NAME", "Chromium"));
        options.setCapability("browserName", "Chrome");
        return options;
    }

    /**
     * Create capabilities for iOS native application
     * @return BaseOptions for Appium
     */
    public static BaseOptions<?> getIOSNativeCapabilities() {
        BaseOptions<?> options = new BaseOptions<>();
        options.setCapability("deviceName", ConfigurationManager.getProperty("IOS_DEVICE_NAME"));
        options.setCapability("platformVersion", ConfigurationManager.getProperty("IOS_PLATFORM_VERSION"));
        options.setCapability("platformName", "iOS");
        options.setCapability("automationName", ConfigurationManager.getProperty("IOS_AUTOMATION_NAME", "XCUITest"));

        String udid = ConfigurationManager.getProperty("IOS_UDID");
        if (udid != null && !udid.isEmpty()) {
            options.setCapability("udid", udid);
        }

        String bundleId = ConfigurationManager.getProperty("IOS_BUNDLE_ID");
        if (bundleId != null && !bundleId.isEmpty()) {
            options.setCapability("bundleId", bundleId);
        }

        String appPath = ConfigurationManager.getProperty("IOS_APP_PATH");
        if (appPath != null && !appPath.isEmpty()) {
            options.setCapability("app", appPath);
        }

        String wdaLocalPort = ConfigurationManager.getProperty("IOS_WDA_LOCAL_PORT");
        if (wdaLocalPort == null || wdaLocalPort.isEmpty()) {
            wdaLocalPort = "8200";
        }
        options.setCapability("wdaLocalPort", Integer.parseInt(wdaLocalPort));

        return options;
    }

    /**
     * Create capabilities for iOS web application
     * @return BaseOptions for Appium
     */
    public static BaseOptions<?> getIOSWebCapabilities() {
        BaseOptions<?> options = new BaseOptions<>();
        options.setCapability("deviceName", ConfigurationManager.getProperty("IOS_DEVICE_NAME"));
        options.setCapability("platformVersion", ConfigurationManager.getProperty("IOS_PLATFORM_VERSION"));
        options.setCapability("platformName", "iOS");
        options.setCapability("automationName", ConfigurationManager.getProperty("IOS_AUTOMATION_NAME", "XCUITest"));
        options.setCapability("browserName", "Safari");
        return options;
    }

    /**
     * Create capabilities for Mobile applications based on configuration
     * @return BaseOptions for Appium
     */
    public static BaseOptions<?> getMobileCapabilities() {
        String mobileOS = ConfigurationManager.getProperty("MOBILE_OS", "android").toLowerCase();
        String appType = ConfigurationManager.getProperty("APP_MOBILE_TYPE", "native").toLowerCase();

        if ("android".equals(mobileOS)) {
            if ("web".equals(appType)) {
                return getAndroidWebCapabilities();
            } else {
                return getAndroidNativeCapabilities();
            }
        } else if ("ios".equals(mobileOS)) {
            if ("web".equals(appType)) {
                return getIOSWebCapabilities();
            } else {
                return getIOSNativeCapabilities();
            }
        }

        throw new IllegalArgumentException("Invalid MOBILE_OS: " + mobileOS);
    }
}

