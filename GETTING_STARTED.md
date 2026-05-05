# Getting Started with Mobile Web Automation Framework

## Framework Overview

You now have a complete, production-ready test automation framework that supports:

### Web Automation
- ✅ Google Chrome
- ✅ Mozilla Firefox
- ✅ Internet Explorer
- ✅ Automatic driver management with WebDriverManager

### Mobile Automation
- ✅ Android native applications
- ✅ Android web applications
- ✅ iOS native applications
- ✅ iOS web applications
- ✅ Appium integration

### Test Execution
- ✅ TestNG framework
- ✅ TestNG XML configuration support
- ✅ Log4j logging

## Quick Start

### 1. Build the Project
```bash
cd /Users/admin/Documents/GitHub/MobileWebAutomationFramework_Java
mvn clean install
```

### 2. Configure for Web Testing

Edit `src/main/resources/config.properties`:

```properties
APP_TYPE=web
BROWSER=chrome
URL=https://www.google.com
IMPLICIT_WAIT=10
```

### 3. Run Web Tests
```bash
mvn test
```

## Framework Components

### 1. **ConfigurationManager** (`src/main/java/org/example/utils/ConfigurationManager.java`)
- Loads configuration from `config.properties`
- Provides utility methods to read properties
- Supports string, integer, and boolean property types

**Usage:**
```java
String url = ConfigurationManager.getProperty("URL");
int waitTime = ConfigurationManager.getPropertyAsInt("IMPLICIT_WAIT");
boolean screenshot = ConfigurationManager.getPropertyAsBoolean("SCREENSHOT_ON_FAILURE");
```

### 2. **CapabilityFactory** (`src/main/java/org/example/capabilities/CapabilityFactory.java`)
- Creates browser capabilities for Selenium
- Creates device capabilities for Appium
- Supports web, native app, and hybrid app testing

**Key Methods:**
- `getChromeCapabilities()` - Chrome options
- `getFirefoxCapabilities()` - Firefox options
- `getInternetExplorerCapabilities()` - IE options
- `getAndroidNativeCapabilities()` - Android native app
- `getAndroidWebCapabilities()` - Android web app
- `getIOSNativeCapabilities()` - iOS native app
- `getIOSWebCapabilities()` - iOS web app

### 3. **DriverFactory** (`src/main/java/org/example/driver/DriverFactory.java`)
- Creates and manages WebDriver instances
- Creates and manages AppiumDriver instances
- Handles driver lifecycle

**Key Methods:**
- `getWebDriver()` - Returns WebDriver for web testing
- `getMobileDriver()` - Returns AppiumDriver for mobile testing
- `getDriver()` - Returns driver based on APP_TYPE
- `closeDriver(WebDriver)` - Closes WebDriver
- `closeMobileDriver(AppiumDriver)` - Closes AppiumDriver

### 4. **BaseTest** (`src/test/java/org/example/base/BaseTest.java`)
- Abstract base class for all test classes
- Handles driver initialization in `@BeforeMethod`
- Handles driver cleanup in `@AfterMethod`
- Automatically selects Web or Mobile driver based on config

**Usage:**
```java
public class MyTest extends BaseTest {
    @Test
    public void myTestMethod() {
        // Use webDriver or mobileDriver
        webDriver.get("https://example.com");
    }
}
```

## Configuration File Guide

### Web Configuration
```properties
APP_TYPE=web                    # Specifies web application testing
BROWSER=chrome                  # chrome, firefox, or ie
URL=https://www.google.com     # Application URL
IMPLICIT_WAIT=10               # Implicit wait in seconds
EXPLICIT_WAIT=15               # Explicit wait in seconds
```

### Android Native App Configuration
```properties
APP_TYPE=mobile
MOBILE_OS=android
APP_MOBILE_TYPE=native
APPIUM_SERVER_URL=http://127.0.0.1:4723
ANDROID_DEVICE_NAME=emulator-5554
ANDROID_PLATFORM_VERSION=13
ANDROID_APP_PACKAGE=com.example.app
ANDROID_APP_ACTIVITY=.MainActivity
ANDROID_APP_PATH=/path/to/app.apk
ANDROID_AUTOMATION_NAME=UiAutomator2
```

### Android Web App Configuration
```properties
APP_TYPE=mobile
MOBILE_OS=android
APP_MOBILE_TYPE=web
APPIUM_SERVER_URL=http://127.0.0.1:4723
ANDROID_DEVICE_NAME=emulator-5554
ANDROID_PLATFORM_VERSION=13
ANDROID_AUTOMATION_NAME=Chromium
```

