
package test.lib.java.ui;

        import io.appium.java_client.AppiumDriver;
        import org.openqa.selenium.By;

public class IntroPageObject extends MainPageObject {
    private static final String
            SKIP_INIT_ELEMENT = "com.yoopies.babysittingandroid.beta:id/skip";


    public IntroPageObject(AppiumDriver driver) {
        super(driver);
    }
    public void initSkipIntroAction() {
    // ********************************* tap SKIP btn ************************************
        this.waitForElementAndClick(
            By.id(SKIP_INIT_ELEMENT),
            "Cannot find Skip btn after clicking loging connect init element",
            5);
        }
}
