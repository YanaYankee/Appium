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


public class Helpers {

    public String createXPath(String str){

        String str_xpath = "//*[contains(@text, '"+ str +"')]";
        // in the expression // means whatever nesting of an element, * whatever element,
        // contains - search for approximate coincidence,
        System.out.println(str_xpath);
        return str_xpath;
    }
    public String createXPathResourceId(String className,String text){

        String str_xpathResourceId = "//*[@resource-id='"+ className +"']//*[@text='"+ text +"']";
        // in the expression // means whatever nesting of an element, * whatever element,
        // contains - search for approximate coincidence,
        System.out.println(str_xpathResourceId);
        return str_xpathResourceId;
    }

    public WebElement findElementByName(AppiumDriver driver, String str){

       // return driver.findElementByXPath("//*[contains(@text, '"+ str +"')]"); //attention! do not use elementS, casts error
        return driver.findElementByXPath(createXPath(str));

    }

    public WebElement waitForElementPresent(By by, String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");


        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

//method reloading (5 secs timeout by default, add another number if needed within call)
    public WebElement waitForElementPresent(By by, String error_message, AppiumDriver driver)
    {
        return waitForElementPresent(by, error_message, driver, 5);
    }


    public WebElement waitForElementAndClick(By by, String error_message, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  driver,  timeoutInSeconds);
        element.click();
        return element;
    }


    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message, driver, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean WaitForElementNotPresent(By by,String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");


        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement WaitForElementAndClear(By by,String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebElement element = waitForElementPresent( by,  error_message, driver, timeoutInSeconds);
        element.clear();
        return element;
    }
    public WebElement TextExistanceAssert(By by,String error_message, AppiumDriver driver, long timeoutInSeconds, String text) {
        WebElement element = waitForElementPresent( by ,error_message, driver, timeoutInSeconds ) ;

        element.getAttribute("text");
        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                text
        );
        return element;
    }

    protected void swipeUp(int timeOfSwipe, WebDriver driver) {
        TouchAction action = new TouchAction((MobileDriver) driver);
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
}
