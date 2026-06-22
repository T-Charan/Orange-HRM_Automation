package com.orangehrm.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public static final String BASE_URL  = "https://opensource-demo.orangehrmlive.com";
    public static final String USERNAME  = "Admin";
    public static final String PASSWORD  = "admin123";
    public static final int    TIMEOUT   = 15;
    public static final int    IMPLICIT  = 10;
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    
    @BeforeMethod
    public void setup() {
        
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT));
    		
    }
    
    
    @AfterMethod
    public void Close() {
    	if(driver!= null) {
    		driver.quit();
    	}
    }
}
