package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PositiveAndNegativeRegistrationTest extends TestBase {

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

     /*
     TEST CASE #1: REGISTERING USING ALL VALID INFORMATION
    1. Go	to	http://automationpractice.com/index.php
    2. Click	Sign in link
    3. Enter valid email and click create an acoount
    4. Verify messege 'Create an account' appears on the top
    5. Fill all required boxes click enter
    6. Verify messege "Welcome to your account. Here you can manage all of your personal information and orders."
     */

    @Test
    public void register() throws InterruptedException {
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
    }


    /*
     TEST CASE #2: REGISTERING USING INVALID EMAIL
    1. Go	to	http://automationpractice.com/index.php
    2. Click	Sign in link
    3. Enter invalid email and click create an acoount
    4. Verify messege 'Invalid email address.' appears on the screen
     */

    @Test
    public void registerUsingInvalidEmail() throws InterruptedException {
        signInPage.signInButton.click();
        signInPage.emailInputCreateNew.sendKeys(fName);
        signInPage.submitCreateAccount.click();
        Thread.sleep(2000);
        Assert.assertTrue(personalInformationPage.errorMessege.getText().equalsIgnoreCase("Invalid email address."));
    }
}
