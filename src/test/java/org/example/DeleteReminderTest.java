package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.example.base.SetupTest;
import org.example.pages.AddReminderPage;
import org.example.pages.CompletingReminderPage;
import org.example.pages.DeleteReminderPage;
import org.example.pages.ReminderHomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class DeleteReminderTest {

    private AndroidDriver driver;
    private ReminderHomePage reminderHomePage;
    private AddReminderPage addReminderPage;
    private CompletingReminderPage completePage;
    private DeleteReminderPage deletePage;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = SetupTest.initializeDriver();
        reminderHomePage = new ReminderHomePage(driver);
        addReminderPage = new AddReminderPage(driver);
        completePage = new CompletingReminderPage(driver);
        deletePage = new DeleteReminderPage(driver);
    }

    @Test
    public void addThenDeleteReminderTest() throws InterruptedException {
        try {
            System.out.println("Starting addThenDeleteReminderTest...");
            Thread.sleep(2000);

            System.out.println("Opening the reminder app");
            reminderHomePage.openReminderApp();

            String uniqueTitle = "Deleting reminder Test";

            System.out.println("Clicking on add reminder button");
            reminderHomePage.clickAddButton();

            System.out.println("Setting tittle for the reminder");
            addReminderPage.setTitle(uniqueTitle);

            System.out.println("Setting date for the reminder(current date)");
            addReminderPage.setDate();

            System.out.println("Saving the reminder ");
            addReminderPage.saveReminder();
            Thread.sleep(2000);

            assertTrue("Reminder not found after creation!",
                    completePage.isReminderWithTitleDisplayed(uniqueTitle));

            System.out.println("Clicking on created reminder");
            addReminderPage.clickReminderByTitle(uniqueTitle);

            System.out.println("Clicking on the delete button");
            deletePage.deleteReminderViaBottomButton();

            System.out.println("Verifying the delete");
            deletePage.verifyReminderIsDeleted(uniqueTitle);

            System.out.println("Check if there is no reminders");
            deletePage.verifyNoRemindersVisible();
            Thread.sleep(2000);

            System.out.println("Test passed: Reminder was successfully created and deleted.");

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
