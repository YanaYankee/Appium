package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CountriesSpinner  extends MainPageObject {
    private static final String
            COUNTRIES_SPINNER_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/countriesSpinner",
            FRANCE_COUNTRY_INIT_ELEMENT = "//*[contains(@text, 'FR')]",
            ACCEPT_BUTTON_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/positiveButton",
            FRANCE_COUNTRY_FROM_PICKER_INIT_ELEMENT = "//*[contains(@text, 'France (FR)')]";


    public CountriesSpinner(AppiumDriver driver){
        super(driver);
    }

    public void initCountriesSpinnerObject() {
        // ********** tap Didn't find what you were looking for? btn on Create account screen  ***********************
        this.waitForElementAndClick(
                By.id(COUNTRIES_SPINNER_INIT_ELEMENT),
                "Cannot find 'Didn't find what you were looking for?' ",
                3
        );
    }
    public void SelectCountry() {
        // ********** tap FR text on select country screen ***********************
        this.waitForElementAndClick(
                By.xpath(FRANCE_COUNTRY_INIT_ELEMENT),
                "Cannot find FR on select country screen ",
                3
        );
    }
    public void AcceptButtonClick() {
        // ********** tap ACCEPT btn on select country screen ***********************
        this.waitForElementAndClick(
                By.id(ACCEPT_BUTTON_INIT_ELEMENT),
                "Cannot find ACCEPT btn on select country screen ",
                7
        );
    }

    public void SelectCountryCode() {
        // ********** tap FR text on select country screen ***********************
        this.waitForElementAndClick(
                By.xpath(FRANCE_COUNTRY_FROM_PICKER_INIT_ELEMENT),
                "Cannot find "+ FRANCE_COUNTRY_FROM_PICKER_INIT_ELEMENT + " on select country screen ",
                3
        );
    }


}

