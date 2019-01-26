package com.prestashop.tests.functional_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Search {
    WebDriver driver;

    @BeforeMethod
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
    }

    @Test
    public void search(){
        String searchTerm = "blouse";
        driver.findElement(By.id("search_query_top")).sendKeys(searchTerm + Keys.ENTER);
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Search - My Store"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='lighter']")).getText().toLowerCase().contains(searchTerm));
    }
}
