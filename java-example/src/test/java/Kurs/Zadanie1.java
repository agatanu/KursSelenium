package Kurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Zadanie1 {

    private WebDriver driver;

    @Before
    public void start() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Agata\\Selenium\\Drivers\\chromedriver.exe");
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

//      Firefox Nightly

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("c:/Program Files/Firefox Nightly/firefox.exe");
        WebDriver driver = new FirefoxDriver(options);

    }

    @Test
    public void test(){
        driver.get("http://facebook.com");
    }

    @After
    public void stop(){
        driver.quit();
    }
}
