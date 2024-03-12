package com.parasoft.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    static java.text.DateFormat dateFormat;
    static Date date;

    public static final long PAGE_LOAD_TIMEOUT = 100;

    public static final long IMPLICIT_WAIT = 10;

    public static final String WORKSAPCE_PATH = "C://Users//shaagraw1//Downloads//Selenium-POM-TestNG-Maven-master//Selenium-POM-TestNG-Maven-master";

    public static final String SCREENSHOT_PATH = WORKSAPCE_PATH + "//Screenshot//";

    public static final String DateFormat = "yyyyMMddHH:mm:ss";

    public static String getDate(String format) {
        dateFormat = new SimpleDateFormat(format);
        date = new Date();
        return dateFormat.format(date);
    }

    public static void takeScreenShot(WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(SCREENSHOT_PATH + "//screenshot//" + getDate(DateFormat) + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
