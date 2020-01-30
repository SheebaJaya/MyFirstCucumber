package com.platform.project.commons;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.Thread.currentThread;

public class General {

    protected static final String SCREENSHOT_BASE_PATH = "screenshot/";
    protected static final String SCREENSHOT_EXTN = ".jpg";

    protected WebDriverManager webDriverManager;
    protected WebDriver webDriver;
   org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(General.class);

       public void takeScreenShot(String fileName, String javaClass, String methodName) {

           String timeStamp = new SimpleDateFormat("YYYY-MM-DD_HH.mm.ss_").format(new Date());

           //Convert web driver object to TakeScreenshot

           TakesScreenshot takesScreenshot = ((TakesScreenshot) this.webDriver);

           //Call getScreenshotAs method to create image file

           File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

           //Move image file to new destination

           File destFile = new File(SCREENSHOT_BASE_PATH + timeStamp + javaClass + "_" + methodName + fileName + SCREENSHOT_EXTN);

           //Copy file at destination
           try {
               FileUtils.copyFile(srcFile, destFile);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

       }

       public void check(boolean condition, String fileName, String failMessage) {

           if (condition) {
               logger.info("check condition is true");
               Assert.assertTrue(true);
           } else {
               logger.info(failMessage);
               takeScreenShot(fileName, currentThread().getStackTrace()[2].getClassName(), currentThread().getStackTrace()[2].getMethodName());
               Assert.assertFalse(false);
               //org.testng.Assert.fail();
           }
       }

       //Explicit wait

       public boolean waitForElement(WebDriver webDriver, WebElement webElement, int seconds) {
           WebDriverWait wait = new WebDriverWait(webDriver, seconds);
           try {
               wait.until(ExpectedConditions.visibilityOf(webElement));
               logger.info(webElement.getText() + " is visible.");
               return true;
           } catch (ElementNotFoundException e) {
               e.printStackTrace();
               logger.info("Element no found");
               return false;
           }

       }

       //Explicit Wait - Fluent Wait
       public boolean isElementVisible(WebDriver driver, WebElement webElement, int seconds) {
           Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                   .withTimeout(seconds, TimeUnit.SECONDS)
                   .pollingEvery(10, TimeUnit.MILLISECONDS);

           com.google.common.base.Function<WebDriver, Boolean> isElementFound = arg0 -> {
               try {
                   webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
                   webElement.getSize();
                   return true;
               } catch (NoSuchElementException e) {
                   e.printStackTrace();
                   return false;
               }
           };
           try {
               // wait.until(isElementFound); our function to set the time
               wait.until(ExpectedConditions.visibilityOf(webElement));
               logger.info(webElement.getText() + " is visible.");
               return true;
           } catch (ElementNotFoundException e) {
               e.printStackTrace();
               logger.info("Element no found");
               return false;
           }
       }

       public String getElementText(WebDriver webDriver, WebElement homePageText, int seconds) {
           if (isElementVisible(webDriver, homePageText, 3)) {
               String homeText = homePageText.getText();
               logger.info("home page text is " + homeText);
               return homeText;
           } else {
               return "";
           }
       }
   }



