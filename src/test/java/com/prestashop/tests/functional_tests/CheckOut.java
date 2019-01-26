package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
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

public class CheckOut {
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
    public void signIn(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("Myroslavapavliuk@yahoo.com");
        driver.findElement(By.id("passwd")).sendKeys("123456" + Keys.ENTER);
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
    TEST CASE: CHECKOUT USING VALID CREDENTIALS
            1. Open	browser
            2. Go	to http://automationpractice.com/index.php
            3. Click	on	any	product that	is	not	on sale
            4. Enter	a	random	quantity	between	2 and	5
            5. Select	a	different	size
            6. Click	on	add	to	cart
            7. Proceed to checkout
            8. Choose pay with check
            9. Verify confirmation	message	Your order on My Store is complete.

     */


    @Test
    public void cartDetails() throws InterruptedException{
        signIn();
        actions = new Actions(driver);
        driver.get("http://automationpractice.com");
        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[2]")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
        int count = random.nextInt(4) + 2;
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(count + "");
        Select size = new Select(driver.findElement(By.id("group_1")));
        int sizeIdx = random.nextInt(size.getOptions().size());
        size.selectByIndex(sizeIdx);

        driver.findElement(By.xpath("//p[@id='add_to_cart']/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Proceed to checkout")).click();
        driver.findElement(By.linkText("Proceed to checkout")).click();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(500);
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("(//button[@type = 'submit'])[2]")).click();
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.xpath("(//button[@type = 'submit'])[2]")).click();
        driver.findElement(By.xpath("//a[@title='Pay by check.']")).click();
        driver.findElement(By.xpath("(//button[@type = 'submit'])[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText().equalsIgnoreCase("Your order on My Store is complete."));
    }

}
