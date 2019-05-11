import io.appium.java_client.AppiumDriver;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class Tests  extends Helpers {

    WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        BasicConfigurator.configure();
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "com.yoopies.babysittingandroid.beta");
        capabilities.setCapability("appActivity", "com.yoopies.loginmodule.activities.splash.SplashActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Downloads/app-beta-debug.apk");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Test: User logins and then logs out.
    @Test
    public void loginLogoutTest() {
        waitForElementPresentImplicit(
                By.id("com.yoopies.babysittingandroid.beta:id/btnParent"),
                "I am a parent",
                (AppiumDriver) driver,
                40
        );
    }
}