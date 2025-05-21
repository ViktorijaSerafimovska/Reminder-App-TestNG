package org.example;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.example.base.SetupTest;
import org.example.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class UpdateReminderTest {

    private AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = SetupTest.initializeDriver();
    }

    @Test
    public void addThenUpdateReminderTest() {
        System.out.println("Starting addThenUpdateReminderTest...");
        try {
            ReminderHomePage homePage = new ReminderHomePage(driver);
            AddReminderPage addPage = new AddReminderPage(driver);
            UpdateReminderPage updatePage = new UpdateReminderPage(driver);
            CompletingReminderPage completePage = new CompletingReminderPage(driver);

            System.out.println("Launching Reminder app main activity");
            driver.startActivity(new Activity("com.samsung.android.app.reminder", ".ui.LaunchMainActivity"));

            System.out.println("Clicking on the Add Reminder button");
            homePage.clickAddButton();

            System.out.println("Setting title for the new reminder");
            addPage.setTitle("This has to be edited reminder");

            System.out.println("Setting date for the new reminder");
            addPage.setDate();

            System.out.println("Saving the new reminder");
            addPage.saveReminder();

            Thread.sleep(2000);

            System.out.println("Opening the created reminder");
            homePage.openFirstReminder();

            System.out.println("Clicking on the Edit button");
            updatePage.clickEditButton();

            System.out.println("Updating the title of the reminder");
            updatePage.setTitle("Updated reminder");

            System.out.println("Updating the date of the reminder 30 of May ");
            updatePage.setDate();

            System.out.println("Saving the updated reminder");
            updatePage.saveReminder();

            Thread.sleep(2000);

            System.out.println("Marking the reminder as complete by clicking the checkbox");
            completePage.clickCompleteCheckBox();

            System.out.println("Test passed: Reminder updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Test failed: " + e.getMessage(), false);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing the driver and cleaning up");
            driver.quit();
        }
    }
}