package com.prestashop.tests.smoke_tests;

import com.prestashop.pages.MyAddressPage;
import com.prestashop.pages.PersonalInformationPage;
import com.prestashop.pages.SignInPage;
import com.prestashop.pages.WebPageHeader;
import com.prestashop.utilities.TestBase;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Account_Information extends TestBase {

    SignInPage signInPage;
    PersonalInformationPage personalInformationPage;
    WebPageHeader webPageHeader;
    MyAddressPage myAddressPage;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        signInPage = new SignInPage();
        personalInformationPage = new PersonalInformationPage();
        webPageHeader = new WebPageHeader();
        myAddressPage = new MyAddressPage();
    }

    private static final String USERNAME= "Tester@yahoo.com";
    private static final String PASSWORD = "123456";
    private static final String USER_FULL_NAME = "Test Tester";

    //Login:	my	account
    // 1. Go	to	http://automationpractice.com/index.php
    // 2. Click	Sign in link
    //3. Login	using	valid	username	and	password
    // 4. Verify	that	title contains My	account
    //5. Verify	that	account	holder	full	name	is	displayed	next	to	the	Sign	out	link

    @Test

    public void testMyAccount(){
        signInPage.login(USERNAME,PASSWORD);
        assertTrue(driver.getTitle().contains("My account"));
        assertEquals(webPageHeader.accountHolderNameOnPage.getText(),USER_FULL_NAME);
        assertTrue(webPageHeader.accountHolderNameOnPage.isDisplayed());

    }

    //Login:	My	personal	information
    //6. Click	on	My	personal	information button
    // 7. Verify	title	contains	Identity
    // 8. Verify	that first	name	and	last	name	matches	the	full	name	on	top
    // 9. Click	on	Save	button
    //10. Verify	error	message	“The	password	you	entered	is	incorrect.”
    // 11. Click	on Back	to	your	account
    // 12. Verify	that	title contains My	account

    @Test
    public void personalInfo() {
        signInPage.login(USERNAME, PASSWORD);
        personalInformationPage.myPersonalInfoButton.click();
        assertTrue(driver.getTitle().contains("Identity"));
        String nameOnTop = webPageHeader.accountHolderNameOnPage.getText();
        String inputBoxName = personalInformationPage.firstNameInputBox.getAttribute("value") + " " +
        personalInformationPage.lastNameInputBox.getAttribute("value");
        assertTrue(nameOnTop.equalsIgnoreCase(inputBoxName));
        personalInformationPage.saveButton.click();
        assertEquals(personalInformationPage.errorMessege.getText(),"The password you entered is incorrect.");
        personalInformationPage.backToAccountButton.click();
        assertTrue(driver.getTitle().contains("My account"));
    }


    //Login:	My	addresses
    //13. Click on	My	addresses
    //14. Click on	Add a	new	address
    //15. Verify	that first	name	and	last	name	matches	the	full	name	on	top
    //16. Delete	the	first	name
    // 17. Click	save
    //   18. Verify	error	message	“firstname	is	required.”

    @Test
    public void addressInfo() {
        signInPage.login(USERNAME, PASSWORD);
        myAddressPage.myAddressesButton.click();
        myAddressPage.addNewAddressesButton.click();
        String nameOnTop = webPageHeader.accountHolderNameOnPage.getText();
        String inputBoxName = myAddressPage.firstNameInputBox.getAttribute("value") + " " +
                myAddressPage.lastNameInputBox.getAttribute("value");
        assertTrue(nameOnTop.equalsIgnoreCase(inputBoxName));
        myAddressPage.firstNameInputBox.clear();
        myAddressPage.submitAddressButton.click();
        assertEquals(myAddressPage.errorMessege.getText(),"firstname is required.");
    }
}
