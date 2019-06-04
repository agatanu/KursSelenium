package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Zadanie17 {

    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(options);

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
        categories = driver.findElements(By.xpath("//tr/td/a[contains(@href,'catalog&category')]"));

        for (int a = 0; a < categories.size(); a++) {
            categories = driver.findElements(By.xpath("//tr/td/a[contains(@href,'catalog&category')]"));
            categories.get(a).click();

            List<WebElement> subcategories;
            subcategories = driver.findElements(By.xpath("//tr/td/a[contains(@href,'catalog&category')]"));
            for (int b = 0; b < subcategories.size(); b++) {
                subcategories = driver.findElements(By.xpath("//tr/td/a[contains(@href,'catalog&category')]"));
                subcategories.get(b).click();

                {
                    List<WebElement> products;
                    products = driver.findElements(By.xpath("//a[contains(@href,'product&category')]"));
                    for (int c = 0; c < products.size(); c++) {
                        products = driver.findElements(By.xpath("//a[contains(@href,'product&category')]"));
                        products.get(c).click();

                        for (LogEntry log : driver.manage().logs().get("performance").getAll()) {
                            System.out.println(log);
                        }
                        driver.navigate().back();
                    }
                }
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}

