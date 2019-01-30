package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAddressPage {

    public MyAddressPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "My addresses")
    public WebElement myAddressesButton;

    @FindBy(linkText = "Add a new address")
    public WebElement addNewAddressesButton;

    @FindBy(id = "firstname")
    public WebElement firstNameInputBox;

    @FindBy(id = "lastname")
    public WebElement lastNameInputBox;

    @FindBy(id = "submitAddress")
    public WebElement submitAddressButton;

    @FindBy(xpath = "(//ol/li)[1]")
    public WebElement errorMessege;

    @FindBy(xpath = "//span[@class='address_address1']")
    public WebElement streetInput;

    @FindBy(xpath = "(//span[@class='address_name'])[1]")
    public WebElement firstNameShippingAddress;

    @FindBy(xpath = "(//span[@class='address_name'])[2]")
    public WebElement lastNameShippingAddress;

    @FindBy(xpath = "//span[@class='address_address1']")
    public WebElement streetShippingAddress;

    @FindBy(xpath = "(//li/span)[6]")
    public WebElement cityShippingAddress;

    @FindBy(xpath = "(//li/span)[8]")
    public WebElement zipShippingAddress;

    @FindBy(xpath = "(//li/span)[11]")
    public WebElement cellNShippingAddress;

}
