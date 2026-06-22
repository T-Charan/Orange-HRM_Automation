package com.orangehrm.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private final WebDriver driver;
	private final WebDriverWait wait;
	
	private final By usernamefield = By.name("username");
	private final By passwordfield = By.name("password");
	private final By loginButton = By.xpath("//button[@type='submit']");
	private final By errorMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
	private final By requiredMessage = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
	private final By loginLogo = By.xpath("(//img[@alt='profile picture'])[1]");	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	
	public void enterusername(String username) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(usernamefield));
		field.clear();
		field.sendKeys(username);
	}
	
	public void enterpassword(String Password) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordfield));
		field.clear();
		field.sendKeys(Password);
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public void login(String Username, String Password) {
		enterusername(Username);
		enterpassword(Password);
		clickLoginButton();
	}
	
	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
	}
	
	public boolean getRequiredMessage() {
		return !driver.findElements(requiredMessage).isEmpty();
	}
	
	public boolean loginPageDisplay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginLogo)).isDisplayed();
    }
}
