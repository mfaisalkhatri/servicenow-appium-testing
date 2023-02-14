package io.github.mfaisalkhatri.drivers;

import static java.text.MessageFormat.format;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Builder;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Faisal Khatri
 * @since 2/14/2023
 **/
@Builder
public class AndroidDriverManager {
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<> ();
    public               Platform                   platform;
    public               String                     platformVersion;
    private              String                     buildName;
    private              String                     testName;

    private static final String LT_USERNAME   = System.getProperty ("LT_USERNAME");
    private static final String LT_ACCESS_KEY = System.getProperty ("LT_ACCESS_KEY");
    private static final String GRID_URL      = "@mobile-hub.lambdatest.com/wd/hub";
    private              String deviceName;
    private              String app;
    //for running tests on local
    //    private static final String GRID_URL = "http://localhost:4723/wd/hub";
    //    private static final String APP_PATH = System.getProperty (
    //        "user.dir") + "\\src\\test\\resources\\com.servicenow.fulfiller_15.4.0-service-now.apk";

    public AndroidDriverManager createAndroidDriver () {
        try {
            DRIVER.set (
                new AndroidDriver (new URL (format ("https://{0}:{1}{2}", LT_USERNAME, LT_ACCESS_KEY, GRID_URL)),
                    capabilities ()));

            // For running the tests on local device
            //            new AndroidDriver (new URL (GRID_URL), capabilities ()));
            setupDriverTimeouts ();

        } catch (MalformedURLException e) {
            throw new Error ("Error setting up android Driver", e);
        }
        return this;
    }

    public AndroidDriver getDriver () {
        return DRIVER.get ();
    }

    public void quitDriver () {
        if (null != getDriver ())
            getDriver ().quit ();
        DRIVER.remove ();
    }

    private void setupDriverTimeouts () {
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
    }

    // For running the tests on local device
    //    private Capabilities capabilities () {
    //        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options ().setDeviceName ("Note 9 Pro Max")
    //            .setAutomationName (AutomationName.ANDROID_UIAUTOMATOR2)
    //            .setApp (APP_PATH)
    //            .setAppPackage ("com.servicenow.fulfiller")
    //            .setAppActivity ("com.servicenow.mobilesky.LaunchActivity")
    //            .setNewCommandTimeout (Duration.ofSeconds (20))
    //            .setNoReset (false);
    //        return uiAutomator2Options;

    //}

    private DesiredCapabilities capabilities () {
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability ("lt:options", ltOptions ());
        return capabilities;
    }

    private HashMap<String, Object> ltOptions () {
        final HashMap<String, Object> ltOptions = new HashMap<> ();
        ltOptions.put (MobileCapabilityType.PLATFORM_NAME, platform);
        ltOptions.put (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        ltOptions.put (MobileCapabilityType.DEVICE_NAME, deviceName);
        ltOptions.put (MobileCapabilityType.APP, app);
        ltOptions.put (AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        ltOptions.put ("username", LT_USERNAME);
        ltOptions.put ("accessKey", LT_ACCESS_KEY);
        ltOptions.put ("build", buildName);
        ltOptions.put ("name", testName);
        ltOptions.put ("w3c", true);
        ltOptions.put ("isRealMobile", true);
        ltOptions.put ("network", true);
        ltOptions.put ("visual", true);
        ltOptions.put ("console", true);
        ltOptions.put ("devicelog", true);
        return ltOptions;
    }

}


