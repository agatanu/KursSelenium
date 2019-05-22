package Kurs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Zadanie13 {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5050/litecart");
    }

    @Test
    public void addAndDeleteProducts() {

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 3);

        //add products

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Popular Products')]"))).click();

        WebElement redDuck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Red Duck')]")));
        redDuck.click();
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name='add_cart_product']")));
        addToCartButton.click();
        driver.findElement(By.xpath("//div[@class='featherlight-close-icon featherlight-close']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='featherlight active']")));


        WebElement blueDuck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Blue Duck')]")));
        blueDuck.click();
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        driver.findElement(By.xpath("//div[@class='featherlight-close-icon featherlight-close']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='featherlight active']")));


        WebElement purpleDuck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Purple Duck')]")));
        purpleDuck.click();
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        driver.findElement(By.xpath("//div[@class='featherlight-close-icon featherlight-close']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='featherlight active']")));


        //remove products
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cart']//a")));
        cart.click();

        driver.findElement(By.xpath("//tr[1]//td[6]//button[1]")).click();
        WebElement summaryTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-checkout-summary")));
        driver.findElement(By.xpath("//tr[1]//td[6]//button[1]")).click();
        wait.until(ExpectedConditions.visibilityOf(summaryTable));
        driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
        wait.until(ExpectedConditions.invisibilityOf(summaryTable));

    }


    @After
    public void after() {
        driver.quit();
    }

}