package Kurs;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;

public class Zadanie15 {

    WebDriver driver;

    @Test
    public void virtualBoxTest() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        new ChromeDriver(options);

        driver = new RemoteWebDriver(new URL("http://10.0.2.15:4444/wd/hub"), options);
        driver.get("https://www.google.com");

        driver.quit();

//       On separate terminals in virtual machine:
//        java -jar selenium-server-standalone-3.141.59.jar -role hub
//        java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.56.1:4444/wd/hub
    }
}