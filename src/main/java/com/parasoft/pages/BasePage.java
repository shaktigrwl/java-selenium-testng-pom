package com.parasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.parasoft.utils.SeleniumActions;
import com.parasoft.utils.TestUtils;
import com.parasoft.utils.WebDriverListener;



public class BasePage {
    protected static WebDriver driver;
    protected static Properties properties;
    protected static SeleniumActions sele_Actions;
    protected static WebDriverListener eventListener;
    protected static EventFiringWebDriver e_driver;
    protected static ChromeOptions chromeOptions;


    /*
     * protected ITestResult result; protected ExtentReports extent; protected
     * ExtentTest extentTest;
     */
    public BasePage() {

        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/com/parasoft/config/config.properties");
            properties.load(ip);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("io exception");

        }

    }

    protected static void initializaton() {
        String browserName = properties.getProperty("browser");
        driver = getDriver(browserName);

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebDriverListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

        driver.get(properties.getProperty("url"));

        sele_Actions = new SeleniumActions();

    }

    private static WebDriver getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", TestUtils.WORKSAPCE_PATH + "//src//drivers//chromedriver.exe");
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            return new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", TestUtils.WORKSAPCE_PATH + "//src//drivers//IEDriverServer.exe");
            return new InternetExplorerDriver();
        }
        return null;
    }

    public void tearDownMain() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
