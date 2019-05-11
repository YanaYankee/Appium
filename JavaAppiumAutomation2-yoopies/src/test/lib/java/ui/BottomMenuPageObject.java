package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class BottomMenuPageObject extends MainPageObject {
    private static final String
            MORE_INIT_ELEMENT = "//*[contains(@text, 'More')]";//"//*[contains(@text, '44 Rue Ordener')]",


    public BottomMenuPageObject(AppiumDriver driver){
        super(driver);
    }
    public void initMoreBtnClickAction() {
        this.waitForElementAndClick(
                By.xpath( MORE_INIT_ELEMENT),
                "Cannot find More btn",
                7);

    }

}
