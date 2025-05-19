package org.example.pages;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ReminderHomePage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private final By addButton = By.id("com.samsung.android.app.reminder:id/floating_action_button");
    private final By firstReminder = By.id("com.samsung.android.app.reminder:id/selected_ripple_layout");

    public ReminderHomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openReminderApp() {
        driver.startActivity(new Activity("com.samsung.android.app.reminder", ".ui.LaunchMainActivity"));
    }

    public void openFirstReminder() {
        wait.until(ExpectedConditions.elementToBeClickable(firstReminder)).click();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }
}
