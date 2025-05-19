package org.example;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class DeleteReminderTest {

    private AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = SetupTest.initializeDriver();
    }

    @Test
    public void addThenDeleteReminderTest() {
        System.out.println("Starting addThenDeleteReminderTest...");
        try {
            System.out.println("Opening the Reminder app");
            driver.startActivity(new Activity("com.samsung.android.app.reminder", ".ui.LaunchMainActivity"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("Clicking on the add button");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/floating_action_button")));
            addButton.click();

            System.out.println("Setting tittle for the reminder");
            String uniqueTitle = "Deleting reminder Test " ;
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("com.samsung.android.app.reminder:id/add_viewholder_text_view")));
            titleInput.sendKeys(uniqueTitle);

            System.out.println("Clicking on date field ");
            WebElement addTime = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.LinearLayout[@content-desc='Add time']")));
            addTime.click();

            System.out.println("Setting date for the reminder");
            WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/condition_date_text")));
            dateField.click();

            System.out.println("Selecting date: Thursday, May 29, 2025");
            WebElement specificDate = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.View[@content-desc='Thursday, May 29, 2025']")));
            specificDate.click();

            System.out.println("Setting on time ");
            WebElement timeField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/condition_time_text")));
            timeField.click();

            System.out.println("Clicking on saving button");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/action_save_reminder")));
            saveButton.click();

            System.out.println("Reminder was created under title:  " + uniqueTitle);
            Thread.sleep(2000);

            System.out.println("Clicking on the created reminder");
            WebElement createdReminder = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text='" + uniqueTitle + "']")));
            createdReminder.click();

            System.out.println("Deleting the created reminder: " + uniqueTitle);
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.samsung.android.app.reminder:id/detail_bottom_delete_btn")));
            deleteButton.click();

            System.out.println("Confirmation about the deleting");
            WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("android:id/button1")));
            confirmDelete.click();

            Thread.sleep(2000);


            List<WebElement> deletedReminder = driver.findElements(
                    By.xpath("//android.widget.TextView[@text='" + uniqueTitle + "']"));
            assertTrue("Reminder was not deleted!", deletedReminder.size() == 0);

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
