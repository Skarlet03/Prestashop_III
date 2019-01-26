package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Login {

    WebDriver driver;

    @BeforeMethod
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
    String passwd = faker.internet().password();
    Select s1 ;
    Select s2;
    Select s3 ;
    int day;
    int month;
    int year;


    public void register() throws InterruptedException {
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(email + Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.id("id_gender" + (random.nextInt(2) + 1) + "")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(fName);
        driver.findElement(By.id("customer_lastname")).sendKeys(lName);
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
    }

     /*
     TEST CASE #1: LOGIN USING ALL VALID INFORMATION
    1. Go	to	http://automationpractice.com/index.php
    2. Click	Sign in link
    3. Enter registered email and and password
    4. Verify messege "Welcome to your account. Here you can manage all of your personal information and orders."
     */

    @Test
    public void loginPositiveScenario() throws InterruptedException {
        register();
        driver.get("http://automationpractice.com");
        driver.findElement(By.linkText("Sign out")).click();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(passwd + Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='info-account']")).getText().equals("Welcome to your account. Here you can manage all of your personal information and orders."));
    }


    /*
     TEST CASE #2: LOGIN USING INVALID PASSWORD
    1. Go	to	http://automationpractice.com/index.php
    2. Click	Sign in link
    3. Enter valid email and invalid password
    4. Verify messege 'Authentication failed.' appears on the screen
     */
    @Test
    public void loginNegativeScenario() throws InterruptedException {
        register();
        driver.get("http://automationpractice.com");
        driver.findElement(By.linkText("Sign out")).click();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(faker.internet().password() + Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText().equalsIgnoreCase("Authentication failed."));

    }


}
