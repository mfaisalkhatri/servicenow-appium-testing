package io.github.mfaisalkhatri.pages;

import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.drivers.AndroidDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Faisal Khatri
 * @since 2/14/2023
 **/
public class NewIncidentPage {

    private final AndroidDriverManager androidDriverManager;
    private final WebDriverWait        wait;

    public NewIncidentPage (final AndroidDriverManager androidDriverManager) {
        this.androidDriverManager = androidDriverManager;
        this.wait = new WebDriverWait (androidDriverManager.getDriver (), Duration.ofSeconds (30));

    }

    private WebElement callerField () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.id ("com.servicenow.fulfiller:id/valueLayout"));
    }

    private WebElement searchView () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("SearchView"));
    }

    private void selectCallerName () {
        callerField ().click ();
        wait.until (ExpectedConditions.elementToBeClickable (AppiumBy.accessibilityId ("Abraham Lincoln")))
            .click ();

    }

    public void setCallerName () {
        selectCallerName ();
    }

    private WebElement shortDescriptionField () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Short description"));
    }

    public void enterShortDescription (String shortDesc) {
        shortDescriptionField ().sendKeys (shortDesc);
    }

    private WebElement submitBtn () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Submit"));
    }

    public void clickSubmitBtn () {
        submitBtn ().click ();
    }
}
