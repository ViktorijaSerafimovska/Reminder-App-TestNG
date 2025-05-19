package org.example;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class AddReminderTest {

    private AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = SetupTest.initializeDriver();
    }

    @Test
    public void addReminderTest() {
        System.out.println("Starting addReminderTest...");
        try {
            System.out.println("Opening the Reminder app");
            driver.startActivity(new Activity("com.samsung.android.app.reminder", ".ui.LaunchMainActivity"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("Clicking on add button");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/floating_action_button")));
            addButton.click();

            System.out.println("Adding title for the reminder");
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("com.samsung.android.app.reminder:id/add_viewholder_text_view")));
            titleInput.sendKeys("Create new reminder");

            System.out.println("Setting date for the reminder");
            WebElement addTime = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.LinearLayout[@content-desc='Add time']")));
            addTime.click();

            System.out.println("Clicking on date field");
            WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/condition_date_text")));
            dateField.click();

            System.out.println("Selecting date: Thursday, May 29, 2025");
            WebElement specificDate = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.View[@content-desc='Thursday, May 29, 2025']")));
            specificDate.click();

            System.out.println("Clicking on time field");
            WebElement timeField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/condition_time_text")));
            timeField.click();

            System.out.println("Saving the reminder ");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/action_save_reminder")));
            saveButton.click();

            System.out.println("Clicking on the check box of the created reminder");
            WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(
                    MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.samsung.android.app.reminder:id/reminder_color_complete_view\").instance(0)")));
            checkBox.click();
            Thread.sleep(2000);

            System.out.println("Test passed: Reminder was added successfully.");
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
