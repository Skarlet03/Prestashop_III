package com.prestashop.pages;


import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {

    public AddToCartPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
    public WebElement confirmationMessage;

    @FindBy(id = "layer_cart_product_quantity")
    public WebElement productQuantity;

    @FindBy(id = "layer_cart_product_attributes")
    public WebElement productSize;

    @FindBy(id = "layer_cart_product_price")
    public WebElement productPrice;

    @FindBy(id = "layer_cart_product_title")
    public WebElement productName;

    @FindBy(xpath = "//span[@class='cross']")
    public WebElement closeWindow;


    @FindBy(linkText = "Proceed to checkout")
    public WebElement proceedToCheckout;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement continueShopping;

    @FindBy(xpath = "//a[@class='ajax_cart_block_remove_link']")
    public WebElement removeItemFromCart;

}
