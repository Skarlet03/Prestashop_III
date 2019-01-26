package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
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

public class Registration extends TestBase {

    @BeforeMethod
    void setup(){
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
    }


    Faker faker = new Faker();
    Random random = new Random();
    String email= faker.internet().emailAddress();
    String fName = faker.name().firstName();
    String lName = faker.name().lastName();
    String street = faker.address().streetAddress();
    String city = faker.address().city();
    String zip = faker.address().zipCode().substring(0,5);
    String cellN = faker.phoneNumber().cellPhone();
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
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(email + Keys.ENTER);
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='page-heading']")).getText().equalsIgnoreCase("Create an account"));
        driver.findElement(By.id("id_gender" + (random.nextInt(2) + 1) + "")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(fName);
        driver.findElement(By.id("customer_lastname")).sendKeys(lName);
        String passwd = faker.internet().password();
        driver.findElement(By.id("passwd")).sendKeys(passwd);
        Select s1 = new Select(driver.findElement(By.id("days")));
        day = random.nextInt(s1.getOptions().size()) + 1;
        s1.selectByIndex(day);
        Select s2 = new Select(driver.findElement(By.id("months")));
        month = random.nextInt(s2.getOptions().size()) + 1;
        s2.selectByIndex(month);
        Select s3 = new Select(driver.findElement(By.id("years")));
        year = random.nextInt(s3.getOptions().size()) + 1;
        s3.selectByIndex(year);
        driver.findElement(By.id("address1")).sendKeys(street);
        driver.findElement(By.id("city")).sendKeys(city);
        Select s4 = new Select(driver.findElement(By.id("id_state")));
        s4.selectByIndex(random.nextInt(s4.getOptions().size()) + 1);
        driver.findElement(By.id("postcode")).sendKeys(zip);
        cellN = cellN.substring(0, cellN.length() - 1);
        driver.findElement(By.id("phone_mobile")).sendKeys(cellN + Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='info-account']")).getText().equals("Welcome to your account. Here you can manage all of your personal information and orders."));
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
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(fName + Keys.ENTER);
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='create_account_error']/ol/li")).getText().equalsIgnoreCase("Invalid email address."));
    }
}
