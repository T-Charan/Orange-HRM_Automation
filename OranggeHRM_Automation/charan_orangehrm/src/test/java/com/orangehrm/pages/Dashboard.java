package com.orangehrm.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {

	 private final WebDriver driver;
	 private final WebDriverWait wait;

	    // Locators
	 private final By dashboardHeader = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
	 private final By userDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
	 private final By logoutOption = By.xpath("//a[text()='Logout']");
	 private final By sidebarMenuItems = By.xpath("//a[@class='oxd-main-menu-item']");
	 private final By quickLaunchWidgets = By.cssSelector(".orangehrm-quick-launch-card");

	    public Dashboard(WebDriver driver) {
	        this.driver = driver;
	        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    public String getDashboardTitle() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
	    }

	    public boolean isDashboardDisplayed() {
	        return wait.until(ExpectedConditions.urlContains("/dashboard")).equals(Boolean.TRUE)
	                || driver.getCurrentUrl().contains("dashboard");
	    }

	    public void logout() {
	        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(logoutOption)).click();
	    }

	    public void navigateTo(String menuItemName) {
	        List<WebElement> items = wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(sidebarMenuItems));
	        for (WebElement item : items) {
	            if (item.getText().trim().equalsIgnoreCase(menuItemName)) {
	                item.click();
	                return;
	            }
	        }
	        throw new RuntimeException("Menu item not found: " + menuItemName);
	    }

	    public int getQuickLaunchWidgetCount() {
	        return driver.findElements(quickLaunchWidgets).size();
	    }

	    public boolean isMenuItemPresent(String menuItemName) {
	        List<WebElement> items = driver.findElements(sidebarMenuItems);
	        return items.stream().anyMatch(i -> i.getText().trim().equalsIgnoreCase(menuItemName));
	    }
}
