package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ProductInformation extends TestBase {

    HomePage homePage;
    AddToCartPage addToCartPage;
    ItemDetailsPage itemDetailsFrame;


    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        homePage = new HomePage();
        addToCartPage = new AddToCartPage();
        itemDetailsFrame = new ItemDetailsPage();

    }
    /*
    Product information - price:
        1. Go	to	http://automationpractice.com/index.php
        //2. Click	on	any	product
        3. Verify	that	same	name	and	price	displayed	as	on	the	home	page
     */
    @Test
    public void price(){
        //List of all futured items
        List<WebElement> listOfItems = homePage.listOfItems();
        //Index position of chosen item
        int indexChoose = random.nextInt(listOfItems.size());
        String nameHome = homePage.item(indexChoose, "name").getText();
        String priceHome = homePage.item(indexChoose,"price").getText();
        //Click	on	product
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        String priceSelected = itemDetailsFrame.price.getText();
        String nameSelected = itemDetailsFrame.name.getText();
        assertEquals(nameHome,nameSelected);
        assertEquals(priceHome, priceSelected);
    }

    /*
    Product information - details:
        4. that	default	quantity	is	1
        5. Verify	that	default size	is	S
        6. Verify	that	size	options	are	S,	M,	L
     */
    @Test
    public void details() {
        //List of all futured items
        List<WebElement> listOfItems = homePage.listOfItems();
        //Index position of chosen item
        int indexChoose = random.nextInt(listOfItems.size());
        //Click	on	product
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        String quantity = itemDetailsFrame.quantity.getAttribute("value");
        Select select = new Select(itemDetailsFrame.sizesDropdownMenu);
        //storing default selected size
        String defaultSize = select.getFirstSelectedOption().getAttribute("title");
        //all available options from dropdown
        List<WebElement> options = select.getOptions();
        String allOptions = "";
        for (WebElement n : options)
            allOptions += n.getText();

        assertEquals(allOptions,"SML");
        assertEquals(quantity,"1");
        assertEquals(defaultSize,"S");
    }

    /*
    Product information – Add to cart:
        7. Click	on	Add	to	cart
        8. Verify	confirmation	message	“Product	successfully	added	to	your	shopping
        cart”
        9. that	default	quantity	is 1
        10. Verify	that	default size	is	S
        11. Verify	that	same	name	and	price	displayed	as	on	the	home	page
     */

    @Test
    public void addToCart(){
        //List of all futured items
        List<WebElement> listOfItems = homePage.listOfItems();
        //Index position of chosen item
        int indexChoose = random.nextInt(listOfItems.size());
        String nameHome = homePage.item(indexChoose, "name").getText();
        String priceHome = homePage.item(indexChoose,"price").getText();
        //Click	on	product
        homePage.item(indexChoose,"clickOnItem").click();
        homePage.switchToProductframe();
        //click add to cart
        itemDetailsFrame.addToCart.click();
        assertTrue(addToCartPage.confirmationMessage.getText().equals("Product successfully added to your shopping cart"));
        assertTrue(addToCartPage.productQuantity.getText().equals("1"));
        assertTrue(addToCartPage.productSize.getText().contains("S"));
        assertTrue(priceHome.equalsIgnoreCase(addToCartPage.productPrice.getText()));
        assertTrue(nameHome.equalsIgnoreCase(addToCartPage.productName.getText()));
    }


}
