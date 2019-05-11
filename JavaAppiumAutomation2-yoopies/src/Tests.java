import test.lib.java.CoreTestCase;
import test.lib.java.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;


public class Tests extends CoreTestCase {

private MainPageObject MainPageObject;
protected void setUp() throws Exception{
    super.setUp();
    MainPageObject = new MainPageObject(driver);
}


    // Test: User logins and then logs out.
    @Test
    public void testRegisterAndMySituationSettings() {
        SignInSignUpPageObject SignInSignUpPageObject = new SignInSignUpPageObject(driver);
        SignInSignUpPageObject.initSignInSignUpScreen();
        SignInSignUpPageObject.initRegisterScreen();


        RegisterTopOfFirstScreenPageObject RegisterTopOfFirstScreenPageObject = new RegisterTopOfFirstScreenPageObject(driver);
        RegisterTopOfFirstScreenPageObject.initRegisterEmailInputSendKeys();
        RegisterTopOfFirstScreenPageObject.initRegisterNameInputSendKeys();


        RegisterBottomOfFirstScreenPageObject RegisterBottomOfFirstScreenPageObject = new RegisterBottomOfFirstScreenPageObject(driver);
        RegisterBottomOfFirstScreenPageObject.swipeToInitRegisterBottomScreen();


        RegisterTopOfFirstScreenPageObject.initRegisterLastNameInputSendKeys();
        RegisterBottomOfFirstScreenPageObject.initRegisterPasswordInputSendKeys();

        NextBtnPageObject NextBtnPageObject = new NextBtnPageObject(driver);
        NextBtnPageObject.nextButtonClick();


        RegisterSecondScreenPageObject RegisterSecondScreenPageObject = new RegisterSecondScreenPageObject(driver);
        RegisterSecondScreenPageObject.initRegisterAddressInputSendKeys();
        RegisterSecondScreenPageObject.initRegisterNoSuchAddressClick();



        CountriesSpinner CountriesSpinner = new CountriesSpinner(driver);
        CountriesSpinner.initCountriesSpinnerObject();
        CountriesSpinner.SelectCountry();
        CountriesSpinner.AcceptButtonClick();

        RegisterSecondScreenPageObject.initRegisterAddressInputSendKeys();
        RegisterSecondScreenPageObject.initRegisterConfirmAddress();
        RegisterSecondScreenPageObject.initRegisterPhoneCodeSelector();
        CountriesSpinner.SelectCountryCode();
        RegisterSecondScreenPageObject.initRegisterPhoneNumberInputSendKeys();
        RegisterSecondScreenPageObject.RegisterButtonClick();

        IntroPageObject IntroPageObject = new IntroPageObject(driver);
        IntroPageObject.initSkipIntroAction();

        BottomMenuPageObject BottomMenuPageObject = new BottomMenuPageObject(driver);
        BottomMenuPageObject.initMoreBtnClickAction();

        // ********************************* swipeUp ************************************

        MainPageObject.swipeUp(1000);

        LogOutArrowPageObject LogOutArrowPageObject = new LogOutArrowPageObject(driver);
        LogOutArrowPageObject.initLogOutArrowClickAction();

        LogOutArrowPageObject.initLogOutArrowClickAction();

    }


    // Test: User logins and then logs out.
    @Test
    public void testLoginLogoutTest()
    {


        SignInSignUpPageObject SignInSignUpPageObject = new SignInSignUpPageObject(driver);
        SignInSignUpPageObject.initSignInSignUpScreen();
        SignInSignUpPageObject.initLoginScreen();

        LoginPageObject LoginPageObject = new LoginPageObject(driver);
        LoginPageObject.initSendKeysToEmailAction();
        LoginPageObject.initSendKeysToPasswordAction();
        LoginPageObject.initFinishLoginInProcessAction();

        IntroPageObject IntroPageObject = new IntroPageObject(driver);
        IntroPageObject.initSkipIntroAction();

        BottomMenuPageObject BottomMenuPageObject = new BottomMenuPageObject(driver);
        BottomMenuPageObject.initMoreBtnClickAction();

        // ********************************* swipeUp ************************************

        MainPageObject.swipeUp(1000);

        LogOutArrowPageObject LogOutArrowPageObject = new LogOutArrowPageObject(driver);
        LogOutArrowPageObject.initLogOutArrowClickAction();

        LogOutArrowPageObject.initLogOutArrowClickAction();

    }
}