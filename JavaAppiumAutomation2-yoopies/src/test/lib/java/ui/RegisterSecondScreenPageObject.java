package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class RegisterSecondScreenPageObject extends MainPageObject {
    private static final String
            REGISTER_ADDRESS_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/postalAddressInput",
            REGISTER_ADDRESS_STRING = "paris rue ordener 44",
            NO_SUCH_ADDRESS_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/title",
            ADDRESS_FROM_SUGGESTED_LIST = "//*[contains(@text, '44 Rue Ordener')]",
            SELECT_COUNTRY_CODE_ARROW = "com.yoopies.babysittingandroid.beta:id/imageView_arrow",
            REGISTER_PHONE_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/phoneInputCarrier",
            REGISTER_PHONE_NUMBER_STRING = "637333777",
            REGISTER_BUTTON_INIT_ELEMENT = "//*[contains(@text, 'Registration')]"; //


    public RegisterSecondScreenPageObject(AppiumDriver driver){
        super(driver);
    }


    public void initRegisterAddressInputSendKeys() {
        // ********************************* Address input on Create account screen 2 ***********************
        this.waitForElementAndSendKeys(
                By.id(REGISTER_ADDRESS_INIT_ELEMENT),
                REGISTER_ADDRESS_STRING,
                "Cannot find searched Address input on Create account screen 2",
                3
        );
    }
    public void initRegisterNoSuchAddressClick() {
        // ********** tap Didn't find what you were looking for? btn on Create account screen  ***********************
        this.waitForElementAndClick(
                By.id(NO_SUCH_ADDRESS_INIT_ELEMENT),
                "Cannot find 'Didn't find what you were looking for?' ",
                3
        );
    }

    public void initRegisterConfirmAddress(){
        // ********** tap 44 Rue Ordener on select address screen ***********************
        this.waitForElementAndClick(
                By.xpath(ADDRESS_FROM_SUGGESTED_LIST),
                "Cannot find " + ADDRESS_FROM_SUGGESTED_LIST + " on select address screen ",
                7
        );
    }

    public void initRegisterPhoneCodeSelector() {
        // ******************* select phone Code arrow on Create account screen 2 ***********************
        this.waitForElementAndClick(
                By.id(SELECT_COUNTRY_CODE_ARROW),
                "Cannot find select phone Code arrow on Create account screen 2",
                3
        );
    }
    public void initRegisterPhoneNumberInputSendKeys() {
        // ********** phone number input on Create account screen 2 ***********************

        this.waitForElementAndSendKeys(
                By.id(REGISTER_PHONE_INIT_ELEMENT),
                REGISTER_PHONE_NUMBER_STRING,
                "Cannot find searched emailInput on Create account screen",
                5
        );
    }
    public void RegisterButtonClick() {
        // ********************************* tap Next btn on Create account screen 2 ***********************
        this.waitForElementAndClick(
                By.xpath(REGISTER_BUTTON_INIT_ELEMENT),
                "Cannot find Registration btn on Register",
                7
        );
    }
}
