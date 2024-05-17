package edu.praktikum.sprint4;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseClass {
    WebDriver driver;

    @Before
    public void createNewChromeDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}