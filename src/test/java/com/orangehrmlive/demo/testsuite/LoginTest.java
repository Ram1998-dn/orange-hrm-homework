package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.excelutility.ExcelUtility;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(CustomListeners.class)
public class LoginTest extends BaseTest {



    LoginPage loginPage;
    HomePage homePage;
    AdminPage adminPage;
    ViewSystemUsersPage viewSystemUsersPage;
    AddUserPage addUserPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        loginPage = new LoginPage();
        adminPage = new AdminPage();
        homePage = new HomePage();
        viewSystemUsersPage = new ViewSystemUsersPage();
        addUserPage = new AddUserPage();

    }

    @Test(groups = {"smoke", "sanity", "regression"})
    public void verifyUserShouldLoginSuccessFully() {

        //Login to Application
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        //verify welcome message
        Assert.assertTrue(homePage.isLogoDisplayed(), "Dashboard message not displayed");

    }

    @Test(groups = {"sanity", "regression"})
    public void verifyThatTheLogoDisplayOnHomePage() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.verifyUserProfileIsDisplayed(),"User Profile image is displayed");


    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldLogOutSuccessFully() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();


        homePage.selectLogOutOption();

        Assert.assertTrue(loginPage.isLoginPanelDisplay(), "Login Panel text is not displayed after logout.");
    }

    @Test(groups = {"smoke", "sanity", "regression"})
    public void verifyErrorMessageWithInvalidCredentials() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        String expectedText = "Invalid credentials";
        String actualText = loginPage.getErrorMessage();
        Assert.assertEquals(actualText, expectedText, "Invalid Login");
    }


}


