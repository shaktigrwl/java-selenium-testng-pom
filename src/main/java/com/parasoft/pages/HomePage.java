package com.parasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage() {

        PageFactory.initElements(BasePage.driver, this);
    }

    @FindBy(xpath = "//*[@id='topPanel']/a[2]/img")
    WebElement logo;

    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//*[@id='loginPanel']/form/div[3]/input")
    WebElement logInButton;

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void loginToAccount(String uname, String pass) {
        username.sendKeys(uname);
        password.sendKeys(pass);
        logInButton.click();
    }

}
