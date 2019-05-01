package Kurs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie01 {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Agata\\Selenium\\Drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://facebook.com");
        driver.quit();

    }
}
