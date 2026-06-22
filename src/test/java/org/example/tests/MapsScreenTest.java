package org.example.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.example.screens.IntroScreen;
import org.example.screens.MapsScreen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapsScreenTest {
    private AndroidDriver driver;
    private MapsScreen mapsScreen;

    @BeforeEach
    public void setUp() {
        Capabilities options = new BaseOptions()
                .amend("appium:app", "C:\\Users\\Admin\\Downloads\\СберБанк+Онлайн+—+с+Салютом_15.9.0_APKPure.apk")
                .amend("platformName", "Android")
                .amend("appium:deviceName", "Pixel 6 Pro")
                .amend("appium:automationName", "uiAutomator2")
                .amend("appium:noReset", false)
                .amend("appium:autoGrantPermissions", true)
                .amend("appium:appPackage", "ru.sberbankmobile")
                .amend("appium:appWaitActivity", "*")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true)
                .amend("appium:skipDeviceInitialization", true)
                .amend("appium:disableWindowAnimation", true);

        driver = new AndroidDriver(this.getUrl(), options);
        mapsScreen = new MapsScreen(driver);
        IntroScreen introScreen = new IntroScreen(driver);
        introScreen.clickMapButton();

    }

    @Test
    @DisplayName("Проверка доступности поля поиска")
    public void checkSearchFieldIsClickable() {
        assertTrue(mapsScreen.checkSearchFieldIsClickable(), "Поле поиска не кликабельно");
    }

    @Test
    @DisplayName("Проверка ввода текста в поле поиска")
    public void checkSearchFieldInput() {
        mapsScreen.checkSearchFieldIsClickable();
        mapsScreen.clickSearchField();
        mapsScreen.typeText();
        mapsScreen.getSearchFieldText();
        assertEquals("test", mapsScreen.getSearchFieldText(), "Текст в поле поиска не соответствует введённому");
    }

    @Test
    @DisplayName("Проверка отображения кнопки Офисы после свайпа")
    public void swipeToOfficeButton() {
        assertTrue(mapsScreen.swipeToOfficeButton(), "Кнопка Офисы не отображается");
    }

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
}