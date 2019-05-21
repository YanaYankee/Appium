import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class Tests extends Helpers {



    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
    //    capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Desktop/GIT/Appium/AutomationHomeTask2/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


// ******************************* Ex 7 (Rotate screen) ***********************
// ****************  Set orientation before each test ***********************

        driver.rotate(ScreenOrientation.PORTRAIT);

//*************************************************************
        
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }


  @Test
  public void testChangeScreenOrientationOnSearchResults(){

      waitForElementAndClick(
              By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
              "Cannot find 'Search Wikipedia' input",
              driver,
              5
      );
      String search_line = "Java";
      waitForElementAndSendKeys(
              By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
              search_line,
              "Cannot find searched input",
              driver,
              5
      );
      waitForElementAndClick(
              By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
              "Cannot find 'Object-oriented programming language' searching by '" + search_line + "'",
              driver,
              15
      );
      String title_before_rotation = waitForElementAndGetAttribute(
              By.id("org.wikipedia:id/view_page_title_text"),
              "text",
              driver,
              "Cannot find title of article",
              15
              );

        driver.rotate(ScreenOrientation.LANDSCAPE);
      String title_after_rotation = waitForElementAndGetAttribute(
              By.id("org.wikipedia:id/view_page_title_text"),
              "text",
              driver,
              "Cannot find title of article",
              15
      );
      Assert.assertEquals(
              "Article title have been changed after rotation",
              title_before_rotation,
              title_after_rotation
      );
      driver.rotate(ScreenOrientation.PORTRAIT);
      String title_after_second_rotation = waitForElementAndGetAttribute(
              By.id("org.wikipedia:id/view_page_title_text"),
              "text",
              driver,
              "Cannot find title of article",
              15
      );
      Assert.assertEquals(
              "Article title have been changed after rotation",
              title_before_rotation,
              title_after_second_rotation
      );

  }



//Ex 6 (Test: assert title)
    @Test
    public void assertElementPresent() {

            waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    driver,
                    5
            );
            waitForElementAndSendKeys(
                    By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                    "Java",
                    "Cannot find searched input",
                    driver,
                    5
            );
            waitForElementAndClick(
                    By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
                    "Cannot find 'Object-oriented programming language' searching by 'Java'",
                    driver,
                    7
            );

// **************** Assert that title is present ***********************

        int amount_of_search_results = getAmountOfElements(
                By.id("org.wikipedia:id/view_page_title_text")
        );
        Assert.assertTrue("No title elements are found or there are more than 1 title on the article",
                amount_of_search_results == 1
        );

    }


//Ex 5 (Test: save 2 Articles to reading list)


    @Test
    //********************************* 1. Save 2 articles to one folder ***************************************
    public void saveTwoArticlesToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                7
        );
        waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                driver,
                7
        );

        waitForElementPresent(
                By.xpath("//*[@text='Change language']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );
        waitForElementPresent(
                By.xpath("//*[@text='Share link']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );
        waitForElementAndClick(
                id( "org.wikipedia:id/onboarding_button" ),
                "Cannot find 'Got it' overlay ",
                driver,
                5
        );
        waitForElementAndClear(
                id( "org.wikipedia:id/text_input" ),
                "Cannot find input article folder",
                driver,
                5
        );
//
//    waitForElementAndSendKeys(
//            By.id( "org.wikipedia:id/text_input" ),
//            "Learning programming",
//            "Cannot put text into articles folder input",
//            driver,
//            5
//    );

        String name_of_folder = "Learning programming";
        waitForElementAndSetValue( id( "org.wikipedia:id/text_input" ),
                "Cannot send keys to name of folder input"+ name_of_folder,
                driver,
                5,
                name_of_folder);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot press 'OK' btn",
                driver,
                5
        );

        waitForElementAndClick(
               id("org.wikipedia:id/menu_page_search"),
                "Cannot click Search btn",
                driver,
                5
        );


        waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find searched input",
                driver,
                5
        );
        String name_second_article = "Appium";
        waitForElementAndClick(
                By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+name_second_article+"']" ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                7
        );

        waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                driver,
                7
        );

        waitForElementPresent(
                By.xpath("//*[@text='Change language']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );
        waitForElementPresent(
                By.xpath("//*[@text='Share link']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );
        waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder + "']"),
                "Cannot find button Add to reading list ",
                driver,
                7
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X btn",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot close article, cannot find X btn",
                driver,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder + "']"),
                "Cannot close article, cannot find X btn",
                driver,
                10
        );


//********************************* 2. Removes one of articles ***************************************


        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article",
                driver
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Delete saved article",
                driver,
                5
        );
//********************************* 3. Assert that second is left unremoved ***************************************
        waitForElementAndClick(
                By.xpath("//*[@text='"+name_second_article+"']"),
                "Cannot find saved article",
                driver,
                5
        );
 //********************************* 4. Visit the article and assert that title text is expected ***************************************
        WebElement title_element = waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
//                "Java (programming language)1", // to check if test fails
                name_second_article,
                article_title
        );




    }














    @Test
