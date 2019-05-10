package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class Zadanie8 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5050/litecart");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void checkAmountOfStickersForEachProduct() {

            List<WebElement> products = driver.findElements(By.cssSelector("div.product"));

            for (int a = 0; a < products.size(); a++) {

                List<WebElement> stickers = products.get(a).findElements(By.cssSelector("div.sticker"));
                int stickersAmount = stickers.size();
                assertEquals(1, stickersAmount);
                System.out.println("For this product " + stickers.size() + " sticker/s is/are displayed");
            }
    }

    @After
    public void after() {
        driver.quit();
    }

}