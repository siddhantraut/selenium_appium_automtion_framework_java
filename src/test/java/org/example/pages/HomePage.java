package org.example.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By navigationTitle = By.xpath("//XCUIElementTypeNavigationBar[@name='HomeView']");
    private final By searchStaticText = By.xpath("//XCUIElementTypeStaticText[@name='Search,Edit']");
    private final By favouritesButton = AppiumBy.accessibilityId("automationNavigationHeaderLikesButton");
    private final By basketButton = AppiumBy.accessibilityId("automationNavigationHeaderBasketButton");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(navigationTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasSearchText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchStaticText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean tapFavourites() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(favouritesButton)).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean tapBasket() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(basketButton)).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
