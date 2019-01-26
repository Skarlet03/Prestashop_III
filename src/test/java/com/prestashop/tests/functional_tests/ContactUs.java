package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ContactUs {
    WebDriver driver;
    Actions actions;

    @BeforeMethod
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
    }
    Faker faker = new Faker();
    String email= faker.internet().emailAddress();
     /*
    TEST CASE: CONTACT US USING VALID CREDENTIALS
            1. Open	browser
            2. Go	to http://automationpractice.com/index.php
            3. Click	on	contact us
            4. Fill up all info
            5. Verify confirmation	message	Your message has been successfully sent to our team.

     */
     @Test
     public void contactUs() {
         driver.findElement(By.linkText("Contact us")).click();
         Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Contact us - My store"));
         Select select = new Select(driver.findElement(By.id("id_contact")));
         Random random = new Random();
         select.selectByIndex(random.nextInt(select.getOptions().size()+2));
         driver.findElement(By.id("email")).sendKeys(email);
         driver.findElement(By.id("id_order")).sendKeys("12365");
         driver.findElement(By.id("message")).sendKeys("Messege here");
         driver.findElement(By.xpath("(//button[@type = 'submit'])[2]")).click();
         Assert.assertTrue(driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText().equalsIgnoreCase("Your message has been successfully sent to our team."));

     }
}
