import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
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

    public WebElement waitForElementPresentByXpath(String xpath, String error_message, AppiumDriver driver, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);

        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

//method reloading (5 secs timeout by default, add another number if needed within call)
    public WebElement waitForElementPresentByXpath(String xpath, String error_message, AppiumDriver driver)
    {
        return waitForElementPresentByXpath(xpath, error_message, driver, 5);
    }


    public WebElement waitForElementByXpathAndClick(String xpath, String error_message, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath( xpath,  error_message,  driver,  timeoutInSeconds);
        element.click();
        return element;
    }
    public WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, AppiumDriver driver, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath( xpath,  error_message, driver, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}
