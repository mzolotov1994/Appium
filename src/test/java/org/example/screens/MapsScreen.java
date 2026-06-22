package org.example.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MapsScreen {

    AndroidDriver driver;

    //Поле поиска
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"Поиск\"]")
    WebElement searchField;

    @AndroidFindBy(xpath = "\t\n" + "//android.widget.EditText")
    WebElement editText;

    //Кнопка Офис
    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView/android.view.View[4]/android.widget.Button")
    WebElement officeButton;

    //Метод проверки для поля поиск
    public boolean checkSearchFieldIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        return searchField.isEnabled();
    }

    public void clickSearchField() {
        searchField.click();
    }

    //Метод для проверки кнопки Офис после свайпа
    public boolean swipeToOfficeButton() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().className(\"android.widget.HorizontalScrollView\").instance(0))" +
                        ".setAsHorizontalList()" +
                        ".scrollIntoView(new UiSelector().text(\"Офисы\").instance(0))"
        ));
        return officeButton.isDisplayed();
    }

    public void typeText() {
        driver.pressKey(new KeyEvent(AndroidKey.T));
        driver.pressKey(new KeyEvent(AndroidKey.E));
        driver.pressKey(new KeyEvent(AndroidKey.S));
        driver.pressKey(new KeyEvent(AndroidKey.T));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public String getSearchFieldText() {
        return editText.getAttribute("text");
    }


    public MapsScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
