package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    public SignInPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "Sign in")
    public WebElement signInButton;

    @FindBy(id = "email")
    public WebElement loginUserIdInput;

    @FindBy(id = "email_create")
    public WebElement emailInputCreateNew;

    @FindBy(id = "SubmitCreate")
    public WebElement submitCreateAccount;

    @FindBy (id = "passwd")
    public WebElement loginPasswordInput;

    @FindBy(id = "our_price_display")
    public WebElement getPriceDisplayed;

    @FindBy(xpath = "//div[@class='alert alert-danger']/ol/li")
    public WebElement alert;


    public void login(String username, String password) {
        this.signInButton.click();
        this.loginUserIdInput.sendKeys(username);
        this.loginPasswordInput.sendKeys(password+ Keys.ENTER);
    }



}
