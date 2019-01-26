package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class ErrorMessageValidation extends TestBase {

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
    public void registerWrongName() throws InterruptedException{
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(email + Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.id("id_gender"+(random.nextInt(2)+1)+"")).click();

        driver.findElement(By.id("customer_lastname")).sendKeys(lName);
        String passwd = faker.internet().password();
        driver.findElement(By.id("passwd")).sendKeys(passwd);
        Select s1 = new Select(driver.findElement(By.id("days")));
        day = random.nextInt(s1.getOptions().size())+1;
        s1.selectByIndex(day);
        Select s2=new Select(driver.findElement(By.id("months")));
        month  =random.nextInt(s2.getOptions().size())+1;
        s2.selectByIndex(month);
        Select s3 = new Select(driver.findElement(By.id("years")));
        year = random.nextInt(s3.getOptions().size())+1;
        s3.selectByIndex(year);
        driver.findElement(By.id("address1")).sendKeys(street);
        driver.findElement(By.id("city")).sendKeys(city);
        Select s4 = new Select(driver.findElement(By.id("id_state")));
        s4.selectByIndex(random.nextInt(s4.getOptions().size())+1);
        driver.findElement(By.id("postcode")).sendKeys(zip);
        cellN = cellN.substring(0,cellN.length()-1);
        driver.findElement(By.id("phone_mobile")).sendKeys(cellN+Keys.ENTER);
        String erroeMess = driver.findElement(By.xpath("//ol/li")).getText();
        System.out.println(erroeMess) ;
        Assert.assertTrue(erroeMess.equalsIgnoreCase("firstname is required."));
    }

}
