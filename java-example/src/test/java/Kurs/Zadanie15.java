package Kurs;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

public class Zadanie15 {

    WebDriver driver;

    public void virtualBoxTest() {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),DesiredCapabilities.chrome());
        chrome.get("https://www.google.com");
    }
}
