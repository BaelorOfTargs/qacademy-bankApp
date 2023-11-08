package automation.ui.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import automation.ui.pages.*;

import java.io.IOException;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest{
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
    }
    @BeforeMethod
    void gotoBaseUrl(){
        getBaseUrl();
        driver.manage().window().maximize();
    }
    @Test(priority = 1,description = "Should check if all elements are displayed")
    void assertElements() throws IOException {
        assertTrue(loginPage.getRealWorldLogo().isDisplayed());
        assertTrue(loginPage.getSignInText().isDisplayed());
        assertTrue(loginPage.getUsername().isDisplayed());
        assertTrue(loginPage.getPassword().isDisplayed());
        assertTrue(loginPage.getRememberCB().isDisplayed());
        assertFalse(loginPage.getRememberCB().isSelected());
        assertTrue(loginPage.getLogoText().isDisplayed());
        assertTrue(loginPage.getSignUp().isDisplayed());
        assertTrue(loginPage.getLoginBtn().isEnabled());
        assertTrue(loginPage.getLogoQA().isDisplayed());
        takeScreenShot("LoginDisplayed");
    }
    @Test(priority = 2, description = "should try to log in with empty fields")
    void emptyFields() throws IOException {
        loginPage.getUsername().click();
        loginPage.getPassword().click();
        assertEquals(loginPage.getUsNameHelperText(), "Username is required");
        assertFalse(loginPage.getLoginBtn().isEnabled());
        takeScreenShot("LoginEmptyFields");
    }
    @Test(priority = 3,description = "should try to log in with wrong credentials")
    void wrongCredentialsTry() throws IOException {
        loginPage.setUsername(getNCharacters(2));
        loginPage.setPassword(getNCharacters(2));
        loginPage.getUsername().click();
        assertTrue(loginPage.getPasswordHelperText().isDisplayed());
        assertEquals(loginPage.getPassHelpText(), "Password must contain at least 4 characters");
        assertFalse(loginPage.getLoginBtn().isEnabled());
        loginPage.setPassword(getRandomString(4));
        assertTrue(loginPage.getLoginBtn().isEnabled());
        loginPage.getLoginBtn().click();
        assertTrue(loginPage.getWrongCredentialAlert().isDisplayed());
        assertEquals(loginPage.getWrongCredentialAlertText(),"Username or password is invalid");
        takeScreenShot("LoginWrongCredentials");
    }
    @Test(priority = 4,description = "Should login user into app")
    void correctLogin() throws InterruptedException, IOException {
        loginPage.correctLogin();
        assertTrue(homePage.getSidenavUserName().isDisplayed());
        assertEquals(homePage.getUsernameText(), properties.getProperty("USERNAME_TITLE"));
        takeScreenShot("LoginCorrect");
    }
    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
