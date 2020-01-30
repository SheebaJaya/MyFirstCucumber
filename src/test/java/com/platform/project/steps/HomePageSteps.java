package com.platform.project.steps;


import com.gargoylesoftware.htmlunit.WebClient;
import com.platform.project.commons.ExcelReader;
import com.platform.project.commons.General;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.model.Registration;
import com.platform.project.pageobjects.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.io.IOException;
import java.util.List;

import static java.lang.Thread.currentThread;

public class HomePageSteps extends General {


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

    @Given("^I open my home page$")
    public void openHomePage() {
        homePage.openHomePage();

    }

    @Then("^Check the home page title is correct$")
    public void verifyHomePageTitle() {
        //homePage.openHomePage();
        check(loginPage.getHomePageText().equals("Welcome to iBusineess"), "openpage", "passed");
    }

    @Then("^Check that home page title is (.*)$")
    public void verifyHomePageTitle3(String expectedTitle) {
        //homePage.openHomePage();
        check(loginPage.getHomePageText().equals(expectedTitle), "Open","passed");
    }

    @Then(("^Check the home page title is correct2$"))
    public void verifyHomePageTitle2() {
       // homePage.openHomePage();
        check(loginPage.getHomePageText().equalsIgnoreCase("Welcome"), "openpage2", "failed");
    }

    @Given("^I entered username and password$")
    public void loginHomePage(){
        loginPage.login("jayagokes@gmail.com", "password123");
        // takeScreenShot("loggedin");
        logger.info("Sign in Successful");
    }

    @Then("^Check the Successful Url$")
    public void openSignInPageTest() throws IOException {

        String expectedUrl = "http://107.170.213.234/catalog/index.php";
        String actualUrl = webDriver.getCurrentUrl();
        boolean condition ;
        if(expectedUrl.equals(actualUrl)){
            condition = true;
        }else{
            condition = false;
        }
        check(condition, "login", "Sucess");

        //Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("^search for an item and get search not successful$")
    public void searchTest() throws IOException {
        loginPage.login("jayagokes@gmail.com", "password123");
        //takeScreenShot("loggedin");
        search.search("Ssdsf");
        String errorText = search.getErrormessage();
        boolean condition = errorText.equals("There is no product that matches the search criteria.");
        //takeScreenShot("search", currentThread().getStackTrace()[2].getClassName(), currentThread().getStackTrace()[2].getMethodName());
        if(condition){
            logger.info("Search Successful");

        }else{
            check(condition,"search","successful");
        }

     loginPage.logOff();

    }

    @Then("^search for an item and get search successful$")
    public void searchTestSuccess() throws IOException {
        loginPage.login("jayagokes@gmail.com", "password123");
        //takeScreenShot("loggedin");
        search.search("Samsung Galaxy");
        logger.info("Search Successful");

        loginPage.logOff();

    }

    @Given("^I clicked password forgot$")
    public void passwordForgot(){
        loginPage.passwordForgot();
    }

    @Then("^Enter wrong email id and verify the message$")
    public void passwordForgotTest() throws IOException {

        //  takeScreenShot("passwordforgot");
        String text = loginPage.getEmailBlankError();
        boolean condition;
        if(text.trim().equalsIgnoreCase("Error: The E-Mail Address was not found in our records, please try again.")){
            condition = true;
        }else{
            condition = false;
        }
        check(condition,"passwordforgotten","Errormessage");


       // Assert.assertEquals(text.trim(), "Error: The E-Mail Address was not found in our records, please try again.");
    }

    @When("^search for an item$")
    public void searchItem(){
        loginPage.login("jayagokes@gmail.com", "password123");
        search.search("Samsung Galaxy");
    }

    @Then("^Add to cart and checkout the products with order successfully placed message$")
    public void checkoutProducts() throws IOException {

        buyProducts.checkOut();
        boolean condition = buyProducts.getSuccessMessage().equalsIgnoreCase(
                "Your Order Has Been Processed!");
        if(condition){
            System.out.println("Successfully placed the order");
        }else{
            check(condition,"failureorder","Order was not placed");
        }
        //takeScreenShot("checkout");
        loginPage.logOff();
    }

    @Given("^Read username and password from excel file$")
    public void loginTest_MultipleInputsExcel() throws IOException {
        List<List<String>> lists = ExcelReader.readExcel(readPropertyFile.getFilePath(), readPropertyFile.getFileName(), readPropertyFile.getSheetName());
        for (int i = 0; i < lists.size(); i++) {
            List<String> list = lists.get(i);
            loginPage.login(list.get(0), list.get(1));

            // takeScreenShot("excelLoggin_" + i);
           // String homePageText = loginPage.getHomePageText();
         //  check(homePageText.equalsIgnoreCase("Welcome to iBusiness"), "OpenMultipleInput", "Error in message");
            // Assert.assertEquals(homePageText, "Welcome to iBusiness");
            loginPage.logOff();

        }
    }


}
