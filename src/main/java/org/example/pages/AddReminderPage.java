package org.example.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddReminderPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private final By titleInput = By.id("com.samsung.android.app.reminder:id/add_viewholder_text_view");
    private final By addTime = By.xpath("//android.widget.LinearLayout[@content-desc='Add time']");
    private final By dateField = By.id("com.samsung.android.app.reminder:id/condition_date_text");
    private final By saveButton = By.id("com.samsung.android.app.reminder:id/action_save_reminder");


    public AddReminderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void setTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).sendKeys(title);
    }

    public void setDate() {
        wait.until(ExpectedConditions.elementToBeClickable(addTime)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
    }

    public void saveReminder() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickReminderByTitle(String title) {
        By reminderByTitle = By.xpath("//android.widget.TextView[@text='" + title + "']");
        WebElement reminder = wait.until(ExpectedConditions.elementToBeClickable(reminderByTitle));
        reminder.click();
    }
}
