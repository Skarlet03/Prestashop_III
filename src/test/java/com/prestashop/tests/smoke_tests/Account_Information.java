package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Account_Information {

    WebDriver driver;

    @BeforeMethod
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
    }

    //login to the system method

    public void signIn(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("Myroslavapavliuk@yahoo.com");
        driver.findElement(By.id("passwd")).sendKeys("123456" + Keys.ENTER);
    }

    /*
    Login:	my	account
        1. Go	to	http://automationpractice.com/index.php
        2. Click	Sign in link
        3. Login	using	valid	username	and	password
        4. Verify	that	title contains My	account
        5. Verify	that	account	holder	full	name	is	displayed	next	to	the	Sign	out	link
     */
    @Test
    public void testMyAccount(){
        signIn();
        Assert.assertTrue(driver.getTitle().contains("My account"));
        Assert.assertTrue(driver.findElement(By.xpath("(//a[@rel='nofollow']/span)[1]")).isDisplayed());
    }

    /*
    Login:	My	personal	information
            6. Click	on	My	personal	information button
            7. Verify	title	contains	Identity
            8. Verify	that first	name	and	last	name	matches	the	full	name	on	top
            9. Click	on	Save	button
            10. Verify	error	message	“The	password	you	entered	is	incorrect.”
            11. Click	on Back	to	your	account
            12. Verify	that	title contains My	account
     */

    @Test
    public void personalInfo() {
        signIn();
        driver.findElement(By.xpath( "//span[.='My personal information']")).click();
        Assert.assertTrue(driver.getTitle().contains("Identity"));
        String nameOnTop = driver.findElement(By.xpath("(//a[@rel='nofollow']/span)[1]")).getText();
        String inputBoxName = driver.findElement(By.id("firstname")).getAttribute("value") + " " + driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertTrue(nameOnTop.equalsIgnoreCase(inputBoxName));
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ol/li")).getText().equalsIgnoreCase("The password you entered is incorrect."));
        driver.findElement(By.linkText("Back to your account")).click();
        Assert.assertTrue(driver.getTitle().contains("My account"));
    }

    /*
    Login:	My	addresses
        13. Click on	My	addresses
        14. Click on	Add a	new	address
        15. Verify	that first	name	and	last	name	matches	the	full	name	on	top
        16. Delete	the	first	name
        17. Click	save
        18. Verify	error	message	“firstname	is	required.”

     */

    @Test
    public void addressInfo(){
        signIn();
        driver.findElement(By.linkText("My addresses")).click();
        driver.findElement(By.linkText("Add a new address")).click();
        String nameOnTop = driver.findElement(By.xpath("(//a[@rel='nofollow']/span)[1]")).getText();
        String inputBoxName = driver.findElement(By.id("firstname")).getAttribute("value") +" "+ driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertTrue(nameOnTop.equalsIgnoreCase(inputBoxName));
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("submitAddress")).click();
        Assert.assertTrue(driver.findElement(By.xpath("(//ol/li)[1]")).getText().equalsIgnoreCase("firstname is required."));
    }
}
