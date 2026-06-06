# Mobile Web Automation Framework

A comprehensive Test Automation Framework built with **Selenium**, **Appium**, and **TestNG** for testing web and mobile applications.

## Features

- ✅ **Web Automation**: Support for Chrome, Firefox, and Internet Explorer
- ✅ **Mobile Automation**: Support for Android (native & web) and iOS (native & web)
- ✅ **Property-based Configuration**: Easy configuration through properties file
- ✅ **Driver Factory Pattern**: Centralized driver management
- ✅ **Capability Factory**: Flexible capability management for different platforms
- ✅ **TestNG Integration**: Powerful test execution engine
- ✅ **Logging**: Log4j integration for comprehensive logging
- ✅ **WebDriverManager**: Automatic driver binary management

## Project Structure

```
MobileWebAutomationFramework_Java/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── driver/
│   │   │   │   └── DriverFactory.java          # Driver creation and management
│   │   │   ├── capabilities/
│   │   │   │   └── CapabilityFactory.java      # Capabilities management
│   │   │   └── utils/
│   │   │       └── ConfigurationManager.java   # Configuration loader
│   │   └── resources/
│   │       ├── config.properties               # Configuration file
│   │       └── log4j.properties                # Logging configuration
│   └── test/
│       ├── java/org/example/
│       │   ├── base/
│       │   │   └── BaseTest.java               # Base test class
│       │   └── tests/
│       │       └── SampleTest.java             # Sample test cases
│       └── resources/
├── pom.xml                                     # Maven dependencies
├── testng.xml                                  # TestNG configuration
└── README.md                                   # Documentation
```

## Dependencies

### Core Dependencies
- **Selenium WebDriver** (v4.15.0) - Web browser automation
- **Appium Java Client** (v9.2.0) - Mobile automation
- **TestNG** (v7.8.1) - Test execution framework
- **WebDriverManager** (v5.6.3) - Automated driver management
- **Log4j** (v1.2.17) - Logging framework
- **SLF4J** (v2.0.9) - Logging abstraction

## Configuration

### config.properties File

The framework uses `src/main/resources/config.properties` for configuration.

#### Web Configuration Example:
```properties
APP_TYPE=web
BROWSER=chrome
URL=https://www.google.com
IMPLICIT_WAIT=10
EXPLICIT_WAIT=15
```

**Supported Browsers**: chrome, firefox, ie

#### Mobile Configuration Example (Android Native):
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

#### Mobile Configuration Example (iOS Native):
```properties
APP_TYPE=mobile
MOBILE_OS=ios
APP_MOBILE_TYPE=native
APPIUM_SERVER_URL=http://127.0.0.1:4723
IOS_DEVICE_NAME=iPhone 15
IOS_PLATFORM_VERSION=17.0
IOS_BUNDLE_ID=com.example.app
IOS_APP_PATH=/path/to/app.ipa
IOS_AUTOMATION_NAME=XCUITest
```

#### Mobile Configuration Example (Android Web):
```properties
APP_TYPE=mobile
MOBILE_OS=android
APP_MOBILE_TYPE=web
APPIUM_SERVER_URL=http://127.0.0.1:4723
ANDROID_DEVICE_NAME=emulator-5554
ANDROID_PLATFORM_VERSION=13
ANDROID_AUTOMATION_NAME=Chromium
```

## Key Classes

### 1. DriverFactory
**Location**: `src/main/java/org/example/driver/DriverFactory.java`

Responsible for creating WebDriver and AppiumDriver instances.

**Key Methods**:
- `getWebDriver()` - Returns WebDriver for web applications
- `getMobileDriver()` - Returns AppiumDriver for mobile applications
- `getDriver()` - Returns appropriate driver based on APP_TYPE
- `closeDriver(WebDriver)` - Closes WebDriver
- `closeMobileDriver(AppiumDriver)` - Closes AppiumDriver

### 2. CapabilityFactory
**Location**: `src/main/java/org/example/capabilities/CapabilityFactory.java`

Manages desired capabilities for different platforms.

