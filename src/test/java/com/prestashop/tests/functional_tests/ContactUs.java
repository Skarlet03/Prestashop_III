package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUs extends TestBase {

    PersonalInformationPage personalInformationPage;
    WebPageHeader webPageHeader;
    ContactUsPage contactUsPage;

    @BeforeMethod
    public void setupPages() {
        driver.get(url);
        personalInformationPage = new PersonalInformationPage();
        webPageHeader = new WebPageHeader();
        contactUsPage = new ContactUsPage();
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
         webPageHeader.contactUs.click();
         assertEquals(driver.getTitle(),"Contact us - My Store");
         Select select = new Select(contactUsPage.departmentDropdown);
         select.selectByIndex(random.nextInt(select.getOptions().size()));
         personalInformationPage.email.sendKeys(email);
         contactUsPage.order.sendKeys("12365");
         contactUsPage.messageInput.sendKeys("Messege here");
         contactUsPage.submitButton.click();
         assertEquals(contactUsPage.alertMessage.getText(),"Your message has been successfully sent to our team.");

     }
}
