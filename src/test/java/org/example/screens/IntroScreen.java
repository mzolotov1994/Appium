package org.example.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Intro screen
public class IntroScreen {

    WebDriver driver;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.widget.Button")
    private WebElement loginWithCardNumberButton;

    @AndroidFindBy(id = "ru.sberbankmobile:id/logo")
    private WebElement mainLogo;

    //Элементы для кнопки помощи в хедере
    @AndroidFindBy(xpath = "\t\n" + "//android.widget.Button[@content-desc=\"Помощь\"]")
    private WebElement helpHeaderButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Помощь по входу\"]")
    private WebElement loginHelpHeaderText;

    //Элемент для кнопки "Нет карты сбера"
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.sberbankmobile:id/title_text_view\" and @text=\"Нет карты Сбера\"]")
    private WebElement notCardButton;

    //Элемент кнопки входа по логину
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"ru.sberbankmobile:id/container\"])[2]")
    private WebElement loginButton;

    //Элемент кнопки входа по лицу
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"ru.sberbankmobile:id/container\"])[1]")
    private WebElement loginWithFaceButton;

    //Элемент кнопка На карте
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"На карте, вкладка, 1 из 2\"]")
    WebElement mapsButton;

    //Метод проверки активности кнопки логина по карте
    public boolean checkLoginButtonWithCardNumber() {
        return loginWithCardNumberButton.isEnabled();
    }

    //Метод проверки видимости главного лого в хедере
    public boolean checkMainLogoIsVisible() {
        return mainLogo.isDisplayed();
    }

    //Методы для проверки заголовка после клика на кнопку помощи в хедере
    public void checkHelpButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(helpHeaderButton)).click();
    }

    public String getHelpText() {
        return loginHelpHeaderText.getText();
    }

    //Метод для свайпа к кнопке Оформить и стать клиентом
    public boolean swipeLeftToNotCardButton() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"ru.sberbankmobile:id/title_text_view\").instance(0))" +
                        ".setAsHorizontalList()" + ".scrollIntoView(new UiSelector().text(\"\t\n" +
                        "Нет карты Сбера\").instance(0))"
        ));
        return notCardButton.isDisplayed();
    }

    //Метод для проверки входа по логину
    public boolean checkLoginButtonIsClickable() {
        return loginButton.isEnabled();
    }

    //Метод проверки входа по лицу
    public boolean checkLoginWithFaceButtonIsVisible() {
        return loginWithFaceButton.isDisplayed();
    }

    //Метод для клика по кнопке на карте
    public void clickMapButton() {
        mapsButton.click();
    }

    public IntroScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
