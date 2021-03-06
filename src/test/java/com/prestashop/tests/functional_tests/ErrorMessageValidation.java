package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ErrorMessageValidation extends TestBase {

    SignInPage signInPage;
    PersonalInformationPage personalInformationPage;
    RegistrationPage registrationPage;
    BrowserUtils browserUtils;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        signInPage = new SignInPage();
        personalInformationPage = new PersonalInformationPage();
        registrationPage = new RegistrationPage();
        browserUtils = new BrowserUtils();
    }
    Faker faker = new Faker();
    String email= faker.internet().emailAddress();
    String lName = faker.name().lastName();
    String street = faker.address().streetAddress();
    String city = faker.address().city();
    String zip = faker.address().zipCode().substring(0,5);
    String cellN = faker.phoneNumber().cellPhone();
    String passwd = faker.internet().password();
    int day;
    int month;
    int year;


    /*
        1. Open	browser
        2. Go	to	http://automationpractice.com/index.php
        3. Click	Sign	in link
        4. Enter	new valid email	to	the	email	field
        5. Click	on	Create	Account
        6. Fill	all	the	required	steps	except	for	first	name
        7. Click	on	Register
        8. Verify	that	error	message firstname is required. is	displayed
     */

    @Test
    public void registerWrongName(){
        signInPage.signInButton.click();
        signInPage.emailInputCreateNew.sendKeys(email);
        signInPage.submitCreateAccount.click();
        browserUtils.wait(2);
        driver.findElement(By.id("id_gender"+(random.nextInt(2)+1)+"")).click();
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
        assertEquals(personalInformationPage.errorMessege.getText(),"firstname is required.");
    }

}
