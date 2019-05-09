import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class Tests extends Helpers {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "com.yoopies.babysittingandroid.beta");
        capabilities.setCapability("appActivity", "com.yoopies.loginmodule.activities.splash.SplashActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Desktop/GIT/Appium/JavaAppiumAutomation2-yoopies/apks/app-beta-debug.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }

    // Test: User logins and then logs out.
    @Test
    public void registerAndMySituationSettings() {
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/btnParent"),
                "Cannot find Parent btn input",
                driver,
                23
        );
// ********************************* tap Create account btn ***********************
        waitForElementAndClick(
                By.xpath(createXPath("Create an account")),
                "Cannot find searched 'Create an account' btn",
                driver,
                5
        );

// ********************************* emailInput on Create account screen ***********************
        int j = generateRandomForRegisterData(444);

        String email_for_new_account  = "yanina+autotest" + j + "@yoopies.com" ;
        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/mailInput"),
                email_for_new_account,
                "Cannot find searched emailInput on Create account screen",
                driver,
                7
        );

// ********************************* nameInput on Create account screen ***********************

    String name_for_new_account  = "autotestParent";

    waitForElementAndSendKeys(
            By.id("com.yoopies.babysittingandroid.beta:id/firstnameInput"),
            name_for_new_account,
            "Cannot find searched nameInput on Create account screen",
            driver,
            7
            );
// ***************** swipe for Password and password input on Create account screen ************************************

        swipeUpToElementOnKeyBoardOpened(
                By.id("com.yoopies.babysittingandroid.beta:id/passInput"),
                //          By.xpath("//*[@text='View page in browser1']"), //to check if test works
                "Cannot find the Password input on register screen. Swipe failed",
                driver,
                1);

// ************************ lastnameInput on Create account screen ***********************

        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/lastnameInput"),
                name_for_new_account,
                "Cannot find searched lastNameInput on Create account screen",
                driver,
                3
        );

// ********************************* password on Create account screen ***********************
    String password = "Test1234";

    waitForElementAndSendKeys(
            By.id("com.yoopies.babysittingandroid.beta:id/passInput"),
            password,
            "Cannot find searched emailInput on Create account screen",
            driver,
            3
            );

// ********************************* tap Next btn on Create account screen  ***********************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/nextButton"),
                "Cannot find Next btn on Register",
                driver,
                3
        );

// ********************************* Address input on Create account screen 2 ***********************
        String address = "paris rue ordener 44";

        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/postalAddressInput"),
                address,
                "Cannot find searched Address input on Create account screen 2",
                driver,
                3
        );

// ********** tap Didn't find what you were looking for? btn on Create account screen  ***********************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/title"),
                "Cannot find 'Didn't find what you were looking for?' ",
                driver,
                3
        );

// ********** tap Didn't find what you were looking for? btn on Create account screen  ***********************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/countriesSpinner"),
                "Cannot find 'Didn't find what you were looking for?' ",
                driver,
                3
        );

// ********** tap FR text on select country screen ***********************
        waitForElementAndClick(
                By.xpath(  "//*[@text='FR']" ),
                 "Cannot find FR on select country screen ",
                driver,
                3
                );

// ********** tap ACCEPT btn on select country screen ***********************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/positiveButton"),
                "Cannot find ACCEPT btn on select country screen ",
                driver,
                7
        );

// ********************************* Address input on Create account screen 2 ***********************
        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/postalAddressInput"),
                address,
                "Cannot find searched Address input on Create account screen 2",
                driver,
                3
        );

// ********** tap 44 Rue Ordener on select address screen ***********************
        waitForElementAndClick(
                By.xpath(  "//*[@text='44 Rue Ordener']" ),
                "Cannot find 44 Rue Ordener on select address screen ",
                driver,
                7
        );


// ******************* select phone Code arrow on Create account screen 2 ***********************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/imageView_arrow"),
                "Cannot find select phone Code arrow on Create account screen 2",
                driver,
                3
        );

// ************************** tap France (FR) on Country code list ***********************
        waitForElementAndClick(
                By.xpath(  "//*[@text='France (FR)']" ),
                "Cannot find France (FR) on Country code list",
                driver,
                7
        );

// ********** phone number input on Create account screen 2 ***********************
        String phoneNumberOfNewUser = "637333777";
        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/phoneInputCarrier"),
                phoneNumberOfNewUser,
                "Cannot find searched emailInput on Create account screen",
                driver,5
        );
// ********************************* tap Next btn on Create account screen 2 ***********************
        waitForElementAndClick(
                By.xpath( "//*[@text='Registration']" ),
                "Cannot find Registration btn on Register",
                driver,
                7
        );

// ********************************* tap SKIP btn ************************************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/skip"),
                "Cannot find Skip btn",
                driver,
                3);

// ********************************* tap More btn ************************************

        waitForElementAndClick(//
                By.xpath( "//*[@text='More']"),
                "Cannot find More btn",
                driver,
                7);

// ********************************* swipeUp to logout arrow ************************************
//        swipeUp(1000, driver);
        swipeUpToElement(By.id("com.yoopies.babysittingandroid.beta:id/logOutArrow"),
                "Cannot find arrow logOutscreen",
                driver,
                2);

// ********************************* Logout arrow ************************************

        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/logOutArrow"),
                "Cannot find arrow logOutscreen",
                driver, 7);

// ********************************* Logout arrow ************************************

        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/logOutArrow"),
                "Cannot find arrow logOutscreen",
                driver,
                5);

    }







    // Test: User logins and then logs out.
    @Test
    public void loginLogoutTest()
    {
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/btnParent"),
                "Cannot find Parent btn input",
                driver,
                23
        );

// ********************************* tap PASSWORD (login) input ***********************
        waitForElementAndClick(
                By.xpath(createXPath("Log in")),
                "Cannot find searched Login btn",
                driver,
                5
        );

// ********************************* tap EMAIL (login) input ***********************
        waitForElementAndSendKeys(
                By.xpath(createXPath("Email")),
                "yanina+brienne@yoopies.com",
                "Cannot find searched mailInput",
                driver,
                7
        );

// ********************************* tap PASSWORD (login) input ***********************
        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/passInput"),
                "Test1234",
                "Cannot find PASSWORD input field",
                driver,
                2);


// ********************************* tap CONNECT (login) btn **************************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/connect"),
                "Cannot find CONNECT! btn",
                driver,
                2);

// ********************************* tap SKIP btn ************************************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/skip"),
                "Cannot find Skip btn",
                driver,
                3);

// ********************************* tap More btn ************************************

//        waitForElementAndClick(
////                By.xpath(createXPath("More")),
////                "Cannot find More btn",
////                driver,
////                10);

        // ********************************* tap More btn ************************************

        waitForElementAndClick(//
                By.xpath( "//*[@text='More']"),
                "Cannot find More btn",
                driver,
                7);


// ********************************* swipeUp ************************************

        swipeUp(1000, driver);

// ********************************* Logout arrow ************************************

        waitForElementAndClick(
            By.id("com.yoopies.babysittingandroid.beta:id/logOutArrow"),
            "Cannot find arrow logOutscreen",
            driver,
            7);

// ********************************* Logout arrow ************************************

        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/logOutArrow"),
                "Cannot find arrow logOutscreen",
                driver,
                3);
    }
}