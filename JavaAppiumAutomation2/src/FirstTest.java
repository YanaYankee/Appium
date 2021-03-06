import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    public void saveArticleToMyList() {
        waitForElementAndClick(
                By.xpath(createXPath("Search Wikipedia")),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath(   createXPath("Search Wikipedia")    ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  createXPathResourceId("org.wikipedia:id/page_list_item_container",
                        "Object-oriented programming language")   ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                7
        );
        waitForElementPresent(
                By.id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
        waitForElementAndClick(
                By.xpath(  "//android.widget.ImageView[@content-desc='More options']" ),
                "Cannot find button to open article options",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//*[@text='Add to reading list']" ),
                "Cannot find option to add article to reading list",
                driver,
                8
        );
        waitForElementAndClick(
                By.id(  "org.wikipedia:id/onboarding_button" ),
                "Cannot find 'Got it' tip overlay",
                driver,
                5
        );
        WaitForElementAndClear(
                By.id(  "org.wikipedia:id/text_input" ),
                "Cannot find input to send name of articles folder",
                driver,
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id(  "org.wikipedia:id/text_input" ),
                name_of_folder,
                "Cannot put text into articles folder input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//*[@text='OK']" ),
                "Cannot press ok button",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//android.widget.ImageButton[@content-desc='Navigate up']" ),
                "Cannot close article, cannot find X link",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//android.widget.FrameLayout[@content-desc='My lists']" ),
                "Cannot find navigation item  to 'My Lists'",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//*[@text='" + name_of_folder + "']" ),
                "Cannot find created folder",
                driver,
                5
        );
        swipeElementToLeft(
                By.xpath( "//*[@text='" + name_of_folder + "']"  ),
                "Cannot find saved article",
                driver
        );
        waitForElementPresent(
                By.xpath( "//*[@text='" + name_of_folder + "']"  ),
                "Cannot delete saved article",
                driver,
                5
        );

    }

    @Test
    public void testCompareSwipeArticle(){
        waitForElementAndClick(
                By.xpath(createXPath("Search Wikipedia")),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath(   createXPath("Search Wikipedia")    ),
                "Appium",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//*[@resource-id=org.wikipedia:id/page_list_item_title][@text='Appium']"), // *// move to lower level, and without look in the same element
                "Cannot find 'Appium' in search",
                driver,
                7
        );
        waitForElementPresent(
                By.id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
//        swipeUp(1000, driver);

        swipeUpToElement(
                By.xpath("//*[@text='View page in browser']"),
      //          By.xpath("//*[@text='View page in browser1']"), //to check if test works
                "Cannot find the end end of the article",
                driver,
                20
        );

    }

    @Test
    public void testClearAndCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipadia' input",
                driver,
                15
        );
        waitForElementAndSendKeys(
                By.xpath(   createXPath("Search Wikipedia")   ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        WaitForElementAndClear (
                By.id("org.wi kipedia:id/search_src_text"),
                "Cannot find search field",
                driver,
                5
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
                By.xpath(   createXPath("Search Wikipedia")    ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementPresent(
                By.xpath(  createXPathResourceId("org.wikipedia:id/page_list_item_container",
                                "Object-oriented programming language")   ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                20
       );
    }


    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath(createXPath("Search Wikipedia")),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath(   createXPath("Search Wikipedia")    ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  createXPathResourceId("org.wikipedia:id/page_list_item_container",
                        "Object-oriented programming language")   ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                7
        );
        WebElement title_element = waitForElementPresent(
                By.id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
//                "Java (programming language)1", // to check if test fails
                "Java (programming language)",
                article_title

        );
    }

}

