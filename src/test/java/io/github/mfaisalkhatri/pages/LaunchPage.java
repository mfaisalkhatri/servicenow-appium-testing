package io.github.mfaisalkhatri.pages;

import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.drivers.AndroidDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Faisal Khatri
 * @since 2/14/2023
 **/
public class LaunchPage {

    private final AndroidDriverManager androidDriverManager;
    private final WebDriverWait        wait;

    public LaunchPage (final AndroidDriverManager androidDriverManager) {
        this.androidDriverManager = androidDriverManager;
        wait = new WebDriverWait (androidDriverManager.getDriver (), Duration.ofSeconds (30));
    }

    private WebElement tryDemoLink () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.id ("com.servicenow.fulfiller:id/btn_demo_account"));
    }

    private void clickOnTryDemoLink () {
        tryDemoLink ().click ();
    }

    private WebElement serviceDeskOptionMenu () {
        return wait.until (ExpectedConditions.elementToBeClickable (By.id ("itsm")));
    }

    private void clickServiceDeskAgentOption () {
        switchToWebViewContext ();
        serviceDeskOptionMenu ().click ();
    }

    private WebElement launchDemoBtn () {
        return androidDriverManager.getDriver ()
            .findElement (By.id ("launchDemo"));
    }

    private void clickLaunchDemoBtn () {
        launchDemoBtn ().click ();
        switchToNativeAPP ();
    }

    private void switchToWebViewContext () {
        wait.until (d -> androidDriverManager.getDriver ()
            .getContextHandles ()
            .size () > 1);
        androidDriverManager.getDriver ()
            .context ("WEBVIEW_chrome");

    }

    private void switchToNativeAPP () {
        androidDriverManager.getDriver ()
            .context ("NATIVE_APP");
    }

    public String getWeAreWorkingStatement () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.id ("com.servicenow.fulfiller:id/appsee_we_are_working"))
            .getText ();
    }

    private WebElement acknowledgeBtn () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.id ("com.servicenow.fulfiller:id/positive_btn"));
    }

    public void clickAcknowledgeBtn () {
        acknowledgeBtn ().click ();

    }

    public void launchDemoApp () {
        clickOnTryDemoLink ();
        clickServiceDeskAgentOption ();
        clickLaunchDemoBtn ();

    }

    public MainPage acknowledgeWorkingStatement () {
        clickAcknowledgeBtn ();
        return new MainPage (androidDriverManager);
    }
}
