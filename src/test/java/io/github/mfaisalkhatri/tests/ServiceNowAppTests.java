package io.github.mfaisalkhatri.tests;

import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.pages.IncidentListPage;
import io.github.mfaisalkhatri.pages.LaunchPage;
import io.github.mfaisalkhatri.pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 2/14/2023
 **/
public class ServiceNowAppTests extends BaseTest {

    @BeforeClass
    public void launchApp () {
        LaunchPage launchPage = new LaunchPage (androidDriverManager);
        launchPage.launchDemoApp ();
        //        launchPage.acknowledgeWorkingStatement ();

    }

    @Test
    public void testIncident () {

        MainPage mainPage = new MainPage (androidDriverManager);
        mainPage.createNewIncident ("New incident created using appium automation");
        IncidentListPage incidentListPage = new IncidentListPage (androidDriverManager);

        incidentListPage.searchForIncident ("email");
        incidentListPage.openIncident ();

        assertEquals (incidentListPage.getSeverityText (), "2 - High");
        assertEquals (incidentListPage.getTitle (), "No email! I cant't send or receive anything");
        assertEquals (incidentListPage.getCallerName (), "Francis Soo");
        assertEquals (incidentListPage.getDescription (),
            "I am not able to send or receive any email for the last 24 hours");
    }
}
