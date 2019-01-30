package com.prestashop.tests.functional_tests;

import com.prestashop.pages.HomePage;
import com.prestashop.pages.WebPageHeader;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Search extends TestBase {


    HomePage homePage;
    WebPageHeader webPageHeader;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        homePage = new HomePage();
        webPageHeader = new WebPageHeader();
    }

    @Test
    public void search(){
        //List of all futured items
        List<WebElement> listOfItems = homePage.listOfItems();
        //Index position of chosen item
        int indexChoose = random.nextInt(listOfItems.size());
        String searchTerm = homePage.item(indexChoose, "name").getText().toLowerCase();
        webPageHeader.searchBar.sendKeys(searchTerm + Keys.ENTER);
        assertEquals(driver.getTitle(),"Search - My Store");
        assertTrue(webPageHeader.searchHeader.getText().toLowerCase().contains(searchTerm));
    }
}
