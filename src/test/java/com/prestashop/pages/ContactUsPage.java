package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ContactUsPage {
    public ContactUsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "id_contact")
    public WebElement departmentDropdown;

    @FindBy(id = "id_order")
    public WebElement order;

    @FindBy(id = "message")
    public WebElement messageInput;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement submitButton;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    public WebElement alertMessage;

}
