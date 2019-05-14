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
import static org.junit.Assert.assertNotEquals;
import static sun.security.jgss.GSSUtil.login;

public class Zadanie9 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void login() {
        driver.get("http://litecart.stqa.ru/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @Test
    public void checkAlphabeticalOrder() {

        login();

        driver.findElement(By.xpath("//td[@id='sidebar']//li[3]//a[1]")).click();
        List<WebElement> countries = driver.findElements(By.xpath("//tr/td[5]/a[contains(@href,'countries')]"));

        List<String> listOfCountries = new ArrayList<String>();

        for (int i = 0; i < countries.size(); i++) {
            listOfCountries.add(countries.get(i).getText());
        }


        List<String> unsortedListOfCountries = new ArrayList<String>(listOfCountries);
        Collections.sort(listOfCountries);

        if (listOfCountries.equals(unsortedListOfCountries)) {
            System.out.println("Alphabetical order is correct! PASSED!");
        } else {
            System.out.println("Alphabetical order is NOT correct! FAILED!");
        }
        //Uncomment the row below to see differences
        //assertEquals(listOfCountries,sortedListOfCountries);
    }

    @Test
    public void checkSortingForZonesInCountries() {

        login();

        driver.findElement(By.xpath("//td[@id='sidebar']//li[3]//a[1]")).click();
        int numberOfCountries = driver.findElements(By.cssSelector("tr.row")).size();

        for (int i = 0; i < numberOfCountries; i++) {
            List<WebElement> countries = driver.findElements(By.xpath("//tr/td[5]/a[contains(@href,'countries')]"));
            int zonesCount = Integer.parseInt(driver.findElement(By.xpath("//tr/td[6]")).getText());

            if (zonesCount > 0) {
                countries.get(i).click();
                //System.out.println(driver.getTitle());
                int zonesSize = driver.findElements(By.xpath("//tr/td[3]")).size();
                List<String> zonesList = new ArrayList<String>();

                for (int j = 0; j < zonesSize; j++) {

                    List<WebElement> zones = driver.findElements(By.xpath("//tr/td[3]"));
                    zonesList.add(zones.get(j).getAttribute("value"));
                }

                driver.navigate().back();

                List<String> zonesListBeforeSorting = new ArrayList<String>(zonesList);
                Collections.sort(zonesList);
                assertEquals(zonesList, zonesListBeforeSorting);
            }

        }

    }

    @Test
    public void checkSortingForZonesInCountriesFromGeoZones() {

        login();

        driver.findElement(By.xpath("//td[@id='sidebar']//li[6]//a[1]")).click();
        int numberOfCountries = driver.findElements(By.cssSelector("tr.row")).size();

        for (int i = 0; i < numberOfCountries; i++) {
            List<WebElement> countries = driver.findElements(By.xpath("//tr/td[3]/a[contains(@href,'geo_zones')]"));
            int zonesCount = Integer.parseInt(driver.findElement((By.xpath("//tr/td[4]"))).getText());

            if (zonesCount > 0) {
                countries.get(i).getText();
                countries.get(i).click();
                List<WebElement> geoZones = driver.findElements(By.xpath("//tr/td[3]"));
                List<String> geoZonesList = new ArrayList<String>();

                for (int j = 0; j < geoZones.size(); j++) {

                    String geoZoneName = geoZones.get(j).findElement(By.xpath("//select[contains(@name,'zones')]")).getAttribute("textContent");
                    geoZonesList.add(geoZoneName);
                }

                driver.navigate().back();

                List<String> unsortedGeoZonesList = new ArrayList<String>(geoZonesList);
                Collections.sort(geoZonesList);
                assertEquals(geoZonesList, unsortedGeoZonesList);
            }
        }

    }

    @After
    public void after() {
        driver.quit();
    }

}