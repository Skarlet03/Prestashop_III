package com.prestashop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected Actions actions;
    protected SoftAssert softAssert;
    protected Random random;


    @BeforeMethod
    public void setUpMethod() {

        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        random= new Random();
    }
    public  String url = ConfigurationReader.getProperty("url");

    @AfterClass(alwaysRun = true)
    public void quitWindows() {
        BrowserUtils.wait(3);
        Driver.closeDriver();
        actions = null;
        random=null;
    }


}