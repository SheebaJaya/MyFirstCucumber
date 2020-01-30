package com.platform.project.pageobjects;

import com.platform.project.commons.BasePage;
import com.platform.project.commons.ReadPropertyFile;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'Sign In')]")
    WebElement signInPath;

    @FindBy(xpath = "//input[@name='email_address']")
    WebElement userNameWebElement;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordWebElement;

    @FindBy(xpath = "//td[@class='messageStackError']")
    WebElement tableData;

    @FindBy(xpath = "//h1[contains(text(),'Welcome to iBusiness')]")
    WebElement homePageText;

    @FindBy(xpath = "//span[contains(text(),'Log Off')]")
    WebElement logOff;

    @FindBy(xpath = " //a[contains(text(),'Password forgotten? Click here.')]")
    WebElement passwordForgotten;

    @FindBy(xpath = "//input[@name='email_address']")
    WebElement emailAdd;

    @FindBy(xpath = " //button[@id='tdb4']")
    WebElement buttonClick;

    @FindBy(xpath = "//td[@class='messageStackError']")
    WebElement emailBlankError;

    private Logger logger = Logger.getLogger(LoginPage.class);
    ReadPropertyFile readPropertyFile = new ReadPropertyFile();


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void login(String userName, String password) {
        logger.info("Opening sign in page");
        this.webDriver.get(readPropertyFile.getPropertyValue("loginPageURL"));
        userNameWebElement.sendKeys(userName);
        passwordWebElement.sendKeys(password);
        signInPath.click();
    }

    public void submit_click(){
        signInPath.click();
    }

    public String getErrorMessage(){
     String text = tableData.getText();

        return text;
    }

    public String getHomePageText(){
        //return  baseTest.getElementText(webDriver,homePageText,3);
        String homeText = homePageText.getText();
        logger.info("home page text is " + homeText);
        return  homeText;
    }

    public void logOff(){
        logOff.click();

    }

    public void passwordForgot(){
        this.webDriver.get(readPropertyFile.getPropertyValue("loginPageURL"));
        passwordForgotten.click();
        emailAdd.sendKeys("");
        buttonClick.click();
    }

    public String getEmailBlankError(){
        String errorText = emailBlankError.getText();
        return errorText;
    }
}
