import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class Helpers {

    public String createXPath(String str){

        String str_xpath = "//*[contains(@text, '"+ str +"')]";
        System.out.println(str_xpath);
        return str_xpath;
    }



    public void testLogin_ImplicitWait(AppiumDriver driver,long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }



//    public WebElement waitForElementPresent(By by, String error_message, AppiumDriver driver, long timeoutInSeconds )
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.withMessage(error_message + "\n");
//
//        return wait.until(
//                ExpectedConditions.presenceOfElementLocated(by)
//        );
//    }

    public WebElement waitForElementPresentImplicit(By by, String text, AppiumDriver driver, long timeoutInSeconds )
    {
        testLogin_ImplicitWait(driver, timeoutInSeconds);
        WebElement element = driver.findElement(by);

        assertThat(element.getText(), is(text));
        return element;
    }

    public WebElement waitForElementAndClick(By by, String text, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentImplicit( by,  text,  driver,  timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String text, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentImplicit( by,  text,  driver,  timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    protected void swipeUp(int timeOfSwipe, AppiumDriver driver) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int y_start = (int) (size.height * 0.8);
        int y_end = (int) (size.height * 0.2);

        action.press(x, y_start).waitAction(timeOfSwipe).moveTo(x, y_end).release().perform();
    }

    protected void swipeUpQuick(WebDriver driver) {
        swipeUp(200, (AppiumDriver) driver);
    }

    protected void swipeUpToElement(By by, String text, WebDriver driver, int max_swipes){
//        driver.findElements(by);
//        driver.findElements(by).size();
        int already_swiped = 0;

        while(driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresentImplicit( by,  text, (AppiumDriver) driver,3);
           //     waitForElementPresent(by, "Cannot find element by swiping. \n" + error_message, (AppiumDriver) driver, 3);
                return;
            }
            swipeUpQuick(driver);
            ++already_swiped;
        }
    }


//    *************************** Swipe on KeyBoard Opened *************************************

    protected void swipeUpOnKeyBoardOpened(int timeOfSwipe, AppiumDriver driver) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int y_start = (int) (size.height * 0.3);
        int y_end = (int) (size.height * 0.1);

        action.press(x, y_start).waitAction(timeOfSwipe).moveTo(x, y_end).release().perform();
    }

    protected void swipeUpQuickOnKeyBoardOpened(WebDriver driver) {
        swipeUpOnKeyBoardOpened(200, (AppiumDriver) driver);
    }

    protected void swipeUpToElementOnKeyBoardOpened(By by, String text, WebDriver driver, int max_swipes){
//        driver.findElements(by);
//        driver.findElements(by).size();
        int already_swiped = 0;

        while(driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresentImplicit( by,  text, (AppiumDriver)driver,  3);
   //             waitForElementPresent(by, "Cannot find element by swiping. \n" + error_message, (AppiumDriver) driver, 3);
                return;
            }
            swipeUpQuickOnKeyBoardOpened(driver);
            ++already_swiped;
        }
    }

//    ****************************************** Left Swipe *************************************

    protected void swipeElementToLeft(By by, String text, WebDriver driver){
        WebElement element = waitForElementPresentImplicit(
                by,
                text,
                (AppiumDriver) driver,
                10);
        int left_x = element.getLocation().getX(); //the most left point on X
        int right_x = left_x + element.getSize().getWidth();//the most left point on X + element width;
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y) / 2;

        TouchAction action = new TouchAction((MobileDriver) driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
    protected int generateRandomForRegisterData(int number) {
        Random rand = new Random();
        int value = rand.nextInt(number);
        return  value;
    }
}
