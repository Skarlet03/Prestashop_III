package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPageHeader {
    public WebPageHeader(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//a[@rel='nofollow']/span)[1]")
    public WebElement accountHolderNameOnPage;

    @FindBy(xpath = " //img[@alt='My Store']")
    public WebElement logo;

    @FindBy(xpath = "//div/a/b")
    public WebElement viewCart;

    @FindBy(xpath = "(//div[@class='cart-prices']/div/span)[3]")
    public WebElement cartTotal;

    @FindBy(xpath = "//a[@class='logout']")
    public WebElement signOut;

    @FindBy(id = "search_query_top")
    public WebElement searchBar;

    @FindBy(xpath = "//span[@class='lighter']")
    public WebElement searchHeader;

    @FindBy(linkText = "Contact us")
    public WebElement contactUs;

    @FindBy(xpath = "//a[@class='cart_block_product_name']")
    public WebElement cartItem;

    @FindBy(xpath = " //span[@class='ajax_cart_no_product']")
    public WebElement emptyCart;










}
