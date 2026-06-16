package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

//Intro screen
public class IntroScreen {

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.widget.Button")
    private WebElement loginWithCardNumberButton;

    public boolean checkLoginButtonWithCardNumber() {
        return loginWithCardNumberButton.isEnabled();
    }

    public IntroScreen(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
