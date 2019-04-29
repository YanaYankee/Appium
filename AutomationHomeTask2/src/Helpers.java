import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;


public class Helpers {

    public String createXPath(String str){

        String str_xpath = "//*[contains(@text, '"+ str +"')]";
        // in the expression // means whatever nesting of an element, * whatever element,
        // contains - search for approximate coincidence,
        System.out.println(str_xpath);
        return str_xpath;
    }
    public String createXPathSimp(String key, String value){

        String str_xpath = "//*[@" + key + "='"+ value +"']";
        System.out.println(str_xpath);
        return str_xpath;
    }
    public String createXPathKey(String key, String value, String text){

        String str_xpathKey = "//*[@" + key + "='"+ value +"']//*[@text='"+ text +"']";
        // in the expression // means whatever nesting of an element, * whatever element,
        // contains - search for approximate coincidence,
        System.out.println(str_xpathKey);
        return str_xpathKey;
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


    // public WebElement CheckElementsToHaveWord(driver, "resource-id", "org.wikipedia:id/page_list_item_title" , "Selenium")

    public void CheckElementsToHaveWord(WebDriver driver, String key, String value, String word){
     List<WebElement> rows = driver.findElements(By.xpath(createXPathSimp(key, value)));
//     List<WebElement> rowsDesc = driver.findElements(By.xpath(createXPathSimp(key, value)));

    // print the total number of elements
//     System.out.println("Titles are " + rows);
//     System.out.println("Total selected rows are " + rows.size());

     // Now using Iterator we will iterate all elements
     Iterator<WebElement> iter = rows.iterator();

     // this will check whether list has some element or not
     while (iter.hasNext()) {

         // Iterate one by one
         WebElement item = iter.next();

         // get the text
         String label = item.getText().toLowerCase();

         //check if title contains "Word"
         Assert.assertThat(label, containsString(word.toLowerCase()));

         // print the text
      //   System.out.println("Row label is " + label);
     }

     }




}
