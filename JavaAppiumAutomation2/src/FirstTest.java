import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        waitForElementAndClick(
               By.xpath(createXPath("Search Wikipedia")),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath(
                        createXPath(
                                "Search Wikipedia"
                        )
                ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementPresent(
                By.xpath(
                        createXPathResourceId(
                        "org.wikipedia:id/page_list_item_container",
                        "Object-oriented programming language"
                        )
                ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                20
       );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipadia' input",
                driver,
                15
        );
//to check the test the following method is to be commented
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                driver,
                5
        );
        WaitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                driver,
                5
        );
    }

    // User taps Search input and types Java


}

