package com.blz.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {

    public static WebDriver driver;

    @BeforeTest
    @Feature("BrowserName and url for starting test")
    @Description("Used setUp method for setting the browser")
    @Severity(SeverityLevel.CRITICAL)
    public void setUp() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://news.ycombinator.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @AfterTest
    @Feature("Closing application")
    @Description("Used tearDown method for closing the browser.")
    @Severity(SeverityLevel.CRITICAL)
    public void tearDown() {
        driver.quit();
    }
}
