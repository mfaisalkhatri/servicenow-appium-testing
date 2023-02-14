package io.github.mfaisalkhatri.pages;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.drivers.AndroidDriverManager;
import org.openqa.selenium.WebElement;

/**
 * @author Faisal Khatri
 * @since 2/14/2023
 **/
public class MainPage {

    private final AndroidDriverManager androidDriverManager;

    public MainPage (final AndroidDriverManager androidDriverManager) {
        this.androidDriverManager = androidDriverManager;
    }

    private WebElement quickActionBtn () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Quick Actions"));
    }

    public void clickQuickActionsBtn () {
        quickActionBtn ().click ();
    }

    private WebElement openAnIncidentOption () {
        return androidDriverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Open an incident"));
    }

    public NewIncidentPage clickOpenAnIncidentOption () {
        openAnIncidentOption ().click ();
        return new NewIncidentPage (androidDriverManager);
    }

    public void createNewIncident (String shortDesc) {
        clickQuickActionsBtn ();
        clickOpenAnIncidentOption ();
        NewIncidentPage newIncidentPage = new NewIncidentPage (androidDriverManager);
        newIncidentPage.setCallerName ();
        newIncidentPage.enterShortDescription (shortDesc);
        newIncidentPage.clickSubmitBtn ();
    }

}
