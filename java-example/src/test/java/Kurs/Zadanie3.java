package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Zadanie3 {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();

    }

    @Test
    public void test(){
        driver.get("http://localhost:5050/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @After
    public void stop(){
        driver.quit();
    }

}
