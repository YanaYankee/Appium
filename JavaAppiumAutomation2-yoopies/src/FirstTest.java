import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class FirstTest extends Helpers {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9");
        // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "com.yoopies.babysittingandroid.beta");
        capabilities.setCapability("appActivity", "com.yoopies.loginmodule.activities.splash.SplashActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Desktop/GIT/Appium/JavaAppiumAutomation2/apks/app-allIncluded-debug.apk");

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

        WebElement elementSkip = findElementByName(driver, "SKIP");
        elementSkip.click();
        //    WebElement elementSkip = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");//attention! do not use elementS, casts error


        //   WebElement elementSearch = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");//attention! do not use elementS, casts error
        WebElement elementSearch = findElementByName(driver, "Search Wikipedia");
        elementSearch.click();

        System.out.println("First test run");
    }
}