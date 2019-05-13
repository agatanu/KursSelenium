package Kurs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class Zadanie10 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5050/litecart");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void checkProducts() {

        //Texts
        String productName = driver.findElement(By.cssSelector(".name")).getText();
        String regularPrice = driver.findElement(By.cssSelector(".regular-price")).getText();
        String campaignPrice = driver.findElement(By.cssSelector(".campaign-price")).getText();
        //Styles
        String regularPriceColor = driver.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularPriceFormat = driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        String campaignPriceColor = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignPriceFormat = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");

        //Opens product's details
        driver.findElement(By.xpath("//div[@id='box-campaign-products']//a[@class='link']")).click();

        //Verifies compatibility
        Assert.assertEquals(productName, (driver.findElement(By.cssSelector("h1.title")).getText()));
        Assert.assertEquals(regularPrice, (driver.findElement(By.cssSelector(".regular-price"))).getText());
        Assert.assertEquals(campaignPrice, (driver.findElement(By.cssSelector(".campaign-price"))).getText());
        Assert.assertEquals(regularPriceColor, (driver.findElement(By.cssSelector(".regular-price"))).getCssValue("color"));
        Assert.assertEquals(regularPriceFormat, (driver.findElement(By.cssSelector(".regular-price"))).getCssValue("text-decoration"));
        Assert.assertEquals(campaignPriceColor, (driver.findElement(By.cssSelector(".campaign-price"))).getCssValue("color"));
        Assert.assertEquals(campaignPriceFormat, (driver.findElement(By.cssSelector(".campaign-price"))).getCssValue("font-weight"));
    }

    @After
    public void after() {
        driver.quit();
    }

}