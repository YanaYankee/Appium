public class Helpers {

    import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;


    public class Helpers {


        public WebElement findElementByName(AppiumDriver driver, String str){

            return driver.findElementByXPath("//*[contains(@text, '"+ str +"')]"); //attention! do not use elementS, casts error
        }
    }
}
