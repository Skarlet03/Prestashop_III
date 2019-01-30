package com.prestashop.tests;

import com.prestashop.pages.WOLoginPage;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WONegativeLoginTests extends TestBase {

    @BeforeMethod
    public void setup(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/\n" +
                "login.aspx");
    }

    WOLoginPage woLoginPage;
    @BeforeMethod
    public void setupPages() {
        woLoginPage = new WOLoginPage();
    }
    /*
    WO-1:	Positive	Login	Test
        1. Open	browser
        2. Go	to	website
        http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/
        login.aspx
        3. Verify	title	equals	“Web	Orders	Login”
        4. Enter	username	“Tester”
        5. Enter	password	“test”
        6. Click	on	Login	button
        7. Verify	title	equals	“Web	Orders”
        8. Verify	url	equals
        http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/
        login.aspx
     */
    @Test
    public void WO1(){
        assertEquals(driver.getTitle(),"Web Orders Login");
        woLoginPage.login("Tester", "test");
        assertEquals(driver.getTitle(),"Web Orders");
        assertEquals(driver.getCurrentUrl(),"http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
        //<----FALSE
    }

    /*
    WO-2:	Negative	Login	Test Wrong	Username
        1. Open	browser
        2. Go	to	website
        http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/l
        ogin.aspx
        3. Verify	title	equals	“Web	Orders	Login”
        4. Save	the	current	url
        5. Enter	username	“Test”
        6. Enter	password	“Test”
        7. Click	on	Login	button
        8. Verify	title	still	equals	“Web	Orders Login”
        9. Verify the	current url	equals	the	string	saved	in	step	4
     */
    @Test
    public void WO2(){
        assertEquals(driver.getTitle(),("Web Orders Login"));
        String currentUrl = driver.getCurrentUrl();
       woLoginPage.login("Test", "Test");
        assertEquals(driver.getTitle(),"Web Orders Login");
        assertEquals(driver.getCurrentUrl(),currentUrl);
    }

    /*
    WO-3:	Negative	Login	Test Wrong	Password

        1. Open	browser
        2. Go	to	website
        http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/l
        ogin.aspx
        3. Verify	title	equals	“Web	Orders	Login”
        4. Save	the	current	url
        5. Enter	username	“Tester”
        6. Enter	password	“Tester”
        7. Click	on	Login	button
        8. Verify	title	still	equals	“Web	Orders	Login”
        9. Verify	the	current	url	equals	the	string	saved	in	step	4
     */
    @Test
    public  void wo3(){
        assertEquals(driver.getTitle(),("Web Orders Login"));
        String currentUrl = driver.getCurrentUrl();
        woLoginPage.login("Tester", "Tester");
        assertEquals(driver.getTitle(),"Web Orders Login");
        assertEquals(driver.getCurrentUrl(),currentUrl);
    }

    /*
    WO-4:	Negative	Login	Test Blank	Username
        1. Open	browser
        2. Go	to	website
        http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/l
        ogin.aspx
        3. Verify	title	equals	“Web	Orders	Login”
        4. Save	the	current	url
        5. Enter	password	“test”
        6. Click	on	Login	button
        7. Verify	title	still	equals	“Web	Orders	Login”
        8. Verify	the	current	url	equals	the	string	saved	in	step	4
     */
    @Test
    public  void wo4(){
        assertEquals(driver.getTitle(),("Web Orders Login"));
        String currentUrl = driver.getCurrentUrl();
        woLoginPage.login("", "test");
        assertEquals(driver.getTitle(),"Web Orders Login");
        assertEquals(driver.getCurrentUrl(),currentUrl);
    }

    /*
    WO-5:	Negative	Login	Test Blank	Password
        1. Open	browser
        2. Go	to	website
        http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/l
        ogin.aspx
        3. Verify	title	equals	“Web	Orders	Login”
        4. Save	the	current	url
        5. Enter	username	“Tester”
        6. Click	on	Login	button
        7. Verify	title	still	equals	“Web	Orders	Login”
        8. Verify	the	current	url	equals	the	string	saved	in	step	4
     */
    @Test
    public  void wo5(){
        assertEquals(driver.getTitle(),("Web Orders Login"));
        String currentUrl = driver.getCurrentUrl();
        woLoginPage.login("Tester", "");
        assertEquals(driver.getTitle(),"Web Orders Login");
        assertEquals(driver.getCurrentUrl(),currentUrl);
    }
}