**Key Methods**:
- `getChromeCapabilities()` - Chrome browser capabilities
- `getFirefoxCapabilities()` - Firefox browser capabilities
- `getInternetExplorerCapabilities()` - IE browser capabilities
- `getWebCapabilities()` - Web capabilities based on configuration
- `getAndroidNativeCapabilities()` - Android native app capabilities
- `getAndroidWebCapabilities()` - Android web app capabilities
- `getIOSNativeCapabilities()` - iOS native app capabilities
- `getIOSWebCapabilities()` - iOS web app capabilities
- `getMobileCapabilities()` - Mobile capabilities based on configuration

### 3. ConfigurationManager
**Location**: `src/main/java/org/example/utils/ConfigurationManager.java`

Loads and manages properties from `config.properties`.

**Key Methods**:
- `getProperty(String key)` - Get property value
- `getProperty(String key, String defaultValue)` - Get property with default
- `getPropertyAsInt(String key)` - Get property as integer
- `getPropertyAsBoolean(String key)` - Get property as boolean

### 4. BaseTest
**Location**: `src/test/java/org/example/base/BaseTest.java`

Abstract base class for all test classes with setup and teardown methods.

**Features**:
- `@BeforeMethod` - Initializes driver before each test
- `@AfterMethod` - Closes driver after each test
- Supports both WebDriver and AppiumDriver

## Usage Examples

### Writing a Web Test

```java
package org.example.tests;

import org.example.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {

    @Test(description = "Test Google Search")
    public void testSearch() {
        // Your test code here
        String title = webDriver.getTitle();
        Assert.assertTrue(title.contains("Google"));
    }
}
```

### Writing a Mobile Test

```java
@Test(description = "Test Mobile App Launch")
public void testAppLaunch() {
    // Your mobile test code here
    String appPackage = mobileDriver.getCurrentPackage();
    Assert.assertNotNull(appPackage);
}
```

## Running Tests

### Compile the Project
```bash
mvn clean compile
```

### Install Dependencies
```bash
mvn clean install
```

### Run a Single Test Method
```bash
mvn -Dtest=SampleTest#testMobileAppLaunch test
```

### Run All Tests
```bash
mvn test
```

### Run Specific TestNG Suite
```bash
mvn test -Dtest=testng.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=org.example.tests.SampleTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=org.example.tests.SampleTest#testGoogleSearch
```

## Setting Up Appium for Mobile Testing

### Prerequisites
- Node.js installed
- Android SDK/Emulator or iOS Simulator
- Xcode (for iOS testing)

### Install Appium
```bash
npm install -g appium
```

### Start Appium Server
```bash
appium --address 127.0.0.1 --port 4723
```

## Browser Driver Setup

The framework uses **WebDriverManager** for automatic driver management. Drivers are automatically downloaded and set up based on browser configuration.

- **Chrome**: Automatically managed by WebDriverManager
- **Firefox**: Automatically managed by WebDriverManager
- **Internet Explorer**: Automatically managed by WebDriverManager

## Logging

Log files are generated in the `logs/` directory with the naming pattern `automation.log`.

Configure logging level in `src/main/resources/log4j.properties`:
- DEBUG: Detailed information for debugging
- INFO: General information
- ERROR: Error messages only

## Troubleshooting

### WebDriver Not Found
- Ensure WebDriverManager dependencies are properly installed
- Check Java version (Java 17 or higher recommended)

### Appium Connection Error
- Verify Appium server is running on the configured URL
- Check network connectivity

### Property Not Found
- Verify property key exists in `config.properties`
- Check for typos in property key names

## Best Practices

1. **Extend BaseTest**: All test classes should extend `BaseTest` for automatic driver management
2. **Use Properties File**: Externalize all configuration to `config.properties`
3. **Follow Naming Conventions**: Use descriptive test method names
4. **Handle Exceptions**: Always handle exceptions properly in tests
5. **Close Resources**: Ensure drivers are closed after tests (handled by BaseTest)
6. **Use TestNG Annotations**: Leverage `@Test`, `@BeforeMethod`, `@AfterMethod` annotations

## Support Platforms

### Web Browsers
- ✅ Google Chrome
- ✅ Mozilla Firefox
- ✅ Internet Explorer

### Mobile Platforms
- ✅ Android (Native and Web)
- ✅ iOS (Native and Web)

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any bugs or feature requests.

## Author

Mobile Web Automation Framework

## Version

1.0-SNAPSHOT

