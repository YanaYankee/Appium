package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SignInSignUpPageObject extends MainPageObject {
    private static final String
            PARENT_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/btnParent",
            LOGIN_INIT_ELEMENT = "//*[contains(@text, 'Log in')]",
            REGISTER_INIT_ELEMENT = "//*[contains(@text, 'Create an account')]";



    public SignInSignUpPageObject(AppiumDriver driver){
        super(driver);
    }

    public void initSignInSignUpScreen() {
        this.waitForElementAndClick(
                By.id(PARENT_INIT_ELEMENT),
                "Cannot find Parent btn",
                15);
        this.waitForElementPresent(
                By.xpath(LOGIN_INIT_ELEMENT), //"//*[contains(@text, 'Log in')]"
                "Cannot find Login btn after clicking parent init element",
                5
        );
    }

    public void initLoginScreen() {
        // ********************************* tap PASSWORD (login) input ***********************
        this.waitForElementAndClick(
                By.xpath(LOGIN_INIT_ELEMENT), //"//*[contains(@text, 'Log in')]"
                "Cannot find Login btn after clicking parent init element",
                5
        );
    }
    public void  initRegisterScreen() {
        this.waitForElementAndClick(
                By.xpath(REGISTER_INIT_ELEMENT),
                "Cannot find searched 'Create an account' btn",
                5
        );
    }
}
