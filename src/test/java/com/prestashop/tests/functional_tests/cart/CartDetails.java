package com.prestashop.tests.functional_tests.cart;


import com.prestashop.pages.AddToCartPage;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemDetailsPage;
import com.prestashop.pages.WebPageHeader;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;



public class CartDetails extends TestBase {

    HomePage homePage;
    AddToCartPage addToCartPage;
    ItemDetailsPage itemDetailsFrame;
    WebPageHeader webPageHeader;
    BrowserUtils browserUtils;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        homePage = new HomePage();
        addToCartPage = new AddToCartPage();
        itemDetailsFrame = new ItemDetailsPage();
        webPageHeader = new WebPageHeader();
        browserUtils = new BrowserUtils();
    }

    /*
    CartDetails	Details
            1. Open	browser
            2. Go	to http://automationpractice.com/index.php
            3. Click	on	any	product that	is	not	on sale
            4. Enter	a	random	quantity	between	2 and	5
            5. Select	a	different	size
            6. Click	on	add	to	cart
            7. Verify confirmation	message	Product	successfully	added	to	your	shopping	cart
            8. Dismiss	the	confirmation	window	by	clicking	on	the	x	icon
            9. Click	on	the	company	logo
            10. Click	on	any	product that	is on sale
            11. Enter	a	random	quantity	between	2 and	5
            12. Select	a	different	size
            13. Click	on	add	to	cart
            14. Verify confirmation	message	Product	successfully	added	to	your
            shopping	cart
            15. Dismiss	the	confirmation	window	by	clicking	on	the	x	icon
            16. Hover	over	the	CartDetails	icon
            17. Verify	that	correct	total	is	displayed
            18. Verify	that	total	is	correct	based	on	the	price	and	item	count	of	the
            products	you	added	to	cart.	(Shipping	is	always	$2)
     */

    @Test
    public void cartDetails(){

        //List of stored indexes of items on sale
        List<Integer> indexesOfItemsOnSale = homePage.onSaleItems();
        //Index position of chosen item
        int idx = random.nextInt(indexesOfItemsOnSale.size());
        int indexChoose = indexesOfItemsOnSale.get(idx);
        Double total = 0.0;
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        int count = random.nextInt(4) + 2;
        double price = Double.parseDouble(itemDetailsFrame.price.getText().replace("$",""));
        total+=count*price;
        itemDetailsFrame.quantity.clear();
        itemDetailsFrame.quantity.sendKeys(count + "");
        Select itemSize = new Select(itemDetailsFrame.sizesDropdownMenu);
        int sizeIdx = random.nextInt(itemSize.getOptions().size());
        itemSize.selectByIndex(sizeIdx);
        itemDetailsFrame.addToCart.click();
        assertEquals(addToCartPage.confirmationMessage.getText(),"Product successfully added to your shopping cart");

        browserUtils.wait(1);
        addToCartPage.closeWindow.click();
        driver.switchTo().parentFrame();
        webPageHeader.logo.click();
        idx = random.nextInt(indexesOfItemsOnSale.size());
        indexChoose = indexesOfItemsOnSale.get(idx);
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        count = random.nextInt(4) + 2;
        price = Double.parseDouble(itemDetailsFrame.price.getText().replace("$",""));
        total+=count*price;
        itemDetailsFrame.quantity.clear();
        itemDetailsFrame.quantity.sendKeys(count + "");
        sizeIdx = random.nextInt(itemSize.getOptions().size());
        itemSize.selectByIndex(sizeIdx);
        itemDetailsFrame.addToCart.click();
        assertEquals(addToCartPage.confirmationMessage.getText(),"Product successfully added to your shopping cart");

        browserUtils.wait(1);
        addToCartPage.closeWindow.click();
        actions.moveToElement(webPageHeader.viewCart).pause(2).perform();
        browserUtils.wait(1);
        double cartTotal = Double.parseDouble(webPageHeader.cartTotal.getText().substring(1));
        cartTotal=cartTotal-2.0;
        System.out.println(total);
        System.out.println(cartTotal);
        assertEquals(total ,cartTotal);


        //Sometimes false, even thus totals are the same visibly
        //ex:
        //98.39999999999999
        //98.4
    }
}
