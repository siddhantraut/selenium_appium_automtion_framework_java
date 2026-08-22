package org.example.base;

import org.example.driver.DriverFactory;
import org.example.utils.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

/**
 * Base Test class that handles driver initialization and cleanup for all tests
 */
public class BaseTest {

    protected WebDriver webDriver;
    protected AppiumDriver mobileDriver;

    /**
     * Setup method - runs before each test
     * Initializes the driver based on APP_TYPE in configuration
     */
    @BeforeMethod
    public void setUp() {
        String appType = ConfigurationManager.getProperty("APP_TYPE", "web").toLowerCase();

        if ("mobile".equals(appType)) {
            mobileDriver = (AppiumDriver) DriverFactory.getMobileDriver();
            System.out.println("Mobile driver initialized: " + mobileDriver.getClass().getSimpleName());
        } else {
            webDriver = DriverFactory.getWebDriver();
            System.out.println("Web driver initialized: " + webDriver.getClass().getSimpleName());

            // Navigate to URL if web application
            String url = ConfigurationManager.getProperty("URL");
            if (url != null && !url.isEmpty()) {
                webDriver.navigate().to(url);
                System.out.println("Navigated to URL: " + url);
            }
        }
    }




    /**
     * Teardown method - runs after each test
     * Closes the driver and cleans up resources
     */
    @AfterMethod
    public void tearDown() {
        String appType = ConfigurationManager.getProperty("APP_TYPE", "web").toLowerCase();

        if ("mobile".equals(appType)) {
            if (mobileDriver != null) {
                DriverFactory.closeMobileDriver(mobileDriver);
                System.out.println("Mobile driver closed");
                mobileDriver = null;
            }
        } else {
            if (webDriver != null) {
                DriverFactory.closeDriver(webDriver);
                System.out.println("Web driver closed");
                webDriver = null;
            }
        }
    }
}

