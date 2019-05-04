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



    //Ex.4
    // Написать тест, который:
    //Ищет какое-то слово
    //Убеждается, что в каждом результате поиска есть это слово.

    @Test
    public void testSearchAndCheckSearchWordInResults() {

        // 1. Searches for "Selenium"
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Selenium",
                //       "Seleniumdk;sv", //check the next step really works

                "Cannot find searched input",
                driver,
                5
        );
        //2. Checks if that some articles are enlisted in the search result
        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result is empty",
                driver,
                5
        );
//3. Checks if the word is in each item of search result list
        CheckElementsToHaveWord(
                driver,
                "resource-id",
                "org.wikipedia:id/page_list_item_title",
                "Selenium");
        //"Selenium"); //to check if test works

    }
//********************************************************************************************
    //Написать тест, который: Ищет какое-то слово. Убеждается, что найдено несколько стате.
    // Отменяет поиск. Убеждается, что результат поиска пропал

    @Test
    public void testSearchAndCheckResultsAndCancel(){

 // 1. Searches for "Selenium"
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
               "Selenium",
      //       "Seleniumdk;sv", //check the next step really works

                "Cannot find searched input",
                driver,
                5
        );
 //2. Checks if that some articles are enlisted in the search result
        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result is empty",
                driver,
                5
        );
//3. Cancel search
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                driver,
                5
        );
//4. Check if search result articles list closed
        WaitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search list was not closed",
                driver,
                5
        );
    }


    @Test
    public void testClearAndCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );

//Написать функцию, которая проверяет наличие текста “Search…”
// в строке поиска перед вводом текста и помечает тест упавшим, если такого текста нет.
        WebElement search_text = waitForElementAndClick(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        String search_input = search_text.getAttribute("text");
        Assert.assertEquals(
                "Cannot find text 'Search…' in Search input",
                "Search…",
                search_input

        );
//**********************************************************************
        waitForElementAndSendKeys(
                By.xpath(   createXPathContains("Search Wikipedia")   ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );


        WaitForElementAndClear (
                By.id("org.wikipedia:id/search_src_text"),
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
                "Cannot find 'Search Wikipedia' input",
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
               By.xpath(createXPathContains("Search Wikipedia")),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath(   createXPathContains("Search Wikipedia")    ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementPresent(
                By.xpath(  createXPathKey("resource-id", "org.wikipedia:id/page_list_item_container",
                                "Object-oriented programming language")   ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                20
       );
    }


    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath(createXPathContains("Search Wikipedia")),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath(   createXPathContains("Search Wikipedia")    ),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  createXPathKey("resource-id", "org.wikipedia:id/page_list_item_container",
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

