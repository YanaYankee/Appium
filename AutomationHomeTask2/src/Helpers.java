import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;


public class Helpers {

    protected AppiumDriver driver;




//   ************************************************* WAITS  *************************


    public WebElement waitForElementPresent(By by, String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClick(By by, String error_message, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  driver,  timeoutInSeconds);
        element.click();
        return element;
    }


    public WebElement waitForElementAndClear(By by, String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebElement element = waitForElementPresent( by,  error_message, driver, timeoutInSeconds);

        element.clear();
        return element;
    }
    public boolean waitForElementNotPresent(By by, String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, AppiumDriver driver, long timeoutInSeconds)
    {

        WebElement element = waitForElementPresent( by,  error_message, driver, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public WebElement waitForElementAndSetValue(By by, String error_message, AppiumDriver driver, long timeoutInSeconds, String value) {
        MobileElement element = (MobileElement) waitForElementPresent( by,  error_message,  driver,  timeoutInSeconds);
        element.setValue(value);
        return element;
    }

    //   ************************************************* CHECK IF WORD IS PRESENT  *************************

    // public WebElement CheckElementsToHaveWord(driver, "resource-id", "org.wikipedia:id/page_list_item_title" , "Selenium")

    public void checkElementsToHaveWord(By by, WebDriver driver, String word){
     List<WebElement> rows = driver.findElements(by);

     Iterator<WebElement> iter = rows.iterator();

         while (iter.hasNext()) {
             // Iterate one by one
             WebElement item = iter.next();
             // get the text
             String label = item.getText().toLowerCase();
             //check if title contains "Word"
             Assert.assertThat(label, containsString(word.toLowerCase()));

         }
     }
    protected void swipeUp(int timeOfSwipe, WebDriver driver) {
        //   TouchAction action = new TouchAction((MobileDriver) driver);
        TouchAction action = new TouchAction((MobileDriver)driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int y_start = (int) (size.height * 0.8);
        int y_end = (int) (size.height * 0.2);

        action
                .press(x, y_start)
                .waitAction(timeOfSwipe)
                .moveTo(x, y_end)
                .release()
                .perform();
    }
    protected void swipeUpQuick(WebDriver driver) {
        swipeUp(200, driver);
    }

    protected void swipeUpToElement(By by, String error_message, WebDriver driver, int max_swipes){
//        driver.findElements(by);
//        driver.findElements(by).size();
        int already_swiped = 0;

        while(driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping. \n" + error_message, (AppiumDriver) driver, 0);
                return;
            }
            swipeUpQuick(driver);
            ++already_swiped;
        }
    }
    protected void swipeElementToLeft(By by, String error_message, WebDriver driver){
        WebElement element = waitForElementPresent(
                by,
                error_message,
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
    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements < 1 ) {
            String default_message = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        } else if (amount_of_elements > 1 ) {
            String default_message = "Not more than one element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0 ) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

public String waitForElementAndGetAttribute(By by, String attribute, WebDriver driver, String error_message, long timeOutInSeconds){

    WebElement element = waitForElementPresent(
            by,
            error_message,
            (AppiumDriver) driver,
            timeOutInSeconds);
        return element.getAttribute(attribute);
}
}

