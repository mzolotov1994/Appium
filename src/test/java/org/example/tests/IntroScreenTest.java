package org.example.tests;
// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.example.screens.IntroScreen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.ScreenOrientation;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        introScreen = new IntroScreen(driver);

    }

    @Test
    @DisplayName("Проверка активности кнопки логина по номеру карты")
    public void checkLoginButtonWithCardNumber() {
        assertTrue(introScreen.checkLoginButtonWithCardNumber(), "Кнопка логина по номеру карты не активна");
    }

    @Test
    @DisplayName("Проверка отображения лого в хедере")
    public void checkMainLogoIsVisible() {
        assertTrue(introScreen.checkMainLogoIsVisible(), "Лого в хедере не отображается");
    }

    @Test
    @DisplayName("Проверка заголовка экрана после клика на кнопку помощи в хедере")
    public void checkLoginHelpTextOnHeader() {
        introScreen.checkHelpButton();
        String helpText = introScreen.getHelpText();
        assertTrue(helpText.contains("Помощь по входу"), "Текст заголовка не соответствует требованиям");
    }

    @Test
    @DisplayName("Проверка отображения кнопки 'Нет карты Сбера'")
    public void checkNotCardButton() {
        assertTrue(introScreen.swipeLeftToNotCardButton(), "Кнопка 'Нет карты Сбера' не видна после свайпа");
    }

    @Test
    @DisplayName("Проверка активности кнопки входа по логину")
    public void checkLoginButtonIsClickable() {
        assertTrue(introScreen.checkLoginButtonIsClickable(), "Кнопка входа по логину не активна");
    }

    @Test
    @DisplayName("Проверка отображения кнопки логина по лицу")
    public void checkLoginWithFaceButtonIsVisible() {
        assertTrue(introScreen.checkLoginWithFaceButtonIsVisible(), "Кнопка логина по лицу не отображается");
    }

    @Test
    @DisplayName("Проверка получения Exception при попытке смены ориентации экрана")
    public void checkOrientationIsLocked() {
        assertThrows(InvalidElementStateException.class, () -> driver.rotate(ScreenOrientation.LANDSCAPE),
                "Приложение не заблокировало смену ориентации");
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
