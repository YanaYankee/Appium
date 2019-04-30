import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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



    // Test
    @Test
    public void loginLogoutTest()
    {
        WebElement element_to_open_login_screen = waitForElementPresentById(
                "com.yoopies.babysittingandroid.beta:id/btnParent",
                "Cannot find searched input",
                18,
                driver
        );
        element_to_open_login_screen.click();


        WebElement element_to_enter_login = waitForElementPresentByXpath(
                createXPath("Log in"),
                "Cannot find searched Login btn",
                5,
                driver
        );
        element_to_enter_login.click();


        WebElement element_to_enter_name_input = waitForElementPresentByXpath(
                createXPath("Email"),
                "Cannot find searched mailInput",
                7,
                driver
        );
        element_to_enter_name_input.sendKeys("yanina+brienne@yoopies.com");



//        WebElement element_to_enter_pass_input =  findElementById(
//                driver,
//                "com.yoopies.babysittingandroid.beta:id/passInput");
//        element_to_enter_pass_input.sendKeys("Test1234");

        waitForElementAndSendKeys(
                By.id("com.yoopies.babysittingandroid.beta:id/connect"),
                "Test1234",
                "Cannot find PASSWORD input field",
                driver,
                2);


//
//        WebElement element_to_login = findElementById(
//                driver,
//                "com.yoopies.babysittingandroid.beta:id/connect");
//        element_to_login.click();

// ********************************* tap CONNECT (login) btn ************************************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/connect"),
                "Cannot find CONNECT! btn",
                driver,
                2);


        //
//        WebElement element_to_skip  = waitForElementPresentById(
//                "com.yoopies.babysittingandroid.beta:id/skip",
//                "Cannot find Skip btn",
//                3,
//                driver
//        );
//        element_to_skip.click();

// ********************************* tap SKIP btn ************************************
        waitForElementAndClick(
                By.id("com.yoopies.babysittingandroid.beta:id/skip"),
                "Cannot find Skip btn",
                driver,
                3);



        WebElement element_to_more_screen = waitForElementPresentByXpath(
                createXPath("More"),
                "Cannot find More btn",
                6,
                driver
        );
        element_to_more_screen.click();

        WebElement element_settings_arrow  = waitForElementPresentById(
                "com.yoopies.babysittingandroid.beta:id/logOutArrow",
                "Cannot find arrow logOutscreen",
                7,
                driver
        );
        element_settings_arrow.click();

        WebElement element_logout_arrow  = waitForElementPresentById(
                "com.yoopies.babysittingandroid.beta:id/logOutArrow",
                "Cannot find arrow logOutscreen",
                3,
                driver
        );
        element_logout_arrow .click();

    }
}