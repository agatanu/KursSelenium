package Kurs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Zadanie12 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void login() {
        driver.get("http://litecart.stqa.ru/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @Test
    public void addNewProduct() {

        login();

        String productName = UUID.randomUUID().toString();

        //General
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(productName);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys(productName);
        driver.findElement(By.xpath("//tr/td/input[@name='product_groups[]' and @value='1-3']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("100");
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("18052019");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("18052020");
        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();

        //Information
        Select manufacturer = new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']")));
        manufacturer.selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("test");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("shortdescr");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("longdescr");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("headtitle");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("metadescr");
        driver.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();

        //Prices
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("59,90");
        Select purchase_price = new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")));
        purchase_price.selectByVisibleText("Euros");
        driver.findElement(By.xpath("//input[@name='prices[EUR]']")).sendKeys("159,90");
        driver.findElement(By.xpath("//input[@name='gross_prices[EUR]']")).sendKeys("179,90");
        driver.findElement(By.xpath("//button[@name='save']")).click();

        //Verification
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(productName));
    }

    @After
    public void after() {
       driver.quit();
     }

}