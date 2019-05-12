package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static sun.security.jgss.GSSUtil.login;

public class Zadanie9 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void login() {
        driver.get("http://localhost:5050/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @Test
    public void checkAlphabeticalOrder(){

        login();

        driver.findElement(By.cssSelector("#app-countries")).click();
        List<WebElement> countries = driver.findElements(By.xpath("//tr/td[5]/a[contains(@href,'countries')]"));

        List<String> listOfCountries = new ArrayList<String>();

        for (int i = 0; i < countries.size(); i++) {
            listOfCountries.add(countries.get(i).getText());
        }

        List<String> sortedListOfCountries = new ArrayList<String>(listOfCountries);
        Collections.sort(listOfCountries);

        if(listOfCountries.equals(sortedListOfCountries)){
            System.out.println("Alphabetical order is correct! PASSED!");
        }
        else {
            System.out.println("Alphabetical order is NOT correct! FAILED!");
        }
        //Uncomment the row below to see differences
        //assertEquals(listOfCountries,sortedListOfCountries);
    }

    @After
    public void after() {
        driver.quit();
    }

}