package com.parasoft.testcases;

import com.parasoft.pages.BasePage;
import com.parasoft.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HomePageTestCases extends BasePage {

    HomePage homePage;

    public HomePageTestCases() {
        super();
    }

    /*
     * @BeforeTest public void setExtentReport() { setExtend(); }
     */

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        initializaton();
        homePage = new HomePage();
    }

    @Test(groups = { "Simple", "UI" })
    public void TC001_CheckifLogoisPresent() {
        boolean flag = homePage.isLogoDisplayed();
        Assert.assertTrue(flag);
    }

    @Test(groups = { "Simple", "UI" })
    public void TC002_ValidatingTheHeading() {
        Assert.assertEquals(homePage.getPageTitle(),properties.getProperty("PageTitle"));

    }

    @Test(groups = { "Functionality" })
    public void TC003_LoggingToBank() {
        homePage.loginToAccount(properties.getProperty("validUsername"),properties.getProperty("validPassword"));
    }




    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        tearDownMain();
    }
}
