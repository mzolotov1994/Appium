package org.example;
// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntroScreenTest {

    private AndroidDriver driver;

    private IntroScreen introScreen;

    @BeforeEach
    public void setUp() {
        Capabilities options = new BaseOptions()
                .amend("appium:app", "C:\\Users\\Admin\\Downloads\\СберБанк+Онлайн+—+с+Салютом_15.9.0_APKPure.apk")
                .amend("platformName", "Android")
                .amend("appium:deviceName", "Pixel 6 Pro")
                .amend("appium:automationName", "uiAutomator2")
                .amend("appium:noReset", true)
                .amend("appium:autoGrantPermissions", true)
                .amend("appium:appPackage", "ru.sberbankmobile")
                .amend("appium:appWaitActivity", "*")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(this.getUrl(), options);
        introScreen = new IntroScreen(driver);

    }

    @Test
    public void checkLoginButtonWithCardNumber() {
        assertTrue(introScreen.checkLoginButtonWithCardNumber(), "Кнопка логина по номеру карты не активна");
    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

//    private URL getUrl() {
//        try {
//            return new URL("http://127.0.0.1:4723");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
}
