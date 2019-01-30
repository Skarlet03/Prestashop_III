package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegistrationTest extends TestBase {

    SignInPage signInPage;
    PersonalInformationPage personalInformationPage;
    WebPageHeader webPageHeader;
    MyAddressPage myAddressPage;
    RegistrationPage registrationPage;

    @BeforeMethod
    public void setupPages() {
        signInPage = new SignInPage();
        personalInformationPage = new PersonalInformationPage();
        webPageHeader = new WebPageHeader();
        myAddressPage = new MyAddressPage();
        registrationPage = new RegistrationPage();
    }



    /*
    PositiveAndNegativeRegistrationTest Test
        1. Open	browser
        2. Go	to	http://automationpractice.com/index.php
        3. Click	Sign	in link
        4. Enter	new valid email	to	the	email	field
        5. Click	on	Create	Account
        6. Verify	that	that	email	link	displays	current email
        7. Fill	out	all	the	required	steps
        8. Click	on	Register
        9. Verify	that	correct	first	and	last	name	is displayed correctly	on	top	right
        www.cybertekschool.com training@cybertekschool.com
        2
        10. Click	on	My	personal	information
        11. Verify	that	personal	information	is	displayed correctly
        12. Click on	Back	to	your	account
        13. Click	on	My	addresses	verify	that	address information	is	displayed
        correctly
        14. Click	on sign	out link
        15. Login	using	the	email	and	password	if	the	current	user
        16. Verify	that	correct	first	and	last	name	is displayed correctly	on	top	right
        NOTE: for	the	test	case	above	you	must to	generate	random	information	for	email,
        first	name,	last	name,	phone	etc.
     */

    Faker faker = new Faker();
    String email= faker.internet().emailAddress();
    String fName = faker.name().firstName();
    String lName = faker.name().lastName();
    String street = faker.address().streetAddress();
    String city = faker.address().city();
    String zip = faker.address().zipCode().substring(0,5);
    String cellN = faker.phoneNumber().cellPhone();
    String passwd = faker.internet().password();
    Select s1 ;
    Select s2;
    Select s3 ;
    int day;
    int month;
    int year;


    @Test
    public void register() throws InterruptedException{
        signInPage.signInButton.click();
        signInPage.emailInputCreateNew.sendKeys(email);
        signInPage.submitCreateAccount.click();

        Thread.sleep(2000);

        Assert.assertTrue(registrationPage.pageHeading.getText().equalsIgnoreCase("Create an account"));
        Assert.assertTrue(registrationPage.emailInputBox.getAttribute("value").equalsIgnoreCase(email));
        driver.findElement(By.id("id_gender"+(random.nextInt(2)+1)+"")).click();
        registrationPage.firstNameInputBox.sendKeys(fName);
        registrationPage.lastNameInputBox.sendKeys(lName);
        registrationPage.passwordInputBox.sendKeys(passwd);

        Select s1 = new Select(registrationPage.daysDropdown);
        day = random.nextInt(s1.getOptions().size())+1;
        s1.selectByIndex(day);
        Select s2=new Select(registrationPage.monthsDropdown);
        month  =random.nextInt(s2.getOptions().size());
        s2.selectByIndex(month);
        Select s3 = new Select(registrationPage.yearDropdown);
        year = random.nextInt(s3.getOptions().size())+1;
        s3.selectByIndex(year);

        registrationPage.streetInputBox.sendKeys(street);
        registrationPage.cityInputBox.sendKeys(city);
        Select s4 = new Select(registrationPage.stateDropDownMenu);
        s4.selectByIndex(random.nextInt(s4.getOptions().size())+1);
        registrationPage.postcodeInputBox.sendKeys(zip);
        cellN = cellN.substring(0,cellN.length()-1);
        registrationPage.phoneInputBox.sendKeys(cellN+Keys.ENTER);
        Assert.assertTrue(registrationPage.welcomeToAccountHeading.getText().equals("Welcome to your account. Here you can manage all of your personal information and orders."));
        Assert.assertTrue(webPageHeader.accountHolderNameOnPage.getText().equalsIgnoreCase(fName + " " + lName));
        personalInformationPage.myPersonalInfoButton.click();
        Assert.assertTrue(personalInformationPage.firstNameInputBox.getAttribute("value").equalsIgnoreCase(fName));
        Assert.assertTrue(personalInformationPage.lastNameInputBox.getAttribute("value").equalsIgnoreCase(lName));
        Assert.assertTrue(personalInformationPage.email.getAttribute("value").equalsIgnoreCase(email));
        Assert.assertTrue(s1.getFirstSelectedOption().getText().equals(s1.getOptions().get(day).getText()));
        Assert.assertTrue(s2.getFirstSelectedOption().getText().equals(s2.getOptions().get(month).getText()));
        Assert.assertTrue(s3.getFirstSelectedOption().getText().equals(s3.getOptions().get(year).getText()));
        personalInformationPage.backToAccountButton.click();
        myAddressPage.myAddressesButton.click();
        Assert.assertTrue(myAddressPage.firstNameShippingAddress.getText().equalsIgnoreCase(fName));
        Assert.assertTrue(myAddressPage.lastNameShippingAddress.getText().equalsIgnoreCase(lName));
        Assert.assertTrue(myAddressPage.streetShippingAddress.getText().equalsIgnoreCase(street));
        Assert.assertTrue(myAddressPage.cityShippingAddress.getText().equalsIgnoreCase(city+","));
        Assert.assertTrue(myAddressPage.zipShippingAddress.getText().equalsIgnoreCase(zip));
        Assert.assertTrue(myAddressPage.cellNShippingAddress.getText().equalsIgnoreCase(cellN));
        webPageHeader.signOut.click();


        signInPage.login(email, passwd);
        Assert.assertTrue(webPageHeader.accountHolderNameOnPage.getText().equalsIgnoreCase(fName + " " + lName));
    }

}
