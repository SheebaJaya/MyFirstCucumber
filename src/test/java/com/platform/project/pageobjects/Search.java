package com.platform.project.pageobjects;

import com.platform.project.commons.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Search extends BasePage {

    @FindBy(xpath = " //form[@name='quick_find']//input[@name='keywords']")
    WebElement searchTab;

    @FindBy(xpath = "//body//input[3]")
    WebElement searchClick;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    WebElement searchErrorText;

    private Logger logger = Logger.getLogger(Search.class);

    public Search(WebDriver webDriver) {
        super(webDriver);
    }

    public void search(String keyword) {
        logger.info("Searching for " + keyword);
        searchTab.sendKeys(keyword);
        searchClick.click();
    }

    public String getErrormessage(){
       String errorMessage = searchErrorText.getText();

       return  errorMessage;
    }
}