### iOS Native App Configuration
```properties
APP_TYPE=mobile
MOBILE_OS=ios
APP_MOBILE_TYPE=native
APPIUM_SERVER_URL=http://127.0.0.1:4723
IOS_DEVICE_NAME=iPhone 15 Pro
IOS_PLATFORM_VERSION=17.0
IOS_BUNDLE_ID=com.example.app
IOS_APP_PATH=/path/to/app.ipa
IOS_AUTOMATION_NAME=XCUITest
```

### iOS Web App Configuration
```properties
APP_TYPE=mobile
MOBILE_OS=ios
APP_MOBILE_TYPE=web
APPIUM_SERVER_URL=http://127.0.0.1:4723
IOS_DEVICE_NAME=iPhone 15 Pro
IOS_PLATFORM_VERSION=17.0
IOS_AUTOMATION_NAME=XCUITest
```

## Sample Test Usage

### Web Test Example
```java
package org.example.tests;

import org.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {

    @Test(description = "Test Google page title")
    public void testGooglePageTitle() {
        String title = webDriver.getTitle();
        Assert.assertTrue(title.contains("Google"));
    }
}
```

### Mobile Test Example
```java
package org.example.tests;

import org.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileAppTest extends BaseTest {

    @Test(description = "Test mobile app launch")
    public void testAppLaunch() {
        Assert.assertNotNull(mobileDriver);
        System.out.println("Mobile app launched successfully");
    }
}
```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=GoogleSearchTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=GoogleSearchTest#testGooglePageTitle
```

### Run Tests with TestNG XML
```bash
mvn test -Dsuites=testng.xml
```

## Setting Up Appium (for Mobile Testing)

### 1. Install Node.js (if not installed)
```bash
# Using Homebrew on macOS
brew install node
```

### 2. Install Appium Globally
```bash
npm install -g appium
```

### 3. Install Appium Drivers (if needed)
```bash
appium driver install android
appium driver install xcuitest
```

### 4. Start Appium Server
```bash
appium --address 127.0.0.1 --port 4723
```

## Project Structure
```
MobileWebAutomationFramework_Java/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── driver/
│   │   │   │   └── DriverFactory.java
│   │   │   ├── capabilities/
│   │   │   │   └── CapabilityFactory.java
│   │   │   ├── utils/
│   │   │   │   └── ConfigurationManager.java
│   │   │   └── Main.java
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j.properties
│   └── test/
│       ├── java/org/example/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   └── tests/
│       │       └── SampleTest.java
│       └── resources/
├── pom.xml
├── testng.xml
├── logs/
│   └── automation.log (generated)
└── target/ (generated)
```

## Logging

Logs are configured in `src/main/resources/log4j.properties` and are written to `logs/automation.log`.

Current log level is set to INFO. To change:
1. Edit `src/main/resources/log4j.properties`
2. Change `log4j.rootLogger=INFO` to `log4j.rootLogger=DEBUG` or `ERROR`

## Dependencies Installed

- Selenium WebDriver 4.15.0
- Appium Java Client 9.2.0
- TestNG 7.10.1
- WebDriverManager 5.6.3
- Log4j 1.2.17
- SLF4J 2.0.9

## Troubleshooting

### WebDriver Not Found Error
- Ensure WebDriverManager dependencies are installed
- Run `mvn clean install` to download all dependencies

### Appium Connection Failed
- Verify Appium server is running on configured URL
- Check firewall settings
- Verify network connectivity

### Test Configuration Not Loading
- Verify `config.properties` exists in `src/main/resources/`
- Check property key names (case-sensitive)
- Verify path is correct

## Next Steps

1. **Create your test classes** by extending `BaseTest`
2. **Update `config.properties`** with your application details
3. **Write test methods** using Selenium/Appium APIs
4. **Run tests** using Maven commands
5. **View logs** in `logs/automation.log`

## Supporting Documentation

- Selenium Documentation: https://www.selenium.dev/documentation/
- Appium Documentation: http://appium.io/docs/
- TestNG Documentation: https://testng.org/doc/

## Support

For issues or questions:
1. Check the README.md for detailed framework documentation
2. Review the sample test classes for usage examples
3. Check application logs in `logs/automation.log`

Happy Testing! 🚀

