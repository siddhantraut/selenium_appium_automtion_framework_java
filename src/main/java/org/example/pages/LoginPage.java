package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // XPaths
    By userName = By.xpath("//input[@id='user-name']");
    By password = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//input[@id='login-button']");


    // Actions
    public void enterUserName(String username) {
        webDriver.findElement(userName).sendKeys(username);
    }

    public void enterPassword(String pwd) {
        webDriver.findElement(password).sendKeys(pwd);
    }

    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }
}