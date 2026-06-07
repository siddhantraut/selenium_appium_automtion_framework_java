package org.example.tests;

import io.appium.java_client.AppiumDriver;
import org.example.driver.DriverFactory;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest {
    private AppiumDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = (AppiumDriver) DriverFactory.getMobileDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void validateHomePageLoads() {
        Assert.assertTrue(homePage.isLoaded(), "Home page did not load");
        Assert.assertTrue(homePage.hasSearchText(), "Search text not present");
    }

    @Test(dependsOnMethods = "validateHomePageLoads")
    public void validateHeaderButtons() {
        Assert.assertTrue(homePage.tapFavourites(), "Tapping favourites failed");
        Assert.assertTrue(homePage.tapBasket(), "Tapping basket failed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
