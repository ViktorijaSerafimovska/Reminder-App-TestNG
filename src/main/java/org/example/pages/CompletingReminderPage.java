package org.example.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CompletingReminderPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private final By completeCheckBox = MobileBy.AndroidUIAutomator(
            "new UiSelector().resourceId(\"com.samsung.android.app.reminder:id/reminder_color_complete_view\").instance(0)");

    public CompletingReminderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isReminderWithTitleDisplayed(String title) {
        try {
            By reminderTitle = By.xpath("//android.widget.TextView[@text='" + title + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(reminderTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFirstCompleteCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(completeCheckBox)).click();
    }

    public void clickCompleteCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(completeCheckBox)).click();
    }
}
