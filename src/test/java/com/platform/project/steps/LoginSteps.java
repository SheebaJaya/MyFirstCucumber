package com.platform.project.steps;

import com.platform.project.commons.General;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageobjects.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

public class LoginSteps extends General {


    HomePage homePage;
    private Logger logger = Logger.getLogger(HomePage.class);
    private ReadPropertyFile readPropertyFile = new ReadPropertyFile();
    LoginPage loginPage;
    Search search;
    BuyProducts buyProducts;
    Account account;
    WebDriverManager webDriverManager;

    @Before
    public void setUp() {
        webDriverManager = new WebDriverManager();
//        readPropertyFile = new ReadPropertyFile();
        webDriver = webDriverManager.getWebDriver();
        //  webDriver = webDriverManager.getWebDriver("chrome");

        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        search = new Search(webDriver);
        buyProducts = new BuyProducts(webDriver);
        account = new Account(webDriver);

    }


    @After
    public void cleanUp() {
        webDriver.quit();
    }

    @Given("^We are on the login page$")
     public void loginPageTest(){
         this.webDriver.get(readPropertyFile.getPropertyValue("loginPageURL"));

     }

     @When("^I fill Username with (.*) and I fill Password with (.*)$")
    public void login(String userName, String password){
        logger.info("Username is "+ userName);
        loginPage.login(userName,password);
     }

     @And("^I click on Sign In Button$")
    public void submit_Button(){
        loginPage.submit_click();
     }

     @Then("^I should see (.*) message$")
    public void warning_messages(String warning){
        logger.info(warning);
     }
}
