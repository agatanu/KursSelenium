package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Zadanie13 {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://litecart.stqa.ru");
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    @Test
    public void addAndRemoveProducts() {
        //add (without loop)
        int currentQuantityInCart = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());

        driver.findElement(By.xpath("//div[contains(text(),'Red Duck')]")).click();
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(1 + currentQuantityInCart)));
        driver.get("http://litecart.stqa.ru");

        driver.findElement(By.xpath("//div[contains(text(),'Purple Duck')]")).click();
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(2 + currentQuantityInCart)));
        driver.get("http://litecart.stqa.ru");

        driver.findElement(By.xpath("//div[contains(text(),'Green Duck')]")).click();
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(3 + currentQuantityInCart)));
        driver.get("http://litecart.stqa.ru");

        driver.get("http://litecart.stqa.ru/en/checkout");

        //remove (with loop)
        for (int a = 0; a <= 2; a++) {
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            WebElement summaryTable = driver.findElement(By.xpath("//div[@id='box-checkout-summary']"));
            wait.until(ExpectedConditions.stalenessOf(summaryTable));
        }
    }

    @After
    public void after() {
        driver.quit();
    }
}

