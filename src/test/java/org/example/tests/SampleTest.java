package org.example.tests;

import org.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Sample test class demonstrating the framework usage
 */
public class SampleTest extends BaseTest {

    /**
     * Sample test for web application
     */
    @Test(description = "Sample Web Test - Google Search")
    public void testGoogleSearch() {
        try {
            // This test assumes you are testing Google or any web application
            String pageTitle = webDriver.getTitle();
            System.out.println("Page Title: " + pageTitle);
            Assert.assertNotNull(pageTitle, "Page title should not be null");
        } catch (Exception e) {
            System.err.println("Error in test: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Sample test for verifying page is loaded
     */
    @Test(description = "Sample Test - Page Load Verification")
    public void testPageLoadVerification() {
        try {
            String currentURL = webDriver.getCurrentUrl();
            System.out.println("Current URL: " + currentURL);
            Assert.assertNotNull(currentURL, "Current URL should not be null");
        } catch (Exception e) {
            System.err.println("Error in test: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Sample test for mobile application
     * Note: This test will only run if APP_TYPE is set to 'mobile' in config.properties
     */
    @Test(description = "Sample Mobile Test - App Launch Verification", enabled = true)
    public void testMobileAppLaunch() {
        try {
            if (mobileDriver != null) {
                System.out.println("Mobile app launched successfully");
                Assert.assertNotNull(mobileDriver, "Mobile driver should not be null");
            }
        } catch (Exception e) {
            System.err.println("Error in mobile test: " + e.getMessage());
             throw e;
        }
    }
}

