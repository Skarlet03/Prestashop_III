package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

public class CartActionsTests extends TestBase {


    HomePage homePage;
    AddToCartPage addToCartPage;
    ItemDetailsPage itemDetailsFrame;
    WebPageHeader webPageHeader;
    SignInPage signInPage;
    BrowserUtils browserUtils;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        homePage = new HomePage();
        addToCartPage = new AddToCartPage();
        itemDetailsFrame = new ItemDetailsPage();
        webPageHeader = new WebPageHeader();
        signInPage = new SignInPage();
        browserUtils = new BrowserUtils();
        checkoutPage = new CheckoutPage();
    }


    private static final String USERNAME= "Tester@yahoo.com";
    private static final String PASSWORD = "123456";

    //List of all futured items
    List<WebElement> listOfItems = homePage.listOfItems();
    //Index position of chosen item
    int indexChoose;


    /*
    Cart	Login Test
        1. Open	browser
        2. Go	to	http://automationpractice.com/index.php
        3. Add	any	product	in	the	homepage	to	the	cart
        4. Hover	over	the	cart	icon
        5. Verify	that	cart	contains	the	product
        6. Login	as	any	valid	user
        7. Hover	over	the	cart	icon
        8. Verify that	cart	information	is	same	as	step	5
     */


    @Test
    public void cartLogin(){
        indexChoose= random.nextInt(listOfItems.size());
        String nameHome = homePage.item(indexChoose, "name").getText();
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        itemDetailsFrame.addToCart.click();
        browserUtils.wait(2);
        addToCartPage.closeWindow.click();
        actions.moveToElement(webPageHeader.viewCart).pause(1).perform();
        assertEquals(webPageHeader.cartItem.getAttribute("title"), nameHome);
        signInPage.login(USERNAME,PASSWORD);
        actions.moveToElement(webPageHeader.viewCart).pause(1).perform();
        assertEquals(webPageHeader.cartItem.getAttribute("title"), nameHome);

    }

    /*
    1. Open	browser
    2. Go	to	http://automationpractice.com/index.php
    3. Login	as	any	valid	user
    4. Go	back	to	home	page
    5. Add	any	product	in	the	homepage	to	the	cart
    6. Hover	over	the	cart	icon
    7. Verify	that	cart	contains	the	product
    8. Log	out
    9. Verify	the	cart	contains the	word	empty
     */

    @Test
    public void cartLogout(){
        signInPage.login(USERNAME,PASSWORD);
        webPageHeader.logo.click();
        indexChoose = random.nextInt(listOfItems.size());
        String nameHome = homePage.item(indexChoose, "name").getText();
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        itemDetailsFrame.addToCart.click();
        browserUtils.wait(2);
        addToCartPage.closeWindow.click();
        actions.moveToElement(webPageHeader.viewCart).pause(1).perform();
        assertEquals(webPageHeader.cartItem.getAttribute("title"), nameHome);
        webPageHeader.signOut.click();
        actions.moveToElement(webPageHeader.viewCart).pause(1).perform();
        assertTrue(webPageHeader.emptyCart.getText().contains( "empty"));

    }

    /*
    Cart	Icon	Delete Test
        1. Open	browser
        2. Go	to	http://automationpractice.com/index.php
        3. Add	any	product	in	the	homepage	to	the	cart
        4. Click	on	Continue	shopping
        5. Hover	over	the	cart	icon
        6. Click	the	x	to	delete	the	product
        7. Verify	word	empty is	displayed	in	the	Cart	icon
     */
    @Test
    public void deleteTest(){
        indexChoose= random.nextInt(listOfItems.size());
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        itemDetailsFrame.addToCart.click();
        browserUtils.wait(1);
        addToCartPage.continueShopping.click();
        actions.moveToElement(webPageHeader.viewCart).pause(1).perform();
        addToCartPage.removeItemFromCart.click();
        browserUtils.wait(1);
        assertTrue(webPageHeader.emptyCart.getText().contains( "empty"));
    }

    /*
    Cart	Checkout	Delete Test
        1. Open	browser
        2. Go	to	http://automationpractice.com/index.php
        3. Add	any	product	in	the	homepage	to	the	cart
        4. Click	on	Continue	shopping
        5. Add	another	product	in	the	homepage	to	the	cart
        6. Click	on	Proceed	to	checkout
        7. Verify	message	Your	shopping	cart	contains:	2	Products
        8. Click	the	delete	icon	to	delete	one	of	the	products
        9. Verify	message	Your	shopping	cart	contains:	1	Product
        10.Click	the	delete	icon	to	delete	the	second	product
        11. Verify message Your shopping cart is empty.
     */

    @Test
    public void CheckouDeleteTest() {
        indexChoose = random.nextInt(listOfItems.size());
        homePage.item(indexChoose, "clickOnItem").click();
        homePage.switchToProductframe();
        itemDetailsFrame.addToCart.click();
        browserUtils.wait(2);
        addToCartPage.continueShopping.click();
        driver.switchTo().parentFrame();
        indexChoose = random.nextInt(listOfItems.size());
        homePage.item(indexChoose, "clickOnItem").click();
        homePage.switchToProductframe();
        itemDetailsFrame.addToCart.click();
        browserUtils.wait(1);
        addToCartPage.proceedToCheckout.click();
        assertEquals(checkoutPage.cartContainsMessage.getText(), "Your shopping cart contains: 2 Products" );
        checkoutPage.iconTrash.click();
        browserUtils.wait(1);
        assertEquals(checkoutPage.cartContainsMessage.getText(), "Your shopping cart contains: 1 Product" );
        checkoutPage.iconTrash.click();
        browserUtils.wait(1);
        assertEquals(checkoutPage.alertWarning.getText(), "Your shopping cart is empty." );
    }

}
