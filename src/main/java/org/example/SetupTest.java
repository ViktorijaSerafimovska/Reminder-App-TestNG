package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SetupTest {
    public static AndroidDriver initializeDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13");
        options.setDeviceName("Galaxy A23 5G");
        options.setAutomationName("UiAutomator2");//framework
        options.setAppPackage("com.samsung.android.app.reminder");
        options.setAppActivity(".ui.LaunchMainActivity");
        options.setNoReset(true);
        options.setNewCommandTimeout(Duration.ofSeconds(300));

        return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }
}
