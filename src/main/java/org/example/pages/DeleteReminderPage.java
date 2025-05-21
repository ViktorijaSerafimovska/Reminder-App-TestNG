package org.example.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class DeleteReminderPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public DeleteReminderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public void clickReminderByTitle(String title) {
        By reminderByTitle = By.xpath("//android.widget.TextView[@text='" + title + "']");
        WebElement reminder = wait.until(ExpectedConditions.elementToBeClickable(reminderByTitle));
        reminder.click();
    }


    public boolean isReminderWithTitleDisplayed(String title) {
        try {
            By reminderByTitle = By.xpath("//android.widget.TextView[@text='" + title + "']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(reminderByTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void deleteReminderViaBottomButton() throws InterruptedException {
        WebElement deleteButton = wait.until(driver ->
                driver.findElement(By.id("com.samsung.android.app.reminder:id/detail_bottom_delete_btn")));
        deleteButton.click();

        WebElement confirmDelete = wait.until(driver ->
                driver.findElement(By.id("android:id/button1")));
        confirmDelete.click();

        Thread.sleep(2000);
    }


//    public boolean verifyReminderIsDeleted(String title) {
//        List<WebElement> deletedReminders = driver.findElements(
//                By.xpath("//android.widget.TextView[@text='" + title + "']"));
//        if (deletedReminders.isEmpty()) {
//            System.out.println("Reminder is successfully deleted.");
//            return true;
//        } else {
//            System.out.println("Reminder is still in the list!");
//            return false;
//        }
//    }

    public boolean verifyReminderIsDeleted(String title) {
        String fullContentDesc = title + ",Today, " ;
        List<WebElement> foundReminders = driver.findElements(
                By.xpath("//android.view.View[@content-desc='" + fullContentDesc + "']"));
        if (foundReminders.isEmpty()) {
            System.out.println("Reminder is successfully deleted.");
            return true;
        } else {
            throw new AssertionError("Reminder with content-desc '" + fullContentDesc + "' is STILL present on screen!");
        }
    }
    public boolean verifyNoRemindersVisible() {
        List<WebElement> visibleReminders = driver.findElements(
                By.xpath("//android.view.View[contains(@content-desc, ',Today, ')]"));

        if (visibleReminders.isEmpty()) {
            System.out.println("No reminders are visible.");
            return true;
        } else {
            throw new AssertionError("There are still visible reminders. Found: " + visibleReminders.size());
        }
    }



}
