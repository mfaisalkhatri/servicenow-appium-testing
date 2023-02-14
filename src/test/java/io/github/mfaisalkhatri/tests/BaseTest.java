package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.drivers.AndroidDriverManager;
import org.openqa.selenium.Platform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @author Faisal Khatri
 * @since 2/14/2023
 **/
public class BaseTest {
    protected AndroidDriverManager androidDriverManager;

    @Parameters ({ "buildName", "testName", "app", "platformName", "platformVersion", "deviceName" })
    @BeforeClass (alwaysRun = true)
    public void setupTest (String buildName, String testName, String app, Platform platform, String platformVersion,
        String deviceName) {

        androidDriverManager = AndroidDriverManager.builder ()
            .buildName (buildName)
            .testName (testName)
            .app (app)
            .platform (platform)
            .platformVersion (platformVersion)
            .deviceName (deviceName)
            .build ()
            .createAndroidDriver ();

    }

    @AfterClass (alwaysRun = true)
    public void tearDown () {
        androidDriverManager.quitDriver ();
    }

}
