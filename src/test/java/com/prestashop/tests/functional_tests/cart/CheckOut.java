package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckOut extends TestBase {


    HomePage homePage;
    AddToCartPage addToCartPage;
    ItemDetailsPage itemDetailsFrame;
    SignInPage signInPage;
    CheckoutPage checkoutPage;
    BrowserUtils browserUtils;
    WebPageHeader webPageHeader;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        homePage = new HomePage();
        addToCartPage = new AddToCartPage();
        itemDetailsFrame = new ItemDetailsPage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        browserUtils = new BrowserUtils();
        webPageHeader = new WebPageHeader();
    }


    private static final String USERNAME= "Tester@yahoo.com";
    private static final String PASSWORD = "123456";


    /*
    TEST CASE: CHECKOUT USING VALID CREDENTIALS
            1. Open	browser
            2. Go	to http://automationpractice.com/index.php
            3. Click	on	any	product that	is	not	on sale
            4. Enter	a	random	quantity	between	2 and	5
            5. Select	a	different	size
            6. Click	on	add	to	cart
            7. Proceed to proceedToCheckout
            8. Choose pay with check
            9. Verify confirmation	message	Your order on My Store is complete.

     */

    @Test
    public void cartDetails(){
        signInPage.login(USERNAME,PASSWORD);
        webPageHeader.logo.click();
        //List of all futured items
        List<WebElement> listOfItems = homePage.listOfItems();
        //Index position of chosen item
        int indexChoose = random.nextInt(listOfItems.size());
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        int count = random.nextInt(4) + 2;
        itemDetailsFrame.quantity.clear();
        itemDetailsFrame.quantity.sendKeys(count + "");
        Select itemSize = new Select(itemDetailsFrame.sizesDropdownMenu);
        int sizeIdx = random.nextInt(itemSize.getOptions().size());
        itemSize.selectByIndex(sizeIdx);
        itemDetailsFrame.addToCart.click();
        browserUtils.wait(1);
        addToCartPage.proceedToCheckout.click();
        addToCartPage.proceedToCheckout.click();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        browserUtils.wait(1);
        driver.switchTo().parentFrame();
        checkoutPage.continueCheckout.click();
        checkoutPage.agreeToTerms.click();
        checkoutPage.continueCheckout.click();
        checkoutPage.payByCheckOption.click();
        checkoutPage.continueCheckout.click();
        assertEquals(checkoutPage.alertMessege.getText(),"Your order on My Store is complete.");
    }

}
