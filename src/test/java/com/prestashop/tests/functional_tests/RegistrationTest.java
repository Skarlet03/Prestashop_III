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

public class RegistrationTest extends TestBase {

    @BeforeMethod
    void setup(){
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
    }

    /*
    Registration Test
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


    @Test
    public void register() throws InterruptedException{
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys(email + Keys.ENTER);
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='page-heading']")).getText().equalsIgnoreCase("Create an account"));
        Assert.assertTrue(driver.findElement(By.id("email")).getAttribute("value").equalsIgnoreCase(email));
        driver.findElement(By.id("id_gender"+(random.nextInt(2)+1)+"")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(fName);
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
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='info-account']")).getText().equals("Welcome to your account. Here you can manage all of your personal information and orders."));
        Assert.assertTrue(driver.findElement(By.linkText(fName + " " + lName)).getText().equalsIgnoreCase(fName + " " + lName));
        driver.findElement(By.xpath("//a[@title='Information']")).click();
        Assert.assertTrue(driver.findElement(By.id("firstname")).getAttribute("value").equalsIgnoreCase(fName));
        Assert.assertTrue(driver.findElement(By.id("lastname")).getAttribute("value").equalsIgnoreCase(lName));
        Assert.assertTrue(driver.findElement(By.id("email")).getAttribute("value").equalsIgnoreCase(email));
        s1 = new Select(driver.findElement(By.id("days")));
        Assert.assertTrue(s1.getFirstSelectedOption().getText().equals(s1.getOptions().get(day).getText()));
        s2 = new Select(driver.findElement(By.id("months")));
        Assert.assertTrue(s2.getFirstSelectedOption().getText().equals(s2.getOptions().get(month).getText()));
        s3 = new Select(driver.findElement(By.id("years")));
        Assert.assertTrue(s3.getFirstSelectedOption().getText().equals(s3.getOptions().get(year).getText()));
        driver.findElement(By.linkText("Back to your account")).click();
        driver.findElement(By.linkText("My addresses")).click();
        Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='address_name'])[1]")).getText().equalsIgnoreCase(fName));
        Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='address_name'])[2]")).getText().equalsIgnoreCase(lName));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='address_address1']")).getText().equalsIgnoreCase(street));
        //System.out.println(driver.findElement(By.xpath("(//li/span)[6]")).getText()+ "---->"+ city);
        Assert.assertTrue(driver.findElement(By.xpath("(//li/span)[6]")).getText().equalsIgnoreCase(city+","));
        Assert.assertTrue(driver.findElement(By.xpath("(//li/span)[8]")).getText().equalsIgnoreCase(zip));
        System.out.println(driver.findElement(By.xpath("(//li/span)[11]")).getText()+ "---->"+cellN);
        Assert.assertTrue(driver.findElement(By.xpath("(//li/span)[11]")).getText().equalsIgnoreCase(cellN));
        driver.findElement(By.xpath("//a[@class='logout']")).click();


        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(passwd+Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.linkText(fName + " " + lName)).getText().equalsIgnoreCase(fName + " " + lName));
    }

}
