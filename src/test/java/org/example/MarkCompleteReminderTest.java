package org.example;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.example.base.SetupTest;
import org.example.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class MarkCompleteReminderTest {

    private AndroidDriver driver;
    private ReminderHomePage reminderHomePage;
    private AddReminderPage addReminderPage;
    private CompletingReminderPage completePage;
    private DeleteReminderPage deletePage;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = SetupTest.initializeDriver();
        driver.startActivity(new Activity("com.samsung.android.app.reminder", ".ui.LaunchMainActivity"));
        reminderHomePage = new ReminderHomePage(driver);
        addReminderPage = new AddReminderPage(driver);
        completePage = new CompletingReminderPage(driver);
        deletePage = new DeleteReminderPage(driver);
    }

    @Test
    public void addThenMarkReminderAsCompleteTest() {
        try {
            System.out.println("Starting the addThenMarkReminderAsCompleteTest");
            System.out.println("Clicking on the add button");
            reminderHomePage.clickAddButton();

            System.out.println("Setting title for the reminder");
            String uniqueTitle = "Mark this reminder as complete test ";
            addReminderPage.setTitle(uniqueTitle);

            System.out.println("Setting date for the reminder");
            addReminderPage.setDate();

            System.out.println("Saving the reminder");
            addReminderPage.saveReminder();

            Thread.sleep(2000);

            System.out.println("Reminder was created, verifying...");
            assertTrue("Reminder is not displayed!", completePage.isReminderWithTitleDisplayed(uniqueTitle));


            System.out.println("Marking the reminder as complete");
            completePage.clickFirstCompleteCheckBox();

            System.out.println("Checking if there are any visible reminders");
            deletePage.verifyNoRemindersVisible();

            Thread.sleep(2000);
            System.out.println("Test passed: Reminder marked as complete.");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Test failed: " + e.getMessage(), false);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
