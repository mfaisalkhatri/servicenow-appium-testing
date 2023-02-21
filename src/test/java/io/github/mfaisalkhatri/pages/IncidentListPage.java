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
public class IncidentListPage {
    private final AndroidDriverManager androidDriverManager;
    private final WebDriverWait        wait;

    public IncidentListPage (final AndroidDriverManager androidDriverManager) {
        this.androidDriverManager = androidDriverManager;
        this.wait = new WebDriverWait (androidDriverManager.getDriver (), Duration.ofSeconds (30));
    }

    private WebElement seeAllLink () {
        return wait.until (
            ExpectedConditions.visibilityOfElementLocated (AppiumBy.accessibilityId ("My incidents See all")));

    }

    private WebElement filterBtn () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Filter button off"));
    }

    private WebElement shortDesc () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Short description"));
    }

    private WebElement applyBtn () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.id ("com.servicenow.fulfiller:id/btn_apply"));
    }

    public void searchForIncident (String searchDesc) {
        seeAllLink ().click ();
        filterBtn ().click ();
        shortDesc ().click ();
        shortDesc ().sendKeys (searchDesc);
        applyBtn ().click ();
    }

    public void openIncident () {
        androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("2 - High"))
            .click ();
    }

    public String getSeverityText () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("2 - High"))
            .getText ();
    }

    public String getTitle () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("No email! I cant't send or receive anything"))
            .getText ();
    }

    public String getDescription () {
        return androidDriverManager.getDriver ()
            .findElements (AppiumBy.id ("com.servicenow.fulfiller:id/tv_value"))
            .get (0)
            .getText ();
    }

    public String getCallerName () {
        return androidDriverManager.getDriver ()
            .findElements (AppiumBy.id ("com.servicenow.fulfiller:id/tv_value"))
            .get (1)
            .getText ();
    }
}
