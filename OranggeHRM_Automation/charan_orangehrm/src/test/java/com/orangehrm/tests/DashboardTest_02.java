package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.pages.Dashboard;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.BaseTest;

public class DashboardTest_02 extends BaseTest{

	private Dashboard dashboardPage;

    @BeforeMethod
    public void loginFirst() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage = new Dashboard(driver);
    }

    @Test(priority = 1)
    public void dashboardDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
                "URL should contain 'dashboard' after login");
    }

    @Test(priority = 2)
    public void getdashboardTitle() {
        String title = dashboardPage.getDashboardTitle();
        Assert.assertEquals(title, "Dashboard", "Dashboard header title should be 'Dashboard'");
    }

    @Test(priority = 3)
    public void pimMenuPresent() {
        Assert.assertTrue(dashboardPage.isMenuItemPresent("PIM"),
                "PIM menu item should be visible in the sidebar");
    }

    @Test(priority = 4)
    public void leaveMenuPresent() {
        Assert.assertTrue(dashboardPage.isMenuItemPresent("Leave"),
                "Leave menu item should be visible in the sidebar");
    }

    @Test(priority = 5)
    public void adminMenuPresent() {
        Assert.assertTrue(dashboardPage.isMenuItemPresent("Admin"),
                "Admin menu item should be visible in the sidebar");
    }

    @Test(priority = 6)
    public void quickLaunchWidgets() {
        int count = dashboardPage.getQuickLaunchWidgetCount();
        Assert.assertTrue(count > 0,
                "At least one quick launch widget should be present on the dashboard");
    }
}
