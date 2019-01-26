package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

public class CartDetails extends TestBase {


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
    CartDetails	Details
            1. Open	browser
            2. Go	to http://automationpractice.com/index.php
            3. Click	on	any	product that	is	not	on sale
            4. Enter	a	random	quantity	between	2 and	5
            5. Select	a	different	size
            6. Click	on	add	to	cart
            www.cybertekschool.com training@cybertekschool.com
            3
            7. Verify confirmation	message	Product	successfully	added	to	your	shopping	cart
            8. Dismiss	the	confirmation	window	by	clicking	on	the	x	icon
            9. Click	on	the	company	logo
            10. Click	on	any	product that	is on sale
            11. Enter	a	random	quantity	between	2 and	5
            12. Select	a	different	size
            13. Click	on	add	to	cart
            14. Verify confirmation	message	Product	successfully	added	to	your
            shopping	cart
            15. Dismiss	the	confirmation	window	by	clicking	on	the	x	icon
            16. Hover	over	the	CartDetails	icon
            17. Verify	that	correct	total	is	displayed
            18. Verify	that	total	is	correct	based	on	the	price	and	item	count	of	the
            products	you	added	to	cart.	(Shipping	is	always	$2)
     */


    @Test
    public void cartDetails() throws InterruptedException{
        Double total = 0.0;
        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[2]")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
        int count = random.nextInt(4) + 2;
        double price = Double.parseDouble(driver.findElement(By.id("our_price_display")).getText().replace("$",""));
        total+=count*price;
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(count + "");
        Select size = new Select(driver.findElement(By.id("group_1")));
        int sizeIdx = random.nextInt(size.getOptions().size());
        size.selectByIndex(sizeIdx);

        driver.findElement(By.xpath("//p[@id='add_to_cart']/button")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText().equals("Product successfully added to your shopping cart"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='cross']")).click();
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//img[@alt='My Store']")).click();


        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[5]")).click();
        iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
        price = Double.parseDouble(driver.findElement(By.id("our_price_display")).getText().replace("$",""));
        System.out.println("price: " + price);
        count = random.nextInt(4) + 2;
        total+=count*price;
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(count + "");
        size = new Select(driver.findElement(By.id("group_1")));
        sizeIdx = random.nextInt(size.getOptions().size());
        size.selectByIndex(sizeIdx);

        driver.findElement(By.xpath("//p[@id='add_to_cart']/button")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText().equals("Product successfully added to your shopping cart"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='cross']")).click();
        actions.moveToElement(driver.findElement(By.xpath("//div/a/b"))).pause(2).perform();
        Thread.sleep(1000);
        double cartTotal = Double.parseDouble(driver.findElement(By.xpath("(//div[@class='cart-prices']/div/span)[3]")).getText().substring(1));
        System.out.println(cartTotal);
        cartTotal=cartTotal-2.0;
        System.out.println(total);
        Assert.assertTrue(total == cartTotal);
    }
}
