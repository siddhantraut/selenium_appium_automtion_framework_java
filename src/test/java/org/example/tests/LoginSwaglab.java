package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginSwaglab extends BaseTest {

    @Test(description = "Swag Labs Login Test")
    public void loginSwaglab() throws InterruptedException {
        try {
            LoginPage loginPage = new LoginPage(webDriver);

            loginPage.enterUserName("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            Thread.sleep(5000); // wait 5 seconds
        }

    catch (Exception e) {
        System.err.println("Error in test: " + e.getMessage());
        throw e;
    }
    }

}