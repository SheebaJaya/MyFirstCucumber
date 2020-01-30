package com.platform.project.pageobjects;

import com.platform.project.commons.BasePage;
import com.platform.project.model.Registration;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Account extends BasePage {

    @FindBy(xpath = "//a[@id='tdb3']")
    WebElement myAccount;

    @FindBy(xpath = "//body//form//div//input[1]")
    WebElement genderMale;

    @FindBy(xpath = "//body//form//div//input[2]")
    WebElement genderFemale;

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstName;

    @FindBy(xpath = " //input[@name='lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='dob']")
    WebElement dob;

    @FindBy(xpath = "//input[@name='email_address']")
    WebElement email;

    @FindBy(xpath = " //input[@name='company']")
    WebElement company;

    @FindBy(xpath = " //input[@name='street_address']")
    WebElement streetAddress;

    @FindBy(xpath = "//input[@name='suburb']")
    WebElement subrub;

    @FindBy(xpath = "//input[@name='postcode']")
    WebElement postcode;

    @FindBy(xpath = "//input[@name='city']")
    WebElement city;

    @FindBy(xpath = "//input[@name='state']")
    WebElement state;

    @FindBy(xpath = "//select[@name='country']")
    WebElement country;

    @FindBy(xpath = "//input[@name='telephone']")
    WebElement telephone;

    @FindBy(xpath = "//input[@name='fax']")
    WebElement fax;

    @FindBy(xpath = "//input[@name='newsletter']")
    WebElement newsletter;

    @FindBy(xpath = " //input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//input[@name='confirmation']")
    WebElement confirmation;

    @FindBy(xpath = " //button[@id='tdb4']")
    WebElement continueButton;

    @FindBy(xpath = "//a[@id='tdb4']")
    WebElement continueNewAccount;


    private Logger logger = Logger.getLogger(Account.class);

    public Account(WebDriver webDriver) {
        super(webDriver);
    }

    public void newAccount(Registration registration) {
        myAccount.click();
        continueNewAccount.click();
        if(registration.getGender() == "Female"){
            genderFemale.click();
        }else{
            genderMale.click();
        }
        firstName.sendKeys(registration.getFirstName());
        lastName.sendKeys(registration.getLastName());
        dob.sendKeys(registration.getDob().toString());
        email.sendKeys(registration.getEmail());
        company.sendKeys(registration.getCompany());
        streetAddress.sendKeys(registration.getStreetAddress());
        subrub.sendKeys(registration.getSubrub());
        postcode.sendKeys(registration.getPostcode());
        city.sendKeys(registration.getCity());
        state.sendKeys(registration.getState());
        country.sendKeys(registration.getCountry());
        telephone.sendKeys(registration.getTelephone());
        fax.sendKeys(registration.getFax());
        newsletter.sendKeys(registration.getNewsletter());
        password.sendKeys(registration.getPassword());
        confirmation.sendKeys(registration.getConfirmation());

        continueButton.click();
    }


}
