package com.prestashop.pages;

import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.Random;

public class RegistrationPage {
    public RegistrationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h1[@class='page-heading']")
    public WebElement pageHeading;

    @FindBy(id = "email")
    public WebElement emailInputBox;

    @FindBy(id = "customer_firstname")
    public WebElement firstNameInputBox;

    @FindBy(id = "customer_lastname")
    public WebElement lastNameInputBox;

    @FindBy(id = "passwd")
    public WebElement passwordInputBox;

    @FindBy(id = "address1")
    public WebElement streetInputBox;

    @FindBy(id = "city")
    public WebElement cityInputBox;

    @FindBy(id = "id_state")
    public WebElement stateDropDownMenu;

    @FindBy(id = "postcode")
    public WebElement postcodeInputBox;

    @FindBy(id = "phone_mobile")
    public WebElement phoneInputBox;

    @FindBy(xpath = "//p[@class='info-account']")
    public WebElement welcomeToAccountHeading;

    @FindBy(id = "days")
    public WebElement daysDropdown;

    @FindBy(id = "months")
    public WebElement monthsDropdown;

    @FindBy(id = "years")
    public WebElement yearDropdown;


}