public void saveFirstArticleToMyList() {
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            driver,
            5
    );
    waitForElementAndSendKeys(
            By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
            "Java",
            "Cannot find searched input",
            driver,
            5
    );
    waitForElementAndClick(
            By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
            "Cannot find 'Object-oriented programming language' searching by 'Java'",
            driver,
            7
    );
    waitForElementPresent(
            id(  "org.wikipedia:id/view_page_title_text" ),
            "Cannot find article title",
            driver,
            15
    );
    waitForElementAndClick(
            By.xpath("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options",
            driver,
            7
    );

    waitForElementPresent(
                    By.xpath("//*[@text='Change language']"),
                    "Cannot find button Add to reading list ",
                    driver,
                    7
    );
    waitForElementPresent(
                    By.xpath("//*[@text='Share link']"),
                    "Cannot find button Add to reading list ",
                    driver,
                    7
    );
    waitForElementAndClick(
            By.xpath("//*[@text='Add to reading list']"),
            "Cannot find button Add to reading list ",
            driver,
            7
    );
    waitForElementAndClick(
            id( "org.wikipedia:id/onboarding_button" ),
            "Cannot find 'Got it' overlay ",
            driver,
            5
    );
    waitForElementAndClear(
            id( "org.wikipedia:id/text_input" ),
            "Cannot find input article folder",
            driver,
            5
    );
//
//    waitForElementAndSendKeys(
//            By.id( "org.wikipedia:id/text_input" ),
//            "Learning programming",
//            "Cannot put text into articles folder input",
//            driver,
//            5
//    );

    String name_of_folder = "Learning programming";
    waitForElementAndSetValue( id( "org.wikipedia:id/text_input" ),
            "Learning programming",
            driver,
            5,
            name_of_folder);

    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'OK')]"),
            "Cannot press 'OK' btn",
            driver,
            5
    );
    waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X btn",
            driver,
            5
    );
    waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
            "Cannot close article, cannot find X btn",
            driver,
            5
    );
    waitForElementAndClick(
            By.xpath("//*[@text='"+ name_of_folder + "']"),
            "Cannot close article, cannot find X btn",
            driver,
            5
    );
    swipeElementToLeft(
            By.xpath("//*[@text='Java (programming language)']"),
            "Cannot find saved article",
            driver
    );
    waitForElementNotPresent(
            By.xpath("//*[@text='Java (programming language)']"),
            "Delete saved article",
            driver,
            5
    );

}

    @Test
    public void amountOfNotEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        String search_line = "Linkin Park Diskography";
        waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find searched input",
                driver,
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                driver,
                15
        );
        int amount_of_search_results = getAmountOfElements(
                By.xpath( search_result_locator)
        );
        Assert.assertTrue("We found too few results!",
                amount_of_search_results > 0
        );

    }

    @Test
    public void amountOfEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        String search_line = "sdgfsdfsef";
//        String search_line = "selenium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find searched input",
                driver,
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
            By.xpath(empty_result_label),
                "Cannot find empty result label by the request '" + search_line + "'",
                driver,
                15
        );
        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found results request by "
        );

    }

    //Ex.4
    // Написать тест, который:
    //Ищет какое-то слово
    //Убеждается, что в каждом результате поиска есть это слово.

    @Test
    public void testSearchAndCheckSearchWordInResults() {

        // 1. Searches for "Selenium"
        waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );
        waitForElementAndSendKeys(
                id("org.wikipedia:id/search_src_text"),
                "Selenium",
                //       "Seleniumdk;sv", //check the next step really works

                "Cannot find searched input",
                driver,
                5
        );
        //2. Checks if that some articles are enlisted in the search result
        waitForElementPresent(
                id("org.wikipedia:id/search_results_list"),
                "Search result is empty",
                driver,
                5
        );
//3. Checks if the word is in each item of search result list
        checkElementsToHaveWord(
                id("org.wikipedia:id/page_list_item_title"),
                driver,
                "Selenium");
        //"Selenium"); //to check if test works

    }

    @Test
    public void testSwipeArticle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath(  "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                driver,
                7
        );
        waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                driver,
                15
        );
        swipeUpToElement(
                By.xpath("//*[contains(@text, 'View page in browser')]"),
                "Cannot find ",
                driver,
                100
        );
    }
//********************************************************************************************
    //Написать тест, который: Ищет какое-то слово. Убеждается, что найдено несколько стате.
    // Отменяет поиск. Убеждается, что результат поиска пропал

    @Test
    public void testSearchAndCheckResultsAndCancel(){

 // 1. Searches for "Selenium"
        waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );
        waitForElementAndSendKeys(
                id("org.wikipedia:id/search_src_text"),
               "Selenium",
      //       "Seleniumdk;sv", //check the next step really works

                "Cannot find searched input",
                driver,
                5
        );
 //2. Checks if that some articles are enlisted in the search result
        waitForElementPresent(
                id("org.wikipedia:id/search_results_list"),
                "Search result is empty",
                driver,
                5
        );
//3. Cancel search
        waitForElementAndClick(
                id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                driver,
                5
        );
//4. Check if search result articles list closed
        waitForElementNotPresent(
                id("org.wikipedia:id/search_results_list"),
                "Search list was not closed",
                driver,
                5
        );
    }


    @Test
    public void testClearAndCancelSearch() {
        waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );

//Написать функцию, которая проверяет наличие текста “Search…”
// в строке поиска перед вводом текста и помечает тест упавшим, если такого текста нет.
        WebElement search_text = waitForElementAndClick(
                id("org.wikipedia:id/search_src_text"),
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
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );


        waitForElementAndClear(
                id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                driver,
                5
        );
//to check the test the following method is to be commented
        waitForElementAndClick(
                id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                driver,
                5
        );
        waitForElementNotPresent(
                id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                driver,
                5
        );
    }
    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                15
        );
//to check the test the following method is to be commented
        waitForElementAndClick(
                id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                driver,
                5
        );
        waitForElementNotPresent(
                id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                driver,
                5
        );
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
               By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                20
       );
    }


    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                driver,
                5
        );
        waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                driver,
                5
        );
        waitForElementAndClick(
                By.xpath( "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                driver,
                7
        );
        WebElement title_element = waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
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

