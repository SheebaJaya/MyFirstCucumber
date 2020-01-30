package com.platform.project.commons;

import com.platform.project.pageobjects.HomePage;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
//import org.testng.log4testng.Logger;

import java.util.concurrent.TimeUnit;

public class WebDriverManager  {
    private WebDriver webDriver;

    private String osName = System.getProperty("os.name").toLowerCase();
    private Logger logger = Logger.getLogger(HomePage.class);
    ReadPropertyFile readPropertyFile = new ReadPropertyFile();

    private WebDriver createDriver(String browser) {
        if (osName.equals("windows")) {
            if (browser.equalsIgnoreCase("chrome")) {
                //System.out.println("chrome browser detected");
                logger.info("chrome browser detected");
                System.setProperty(readPropertyFile.getdriver(), readPropertyFile.getdriverPath());
                webDriver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.out.println("firefoxbrowser detected");
                System.setProperty("webdriver.gecko.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/gecko");
                webDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("internet explorer")) {
                System.out.println("IE browser detected");
                System.setProperty("webdriver.ie.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/ie");
                webDriver = new InternetExplorerDriver();
            } else {
                System.out.println("Default browser detected");
                System.setProperty("webdriver.chrome.driver", "/Users/sheeba/IdeaProjects/MyFirstCucumber/src/test/resources/drivers/chromedriver");
                webDriver = new ChromeDriver();
            }
        } else if (osName.contains("mac")) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.out.println("chrome browser detected");
                System.setProperty("webdriver.chrome.driver", "/Users/sheeba/IdeaProjects/MyFirstCucumber/src/test/resources/drivers/chromedriver");
                webDriver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.out.println("firefoxbrowser detected");
                System.setProperty("webdriver.gecko.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/gecko");
                webDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("safari")) {
                System.out.println("Safari browser detected");
                System.setProperty("webdriver.safari.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/safari");
                webDriver = new SafariDriver();
            } else {
                System.out.println("Default browser detected");
                System.setProperty("webdriver.chrome.driver", "/Users/sheeba/IdeaProjects/MyFirstCucumber/src/test/resources/drivers/chromedriver");
                webDriver = new ChromeDriver();
            }
        } else if (osName.contains("linux")) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.out.println("chrome browser detected");
                System.setProperty("webdriver.chrome.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/chromedriver");
                webDriver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.out.println("firefoxbrowser detected");
                System.setProperty("webdriver.gecko.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/gecko");
                webDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("safari")) {
                System.out.println("Safari browser detected");
                System.setProperty("webdriver.safari.driver", "/Users/sheeba/IdeaProjects/MyFirstSelenium/src/Resources/Drivers/safari");
                webDriver = new SafariDriver();
            } else {
                System.out.println("Default browser detected");
                System.setProperty("webdriver.chrome.driver", "/Users/sheeba/IdeaProjects/MyFirstCucumber/src/test/resources/drivers/chromedriver");
                webDriver = new ChromeDriver();
            }
        }

        //implicit wait
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.MICROSECONDS);
        return webDriver;
    }

    public WebDriver getWebDriver() {

        return getWebDriver(createEnvVariable("browser",readPropertyFile.getBrowser()));
    }

    public String createEnvVariable(String envVariableName, String defaultValue) {
        String variableValue = System.getProperty(envVariableName);
        logger.info("Environment Value for " + envVariableName + "  is equal to " + variableValue);

        return variableValue != null ? variableValue : defaultValue;
    }

    public WebDriver getWebDriver(String browser) {
        if (webDriver == null) {
            try {
                webDriver = createDriver(browser);
                System.out.println("Driver intializtion successful");
            } catch (Exception e) {
                System.out.println("Driver intializtion failed");
                e.printStackTrace();
            }

        } else {
            System.out.println("Driver was already intialized");
        }

        return webDriver;
    }
}
