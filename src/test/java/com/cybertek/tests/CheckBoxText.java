package com.cybertek.tests;

import com.cybertek.tests.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxText {
    /*
Test Case Verify CheckBox CheckAll and UncheckAll Buttons
1.	Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
2.	Login with-----Username: Tester, password: test
3.	Click on check all button verify all the checkboxes are checked
4.	Click on uncheck all button verify that all the checkboxes are unchecked
5.	Select one of the checkbox and delete one person
6.	Then verify that deleted item is no longer exists
     */

    @Test
    public void Test1() throws InterruptedException {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();

//      1.	Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
        driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

//        2.	Login with-----Username: Tester, password: test
        WebElement inputUsername = driver.findElement(By.id("ctl00_MainContent_username"));
        inputUsername.sendKeys("Tester");
        WebElement inputPassword = driver.findElement(By.id("ctl00_MainContent_password"));
        inputPassword.sendKeys("test");
        Thread.sleep(2000);
        WebElement buttonLogin = driver.findElement(By.id("ctl00_MainContent_login_button"));
        buttonLogin.click();

//        3.	Click on check all button verify all the checkboxes are checked
        WebElement buttonCheckAll = driver.findElement(By.id("ctl00_MainContent_btnCheckAll"));
        buttonCheckAll.click();
        //ctl00_MainContent_orderGrid_ctl02_OrderSelector
        //...
        //ctl00_MainContent_orderGrid_ctl09_OrderSelector
        for (int i = 2; i < 10; i++) {
            String id = "ctl00_MainContent_orderGrid_ctl0" + i + "_OrderSelector";
            WebElement checkbox = driver.findElement(By.id(id));
            Assert.assertTrue(checkbox.isSelected());
        }
        Thread.sleep(2000);

//        4.	Click on uncheck all button verify that all the checkboxes are unchecked
        WebElement buttonUncheckAll = driver.findElement(By.id("ctl00_MainContent_btnUncheckAll"));
        buttonUncheckAll.click();
        for (int i = 2; i < 10; i++) {
            String id = "ctl00_MainContent_orderGrid_ctl0" + i + "_OrderSelector";
            WebElement checkbox = driver.findElement(By.id(id));
            Assert.assertFalse(checkbox.isSelected());
        }
        Thread.sleep(2000);

//        5.	Select one of the checkbox and delete one person
        WebElement firstPerson = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[2]"));
        String firstPersonName = firstPerson.getText();
        WebElement checkboxFirst = driver.findElement(By.id("ctl00_MainContent_orderGrid_ctl02_OrderSelector"));
        checkboxFirst.click();
        WebElement buttonDelete = driver.findElement(By.id("ctl00_MainContent_btnDelete"));
        buttonDelete.click();
        Thread.sleep(2000);

//        6.	Then verify that deleted item is no longer exists
        WebElement firstPersonAfterDelete = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[2]"));
        String firstPersonNameAfterDelete = firstPersonAfterDelete.getText();

        System.out.println(firstPersonName);
        System.out.println(firstPersonNameAfterDelete);
        Assert.assertNotEquals(firstPersonName, firstPersonNameAfterDelete);

        Thread.sleep(2000);
        driver.quit();
    }

}