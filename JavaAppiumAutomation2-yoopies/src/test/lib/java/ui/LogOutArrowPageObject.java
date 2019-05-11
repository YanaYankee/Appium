package test.lib.java.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LogOutArrowPageObject  extends MainPageObject {
    private static final String
            LOG_OUT_ARROW_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/logOutArrow";


    public LogOutArrowPageObject(AppiumDriver driver){
        super(driver);
    }
    // ********************* Logout arrow  (on several screens) ************************************
    public void initLogOutArrowClickAction() {
        this.waitForElementAndClick(
                By.id( LOG_OUT_ARROW_INIT_ELEMENT),
                "Cannot find arrow logOutscreen",
                7);

    }

}