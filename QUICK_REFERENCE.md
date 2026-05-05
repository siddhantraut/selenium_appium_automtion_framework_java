# Quick Reference Guide - Mobile Web Automation Framework

## Table of Contents
1. [Web Testing](#web-testing)
2. [Mobile Testing](#mobile-testing)
3. [Configuration](#configuration)
4. [Utilities](#utilities)
5. [Common Commands](#common-commands)

---

## Web Testing

### Chrome Browser Test
```java
@Test
public void testChrome() {
    // Set in config.properties:
    // APP_TYPE=web
    // BROWSER=chrome
    // URL=https://www.google.com

    String title = webDriver.getTitle();
    System.out.println("Page Title: " + title);
}
```

### Firefox Browser Test
```java
// Set in config.properties:
// BROWSER=firefox

String currentUrl = webDriver.getCurrentUrl();
System.out.println("Current URL: " + currentUrl);
```

### Internet Explorer Test
```java
// Set in config.properties:
// BROWSER=ie

webDriver.navigate().to("https://example.com");
```

---

## Mobile Testing

### Android Native App Test
```java
@Test
public void testAndroidNativeApp() {
    // Set in config.properties:
    // APP_TYPE=mobile
    // MOBILE_OS=android
    // APP_MOBILE_TYPE=native
    // ANDROID_DEVICE_NAME=emulator-5554
    // ANDROID_APP_PACKAGE=com.example.app
    // ANDROID_APP_ACTIVITY=.MainActivity

    Assert.assertNotNull(mobileDriver);
}
```

### Android Web App Test
```java
@Test
public void testAndroidWeb() {
    // Set in config.properties:
    // APP_TYPE=mobile
    // MOBILE_OS=android
    // APP_MOBILE_TYPE=web
    // ANDROID_DEVICE_NAME=emulator-5554

    String pageSource = mobileDriver.getPageSource();
}
```

### iOS Native App Test
```java
@Test
public void testIOSNativeApp() {
    // Set in config.properties:
    // APP_TYPE=mobile
    // MOBILE_OS=ios
    // APP_MOBILE_TYPE=native
    // IOS_DEVICE_NAME=iPhone 15
    // IOS_BUNDLE_ID=com.example.app

    Assert.assertNotNull(mobileDriver);
}
```

### iOS Web App Test
```java
@Test
public void testIOSWeb() {
    // Set in config.properties:
    // APP_TYPE=mobile
    // MOBILE_OS=ios
    // APP_MOBILE_TYPE=web
    // IOS_DEVICE_NAME=iPhone 15

    mobileDriver.navigate().to("https://example.com");
}
```

---

## Configuration

### Minimal Web Config (Chrome)
```properties
APP_TYPE=web
BROWSER=chrome
URL=https://www.example.com
IMPLICIT_WAIT=10
```

### Minimal Android Native Config
```properties
APP_TYPE=mobile
MOBILE_OS=android
APP_MOBILE_TYPE=native
APPIUM_SERVER_URL=http://127.0.0.1:4723
ANDROID_DEVICE_NAME=emulator-5554
ANDROID_PLATFORM_VERSION=13
ANDROID_APP_PATH=/path/to/app.apk
```

### Minimal iOS Web Config
```properties
APP_TYPE=mobile
MOBILE_OS=ios
APP_MOBILE_TYPE=web
APPIUM_SERVER_URL=http://127.0.0.1:4723
IOS_DEVICE_NAME=iPhone 15
IOS_PLATFORM_VERSION=17.0
```

### All Available Properties
| Property | Description | Example |
|----------|-------------|---------|
| APP_TYPE | web or mobile | web |
| BROWSER | chrome, firefox, ie | chrome |
| URL | Application URL | https://www.google.com |
| IMPLICIT_WAIT | Implicit wait time | 10 |
| EXPLICIT_WAIT | Explicit wait time | 15 |
| MOBILE_OS | android or ios | android |
| APP_MOBILE_TYPE | native or web | native |
| APPIUM_SERVER_URL | Appium server URL | http://127.0.0.1:4723 |
| ANDROID_DEVICE_NAME | Device identifier | emulator-5554 |
| ANDROID_PLATFORM_VERSION | Android version | 13 |
| ANDROID_APP_PACKAGE | App package name | com.example.app |
| ANDROID_APP_ACTIVITY | App activity | .MainActivity |
| ANDROID_APP_PATH | APK file path | /path/to/app.apk |
| IOS_DEVICE_NAME | Device identifier | iPhone 15 |
| IOS_PLATFORM_VERSION | iOS version | 17.0 |
| IOS_BUNDLE_ID | App bundle ID | com.example.app |
| IOS_APP_PATH | IPA file path | /path/to/app.ipa |

---

## Utilities

### ConfigurationManager

```java
// Get String property
String url = ConfigurationManager.getProperty("URL");

// Get property with default value
String browser = ConfigurationManager.getProperty("BROWSER", "chrome");

// Get Integer property
int waitTime = ConfigurationManager.getPropertyAsInt("IMPLICIT_WAIT");

// Get Boolean property
boolean screenshot = ConfigurationManager.getPropertyAsBoolean("SCREENSHOT_ON_FAILURE");
```

### CapabilityFactory

```java
// Get web capabilities
Object chromeOptions = CapabilityFactory.getWebCapabilities();

// Get Android native capabilities
BaseOptions<?> androidCaps = CapabilityFactory.getAndroidNativeCapabilities();

// Get iOS web capabilities
BaseOptions<?> iOSWebCaps = CapabilityFactory.getIOSWebCapabilities();
```

### DriverFactory

```java
// Get web driver
WebDriver driver = DriverFactory.getWebDriver();

// Get mobile driver
AppiumDriver mobileDriver = DriverFactory.getMobileDriver();

// Get driver based on config
Object driver = DriverFactory.getDriver();

// Close driver
DriverFactory.closeDriver(driver);
DriverFactory.closeMobileDriver(mobileDriver);
```

### BaseTest

```java
// Extend BaseTest for automatic driver management
public class MyTest extends BaseTest {
    @Test
    public void myTest() {
        // Use webDriver for web testing
        webDriver.get("https://example.com");

        // OR Use mobileDriver for mobile testing
        mobileDriver.navigate().to("https://example.com");
    }
}
```

---

## Common Commands

### Build Project
```bash
mvn clean install
```

### Run All Tests
```bash
mvn test
```

### Run Specific Test
```bash
mvn test -Dtest=TestClassName
```

### Run Test Method
```bash
mvn test -Dtest=TestClassName#testMethodName
```

### Compile Only (skip tests)
```bash
mvn clean compile -DskipTests
```

### Install Dependencies
```bash
mvn clean install -DskipTests
```

### View Project Info
```bash
mvn help:active-profiles
mvn dependency:tree
```

### Run with Debug Output
```bash
mvn test -X
```

---

## Common WebDriver Operations

```java
// Navigation
webDriver.get("https://example.com");
webDriver.navigate().to("https://example.com");
webDriver.navigate().back();
webDriver.navigate().forward();
webDriver.navigate().refresh();

// Wait operations
WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("element_id")));

// Element interactions
WebElement element = webDriver.findElement(By.id("id"));
element.click();
element.sendKeys("text");
element.submit();

// Get page info
String title = webDriver.getTitle();
String url = webDriver.getCurrentUrl();
String pageSource = webDriver.getPageSource();

// Close driver
webDriver.quit();
```

---

## Common Appium Operations

```java
// Tap element
WebElement element = mobileDriver.findElement(By.id("id"));
new TouchAction(mobileDriver).tap(element).perform();

// Swipe
Dimension size = mobileDriver.manage().window().getSize();
new TouchAction(mobileDriver)
    .press(size.getWidth() / 2, size.getHeight() - 1)
    .waitAction()
    .moveTo(size.getWidth() / 2, 1)
    .release()
    .perform();

// Get orientation
ScreenOrientation orientation = mobileDriver.getOrientation();

// Rotate device
mobileDriver.rotate(ScreenOrientation.LANDSCAPE);

// Close app
mobileDriver.closeApp();

// Quit
mobileDriver.quit();
```

---

## TestNG Annotations

```java
@BeforeClass        // Runs once before all test methods in class
@BeforeMethod       // Runs before each test method
@Test               // Marks method as test
@DataProvider       // Provides data for parameterized tests
@AfterMethod        // Runs after each test method
@AfterClass         // Runs once after all test methods in class

// Example
@Test(description = "Test description", enabled = true, groups = {"smoke"})
public void myTest() {
    // Test code
}
```

---

## File Locations

| File | Location | Purpose |
|------|----------|---------|
| pom.xml | Root | Maven dependencies |
| config.properties | src/main/resources | Framework configuration |
| log4j.properties | src/main/resources | Logging configuration |
| testng.xml | Root | TestNG configuration |
| BaseTest.java | src/test/java | Base test class |
| SampleTest.java | src/test/java | Sample test class |
| DriverFactory.java | src/main/java | Driver creation |
| CapabilityFactory.java | src/main/java | Capability creation |
| ConfigurationManager.java | src/main/java | Configuration loader |

---

## Troubleshooting Quick Tips

| Issue | Solution |
|-------|----------|
| WebDriver not found | Run `mvn clean install` |
| Appium connection failed | Verify Appium server is running |
| Config not loading | Check property file path and names |
| Element not found | Increase EXPLICIT_WAIT or verify locator |
| Test fails with timeout | Increase IMPLICIT_WAIT in config |
| Mobile test not working | Verify device is connected via `adb devices` |

---

## Example Test Class Template

```java
package org.example.tests;

import org.example.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyTest extends BaseTest {

    @Test(description = "My test description")
    public void myTestMethod() {
        try {
            // Your test code here
            String title = webDriver.getTitle();
            Assert.assertNotNull(title);

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            throw e;
        }
    }
}
```

---

## Tips & Best Practices

✅ Always extend `BaseTest` for automatic driver management
✅ Use descriptive test method names
✅ Use `@Test` annotations with descriptions
✅ Handle exceptions properly
✅ Use configuration file for environment-specific values
✅ Avoid hard-coding wait times - use configuration
✅ Use explicit waits when needed
✅ Keep tests independent and reusable
✅ Use meaningful assertions
✅ Run tests regularly

---

**For more information, see README.md and GETTING_STARTED.md**

