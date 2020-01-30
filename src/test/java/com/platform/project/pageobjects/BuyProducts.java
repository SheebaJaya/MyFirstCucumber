package com.platform.project.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.platform.project.commons.BasePage;

public class BuyProducts extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'Buy Now')]")
    WebElement buyNow;

    @FindBy(xpath = " //span[@class='ui-button-text'][contains(text(),'Checkout')]")
    WebElement checkout;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continuePayment;

    @FindBy(xpath = "//tr//input[@name='payment']")
    WebElement codRadioButton;

    @FindBy(xpath = "//button[@id='tdb5']")
    WebElement confirmOrder;

    @FindBy(xpath = "//h1[contains(text(),'Your Order Has Been Processed!')] ")
    WebElement successMessage;

    private Logger logger = Logger.getLogger(BuyProducts.class);


    public BuyProducts(WebDriver webDriver) {
        super(webDriver);
    }

    public void buyProducts() {
        buyNow.click();
    }

    public  void checkOut(){
        buyProducts();
        checkout.click();
        continuePayment.click();
        String codValue = codRadioButton.getAttribute("value");

        System.out.println(codValue);

        if(codValue.equalsIgnoreCase("cod")){
            codRadioButton.click();
        }
        continuePayment.click();
        confirmOrder.click();
    }

    public String  getSuccessMessage(){
        String text = successMessage.getText();
        return  text;
    }
}
