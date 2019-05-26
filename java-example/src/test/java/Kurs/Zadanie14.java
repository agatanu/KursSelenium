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

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Zadanie14 {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 2);
    }

    public void login() {
        driver.get("http://litecart.stqa.ru/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @Test
    public void newWindowCheck() {

        login();

        driver.findElement(By.xpath("//td[@id='sidebar']//li[3]//a[1]")).click();
        driver.findElement(By.cssSelector("a.button")).click();
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i[class*=\"external-link\"]"));
        String orginalWindow = driver.getWindowHandle();

        for (int i = 1; i < externalLinks.size(); i++) {
            externalLinks.get(i).click();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> existingWindows = driver.getWindowHandles();
            for (String newWindow : existingWindows) {
                if (!newWindow.equals(orginalWindow)) {
                    driver.switchTo().window(newWindow);
                    String externalPageUrl = driver.getCurrentUrl();
                    System.out.println("External link number " + i + " goes to " + externalPageUrl);
                    driver.close();
                }
            }
            driver.switchTo().window(orginalWindow);
        }
    }

    @After
    public void after() {
        driver.quit();
    }
}