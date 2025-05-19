package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.example.base.SetupTest;
import org.example.pages.AddReminderPage;
import org.example.pages.CompletingReminderPage;
import org.example.pages.ReminderHomePage;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.assertTrue;


public class AddReminderTest {

    private AndroidDriver driver;
    private ReminderHomePage homePage;
    private AddReminderPage addPage;
    private CompletingReminderPage completePage;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = SetupTest.initializeDriver();
        homePage = new ReminderHomePage(driver);
        addPage = new AddReminderPage(driver);
        completePage = new CompletingReminderPage(driver);
    }

    @Test
    public void addReminderTest() {
        try {
            String reminderTitle = "Create new reminder";

            System.out.println("Opening the reminder app");
            homePage.openReminderApp();

            System.out.println("Clicking on add button");
            homePage.clickAddButton();

            System.out.println("Setting tittle for the reminder");
            addPage.setTitle(reminderTitle);

            System.out.println("Setting date fot the reminder 30 of may");
            addPage.setDate();

            System.out.println("Saving the reminder");
            addPage.saveReminder();

            System.out.println("Marking as complete");
            completePage.clickFirstCompleteCheckBox();

            System.out.println("Test passed: Reminder was successfully created.");

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

