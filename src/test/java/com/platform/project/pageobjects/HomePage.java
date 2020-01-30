package com.platform.project.pageobjects;

import com.platform.project.commons.BasePage;
import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//select[@name='manufacturers_id']")
    WebElement dropDown;


    private Logger logger = Logger.getLogger(HomePage.class);
    ReadPropertyFile readPropertyFile = new ReadPropertyFile();


    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    public void openHomePage(){
        logger.info("Opening Home HomePage");
        this.webDriver.get(readPropertyFile.getPropertyValue("homePageURL"));
    }


    public static void verifyLinkActive(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

            httpURLConnect.setConnectTimeout(3000);

            httpURLConnect.connect();

            if(httpURLConnect.getResponseCode()==200)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }

    public void getAllOptions() {
        openHomePage();
        String[] values = {"Please Select","Canon", "Fox", "GT Interactive", "Hewlett Packard", "Logitech", "Matrox", "Microsoft", "Samsung", "Sierra", "Warner"};
        Select select = new Select(dropDown);
       // select.selectByVisibleText("Canon");
        List<WebElement> options = select.getOptions();
        for (WebElement webElement : options) {
            for (int i = 1; i < values.length; i++) {
                if (webElement.getText().trim().equalsIgnoreCase(values[i])) {
                    System.out.println(webElement.getText() + " & " + values[i] + " are same ");
                }

            }
        }
    }
}
