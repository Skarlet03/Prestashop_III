package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage {
    public CheckoutPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//button[@type = 'submit'])[2]")
    public WebElement continueCheckout;

    @FindBy(id = "cgv")
    public WebElement agreeToTerms;

    @FindBy(xpath = "//a[@title='Pay by check.']")
    public WebElement payByCheckOption;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    public WebElement alertMessege;

    @FindBy(xpath = "//span[@class='heading-counter']")
    public WebElement cartContainsMessage;

    @FindBy(xpath = "(//a[@title='Delete'])[1]")
    public WebElement iconTrash;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    public WebElement alertWarning;

}
