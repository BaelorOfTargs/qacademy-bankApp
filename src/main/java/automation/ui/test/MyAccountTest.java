package automation.ui.test;

import org.testng.annotations.*;
import automation.ui.pages.*;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class MyAccountTest extends BaseTest{
    MyAccountPage myAccountPage;
    LoginPage loginPage;
    HomePage homePage;
    NotificationsPage notificationsPage;
    SignUpPage signUpPage;
    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, properties, wait);
        notificationsPage = new NotificationsPage(driver, properties);
        signUpPage = new SignUpPage(driver, properties, wait);
        myAccountPage = new MyAccountPage(driver,properties,wait);
    }
    @BeforeMethod
    void gotoBaseUrl(){
        getBaseUrl();
        driver.manage().window().maximize();
        loginPage.correctLogin();
        homePage.goToAccountPage();
    }
    @AfterMethod
    void logOutUser(){
        homePage.logOut();
    }
    @Test
    void elementsAssert(){
        assertTrue(myAccountPage.getUserSettingsTitle().isDisplayed());
        assertTrue(myAccountPage.getUserSettingsForm().isDisplayed());
        assertTrue(myAccountPage.getFirstNameInput().isDisplayed());
        assertTrue(myAccountPage.getLastNameInput().isDisplayed());
        assertTrue(myAccountPage.getEmailInput().isDisplayed());
        assertTrue(myAccountPage.getPhoneNumberInput().isDisplayed());
        assertEquals(myAccountPage.getUserSettingsTitleText(),myAccountPage.getMyAccTitleText());
    }
    @Test
    void textValueAssert(){
        assertEquals(myAccountPage.inputFirstnameValue(),properties.getProperty("SETTINGS_FIRST_NAME"));
        assertEquals(myAccountPage.inputLastnameValue(), properties.getProperty("SETTINGS_LAST_NAME"));
        assertEquals(myAccountPage.inputEmailValue(),properties.getProperty("SETTINGS_EMAIL"));
        assertEquals(myAccountPage.inputPhoneNumberValue(), properties.getProperty("SETTINGS_PHONE_NUMBER"));
    }
    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
