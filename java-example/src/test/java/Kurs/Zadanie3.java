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

import static junit.framework.TestCase.assertTrue;

public class Zadanie3 {

    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    public void login() {
        driver.get("http://localhost:5050/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

    }

    //TIME CONSUMING METHOD

    @Test
    public void checkMenuElements() {

        login();

        driver.findElement(By.cssSelector("#app-appearance")).click();
        assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
        driver.findElement(By.cssSelector("#doc-logotype")).click();
        assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
        // ...
    }

    //OPTIMISED METHOD
    @Test
    public void checkMenuElementsOptimised() {

        List<WebElement> menuElements;
        List<WebElement> submenuElements;

        login();

        menuElements = driver.findElements(By.cssSelector("[id^=app-]"));
        for (int a = 0; a < menuElements.size(); a++) {
            menuElements = driver.findElements(By.cssSelector("[id^=app-]"));
            menuElements.get(a).click();
            menuElements = driver.findElements(By.cssSelector("[id^=app-]"));
            assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());

            if (driver.findElements(By.cssSelector(".docs")).size() > 0) {
                submenuElements = driver.findElements(By.cssSelector("[id^=doc-]"));
                for (int b = 0; b < submenuElements.size(); b++) {
                    submenuElements = driver.findElements(By.cssSelector("[id^=doc-]"));
                    submenuElements.get(b).click();
                    assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());

                }
            }

        }
    }

    @After
    public void stop() {
        driver.quit();
    }

}
