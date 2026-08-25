package org.example.tests;
import org.example.base.BaseTest;
import org.example.utils.ExcelUtils;
import org.testng.annotations.Test;
import pages.LoginPage;
import java.io.IOException;

public class SauceDemoLoginTest extends BaseTest {
    String filePath = "src/test/resources/testdata/SaucedemoLogin.xlsx";

    @Test(description = "Login Test - Swag Labs")
    public void testSwagLabLogin() throws IOException {
        try {
            // Read username and password from Excel
            String username = ExcelUtils.getCellData(filePath,
                    "Login", 1, 0);
            String password = ExcelUtils.getCellData(filePath,
                    "Login", 1, 1);
            // Create LoginPage object
            LoginPage loginPage = new LoginPage(webDriver);
            // Login
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLogin();
        } catch (Exception e) {
            System.err.println("Error in test: " + e.getMessage());
            throw e;
        }
    }

}
