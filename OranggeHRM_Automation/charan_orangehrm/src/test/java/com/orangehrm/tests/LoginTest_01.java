package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.Dashboard;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.BaseTest;

public class LoginTest_01 extends BaseTest{

	@Test(priority = 1)	
	public void ValidLoginPage() {
			
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(USERNAME, PASSWORD);
		
		Dashboard dashboardpage = new Dashboard(driver);
		Assert.assertTrue(dashboardpage.isDashboardDisplayed(),"Dashboard should be displayed after valid login");
		
		}
	@Test(priority = 2)
	public void invalidUsername() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("InvalidUser", PASSWORD);
		
		String error = loginpage.getErrorMessage();
		Assert.assertTrue(error.contains("Invalid credentials"),"Error message should say 'Invalid credentials'");
		
		}
	
	@Test(priority = 3)
	public void invalidPassword() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(USERNAME, "InvalidPassword");
		
		String error = loginpage.getErrorMessage();
		Assert.assertTrue(error.contains("Invalid credentials"),"Error message should say 'Invalid credentials'");
		
		}
	
	@Test(priority = 4)
	public void emptyfiield() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickLoginButton();
		
		Assert.assertTrue(loginpage.getRequiredMessage(),"Required field hints should appear when fields are empty");
		
		}
	
	@Test(priority = 5)
	public void emptyUsername() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterpassword(PASSWORD);
		loginpage.clickLoginButton();
		
		Assert.assertTrue(loginpage.getRequiredMessage(),"Required field hint should appear for empty username");
		}
	
	@Test(priority = 6)
	public void emptyPassword() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterpassword(USERNAME);
		loginpage.clickLoginButton();
		
		Assert.assertTrue(loginpage.getRequiredMessage(),"Required field hint should appear for empty password");
		}
	
	@Test(priority = 7)
	public void loginPageDisplay() {
		
		LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginPageDisplay(),"Login page should be displayed on application load");
	}
	
	@Test(priority = 8)
    public void tc008_logout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        Dashboard dashboardpage = new Dashboard(driver);
        dashboardpage.logout();
        
        Assert.assertTrue(loginPage.loginPageDisplay(),"Login page should be displayed after logout");
    }
}
