package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPageObject extends MainPageObject {
    private static final String
            LOGIN_EMAIL_INIT_ELEMENT = "//*[contains(@text, 'Email')]",
            LOGIN_PASSWORD_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/passInput",
            LOGIN_CONNECT_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/connect";



    public LoginPageObject(AppiumDriver driver){
        super(driver);
    }
// ********************************* tap EMAIL (login) input ***********************
    public void initSendKeysToEmailAction() {
            this.waitForElementPresent(
                By.xpath(LOGIN_EMAIL_INIT_ELEMENT), //"//*[contains(@text, 'Log in')]"
                "Cannot find email input btn after clicking login init element",
                5
        );

            this.waitForElementAndSendKeys(
                By.xpath(LOGIN_EMAIL_INIT_ELEMENT),
                "yanina+brienne@yoopies.com",
                "Cannot find searched mailInput after clicking login init element",
                7);
    }

    public void initSendKeysToPasswordAction() {

            this.waitForElementPresent(
                    By.id(LOGIN_PASSWORD_INIT_ELEMENT), //"//*[contains(@text, 'Log in')]"
                    "Cannot find PASSWORD input after clicking login init element",
                    5);

// ********************************* tap PASSWORD (login) input ***********************
            this.waitForElementAndSendKeys(
                By.id(LOGIN_PASSWORD_INIT_ELEMENT),
                "Test1234",
                "Cannot find PASSWORD input field after sending keys email init element",
                2);
    }
    public void initFinishLoginInProcessAction() {
// ********************************* tap CONNECT (login) btn **************************
                this.waitForElementAndClick(
                By.id(LOGIN_CONNECT_INIT_ELEMENT),
                "Cannot find CONNECT! btn after sending keys to login password element",
                2);

    }

}
