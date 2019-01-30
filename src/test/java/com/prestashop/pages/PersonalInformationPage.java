package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInformationPage {
    public PersonalInformationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='My personal information']")
    public WebElement myPersonalInfoButton;

    @FindBy(id = "firstname")
    public WebElement firstNameInputBox;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "lastname")
    public WebElement lastNameInputBox;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement saveButton;

    @FindBy(xpath = "//ol/li")
    public WebElement errorMessege;

    @FindBy(linkText = "Back to your account")
    public WebElement backToAccountButton;

}
