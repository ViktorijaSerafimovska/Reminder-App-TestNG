package org.example.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpdateReminderPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private final By editButton = By.id("com.samsung.android.app.reminder:id/detail_bottom_edit_btn");
    private final By titleInput = By.id("com.samsung.android.app.reminder:id/add_viewholder_text_view");
    private final By addTime = By.xpath("//android.widget.LinearLayout[@content-desc='Add time']");
    private final By dateField = By.id("com.samsung.android.app.reminder:id/condition_date_text");
    private final By specificDate = By.xpath("//android.view.View[@content-desc='Friday, May 30, 2025']");
    private final By timeField = By.id("com.samsung.android.app.reminder:id/condition_time_text");
    private final By saveButton = By.id("com.samsung.android.app.reminder:id/action_save_reminder");

    public UpdateReminderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void setTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).sendKeys(title);
    }

    public void setDate() {
        wait.until(ExpectedConditions.elementToBeClickable(addTime)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
        wait.until(ExpectedConditions.elementToBeClickable(specificDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(timeField)).click();
    }

    public void saveReminder() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
}
