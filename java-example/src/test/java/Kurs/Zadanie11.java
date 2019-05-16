package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class Zadanie11 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://litecart.stqa.ru/en/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void registrationAndLogin() {

        String randomString = UUID.randomUUID().toString();

        driver.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();

        //Registration Form
        Select country = new Select(driver.findElement(By.xpath("//select[@name='country_code']")));
        country.selectByVisibleText("Poland");

        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Agata");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Choinkowa");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("11-111");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Orzesze");
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("555444333");
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("agatatest"+randomString+"@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test123");
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@name='create_account']")).click();

        //Logout
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        //Login
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("agatatest"+randomString+"@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        //Logout
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    @After
    public void after() {
        driver.quit();
    }
}
