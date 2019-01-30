package com.prestashop.pages;

import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    static WebDriver driver = Driver.getDriver();

    /*
    listOfItems method
    Takes driver object and url
    returns the list of items futured on website
    */
    public static List listOfItems(){
        List <WebElement> products = driver.findElements(By.xpath("//ul[@id='homefeatured']/li"));
        return products;
    }

    /*
    onSaleItems method
    Takes driver object and url
    returns the indexes in the list of items futured on website that are on sale (should be used along with .listOfItems())
    */
    public static List onSaleItems() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> products = new ArrayList<WebElement>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 1; i <= driver.findElements(By.xpath("//ul[@id='homefeatured']/li")).size(); i++) {
            try {
                products.add(driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li["+(i-1)+"]/div/div[2]/div[1]/span[3]")));
                indexes.add(i);
            } catch (RuntimeException e) {
                continue;
            }
        }
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        return indexes;
    }

    //item method
    //    takes int index of the item and String from a user with info needed
    //    returns the Webelement belonging to the requested info
    //    if doesn't match info needed throws new RuntimeException
    //    ("NoSuchInformationException: Could not find "+ infoNeeded + " in available options");

    public WebElement item(int idx, String infoNeeded){
        WebElement toReturn = null;
        switch (infoNeeded){
            case ("price"):
                toReturn = driver.findElement(By.xpath("(//span[@itemprop='price'][@class='price product-price'])["+((idx+1)*2)+"]"));
                break;
            case ("clickOnItem"):
                toReturn = driver.findElement(By.xpath(" (//img[@class='replace-2x img-responsive'])["+(idx+1)+"]"));
                break;
            case ("name"):
                toReturn = driver.findElement(By.xpath("(//ul[@id='homefeatured']/li/div/div/h5)["+(idx+1)+"]"));
                break;
            default:
                throw new RuntimeException("NoSuchInformationException: Could not find \""+ infoNeeded + "\" OPTION in available options (price, name, clickOnItem)");
        }
        return toReturn;
    }

    //switches to the iframe
    public void switchToProductframe(){
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(iframe);
    }



}
