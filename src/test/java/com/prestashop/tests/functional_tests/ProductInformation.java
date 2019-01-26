package com.prestashop.tests.functional_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductInformation {
    WebDriver driver;

    @BeforeMethod
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
    }

    /*
    Product information - price:
        1. Go	to	http://automationpractice.com/index.php
        2. Click	on	any	product
        3. Verify	that	same	name	and	price	displayed	as	on	the	home	page
     */
    @Test
    public void price(){
        String nameHome = driver.findElement(By.xpath("(//h5/a[@title='Blouse'])[1]")).getText();
        String priceHome = driver.findElement(By.xpath("(//span[@itemprop='price'][@class='price product-price'])[4]")).getText();
        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[2]")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
        String priceSelected = driver.findElement(By.id("our_price_display")).getText();
        String nameSelected = driver.findElement(By.xpath("//h1[@itemprop]")).getText();
        Assert.assertTrue(nameHome.equalsIgnoreCase(nameSelected) &&  priceHome.equalsIgnoreCase(priceSelected));
    }

    /*
    Product information - details:
        4. that	default	quantity	is	1
        5. Verify	that	default size	is	S
        6. Verify	that	size	options	are	S,	M,	L
     */
    @Test
    public void details() throws InterruptedException {
        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[2]")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
        WebElement qty = driver.findElement(By.xpath("//input[@id='quantity_wanted']"));
        String quantity = qty.getAttribute("value");
        Select select = new Select(driver.findElement(By.id("group_1")));
        String size = select.getFirstSelectedOption().getAttribute("title");
        List<WebElement> options = select.getOptions();
        String opt = "";
        for (WebElement each :
                options) {
            opt += each.getAttribute("title");
        }
        Assert.assertTrue(opt.equals("SML") && quantity.equals("1")&& size.equals("S"));
    }

    /*
    Product information – Add to cart:
        7. Click	on	Add	to	cart
        8. Verify	confirmation	message	“Product	successfully	added	to	your	shopping
        cart”
        9. that	default	quantity	is 1
        www.cybertekschool.com training@cybertekschool.com
        2
        10. Verify	that	default size	is	S
        11. Verify	that	same	name	and	price	displayed	as	on	the	home	page
     */

    @Test
    public void addToCart(){
        String nameHome = driver.findElement(By.xpath("(//a[@class='product-name'])[2]")).getText();
        String priceHome = driver.findElement(By.xpath("(//span[@itemprop='price'][@class='price product-price'])[4]")).getText();
        driver.findElement(By.xpath("(//img[@class='replace-2x img-responsive'])[2]")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//p[@id='add_to_cart']/button")).click();
        //System.out.println(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText().equals("Product successfully added to your shopping cart"));
        Assert.assertTrue(driver.findElement(By.id("layer_cart_product_quantity")).getText().equals("1"));
        Assert.assertTrue(driver.findElement(By.id("layer_cart_product_attributes")).getText().contains("S"));
        Assert.assertTrue(priceHome.equalsIgnoreCase(driver.findElement(By.id("layer_cart_product_price")).getText()));
        Assert.assertTrue(nameHome.equalsIgnoreCase(driver.findElement(By.id("layer_cart_product_title")).getText()));
    }

}
