package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class Zadanie17 {

    private WebDriver driver;
    private WebDriver wait;
    Boolean productInCategory;

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
    public void checkLogsForProducts() {

        login();

        driver.get("http://litecart.stqa.ru/admin/?app=catalog&doc=catalog");

        List<WebElement> categories;
        categories = driver.findElements(By.cssSelector("a[href*='catalog&category']"));
        for (int a = 0; a < categories.size(); a++) {
            categories.get(a).click();

            List<WebElement> subcategories;
            subcategories = driver.findElements(By.xpath("//td/a[contains(@href,'catalog&category')]"));
            for (int b = 0; b < subcategories.size(); b++) {
                subcategories.get(b).click();

                productInCategory = driver.findElement(By.xpath("//em[contains(text(),'Empty')]")).isDisplayed();
                if (productInCategory == false) {
                    List<WebElement> products;
                    products = driver.findElements(By.xpath("//a[contains(@href,'product&category')]"));
                    for (int c = 0; c < products.size(); c++) {
                        products.get(c).click();

                        for (LogEntry log : driver.manage().logs().get("browser").getAll()) System.out.println(log);
                    }
                } else {
                    System.out.println("No product!");
                }
            }

        }

    }
}

//    @After
//    public void stop() {
//        driver.quit();
//    }

