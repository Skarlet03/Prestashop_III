package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemDetailsPage {
    public ItemDetailsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "our_price_display")
    public WebElement price;

    @FindBy(xpath = "//h1[@itemprop]")
    public WebElement name;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    public WebElement quantity;

    @FindBy(id = "group_1")
    public WebElement sizesDropdownMenu;

    @FindBy(xpath= "//p[@id='add_to_cart']/button")
    public WebElement addToCart;
}
