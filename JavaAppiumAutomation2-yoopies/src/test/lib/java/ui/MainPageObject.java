package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class MainPageObject {
    protected AppiumDriver driver;

    //constructor for all tests
    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }



    public String createXPath(String str){

        String str_xpath = "//*[contains(@text, '"+ str +"')]";
        System.out.println(str_xpath);
        return str_xpath;
    }


    public WebElement findElementByName(String str){

        // return driver.findElementByXPath("//*[contains(@text, '"+ str +"')]"); //attention! do not use elementS, casts error
        return driver.findElementByXPath(createXPath(str));
    }

    public WebElement findElementById(String id){

        // return driver.findElementByXPath("//*[contains(@text, '"+ str +"')]"); //attention! do not use elementS, casts error
        return driver.findElement(By.id(id));
    }

    public WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);

        System.out.println(xpath);

        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);

        System.out.println(id);

        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        System.out.println("Element is present");
        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int y_start = (int) (size.height * 0.8);
        int y_end = (int) (size.height * 0.2);

        action.press(x, y_start).waitAction(timeOfSwipe).moveTo(x, y_end).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToElement(By by, String error_message, int max_swipes){
//        driver.findElements(by);
//        driver.findElements(by).size();
        int already_swiped = 0;

        while(driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping. \n" + error_message, 3);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }


//    *************************** Swipe on KeyBoard Opened *************************************

    public void swipeUpOnKeyBoardOpened(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int y_start = (int) (size.height * 0.3);
        int y_end = (int) (size.height * 0.1);

        action.press(x, y_start).waitAction(timeOfSwipe).moveTo(x, y_end).release().perform();
    }

    public void swipeUpQuickOnKeyBoardOpened() {
        swipeUpOnKeyBoardOpened(200);
    }

    public void swipeUpToElementOnKeyBoardOpened(By by, String error_message, int max_swipes){
//        driver.findElements(by);
//        driver.findElements(by).size();
        int already_swiped = 0;

        while(driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping. \n" + error_message, 3);
                return;
            }
            swipeUpQuickOnKeyBoardOpened();
            ++already_swiped;
        }
    }

//    ****************************************** Left Swipe *************************************

    public void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX(); //the most left point on X
        int right_x = left_x + element.getSize().getWidth();//the most left point on X + element width;
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
    public int generateRandomForRegisterData(int number) {
        Random rand = new Random();
        int value = rand.nextInt(number);
        return  value;
    }
}
