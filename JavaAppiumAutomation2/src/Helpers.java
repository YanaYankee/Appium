import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Helpers {

    public String createXPath(String str){

        String str_xpath = "//*[contains(@text, '"+ str +"')]";
        System.out.println(str_xpath);
        return str_xpath;
    }


    public WebElement findElementByName(AppiumDriver driver, String str){

       // return driver.findElementByXPath("//*[contains(@text, '"+ str +"')]"); //attention! do not use elementS, casts error
        return driver.findElementByXPath(createXPath(str));

    }

    public WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds, AppiumDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);

        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }

}