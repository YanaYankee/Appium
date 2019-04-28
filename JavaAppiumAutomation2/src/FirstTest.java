import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;

//{
//        "platformName": "Android",
//        "deviceName": "AndroidTestDevice",
//        "appPackage": "org.wikipedia",
//        "appActivity": ".main.MainActivity"
//        }
public class FirstTest extends Helpers {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
     // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Desktop/GIT/Appium/JavaAppiumAutomation2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void firstTest()
    {
    //   WebElement elementSearch = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");//attention! do not use elementS, casts error
    //    WebElement elementSkip = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");//attention! do not use elementS, casts error

 // User opens app and skips intro
//        WebElement elementSkip = findElementByName(driver, "SKIP");
//        elementSkip.click();
// User taps Search Wikipedia
        WebElement element_to_init_search = findElementByName(driver, "Search Wikipedia");
        element_to_init_search.click();
// User taps Search input and types Java
        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
                createXPath("Search Wikipedia"),
                "Cannot fined searched input",
                driver

        );
     // findElementByName(driver, "Search Wikipedia");
        element_to_enter_search_line.sendKeys("Java");


        waitForElementPresentByXpath(
                createXPathResourceId("org.wikipedia:id/page_list_item_container", "Object-oriented programming language"),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                20

        );
    }

    // User taps Search input and types Java


}

