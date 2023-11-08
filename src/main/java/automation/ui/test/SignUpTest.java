package automation.ui.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import automation.ui.pages.*;

import java.io.IOException;

import static org.testng.Assert.*;

public class SignUpTest extends BaseTest{
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
    void gotoSignUpUrl(){
        getSignUpUrl();
        driver.manage().window().maximize();
    }
    @Test(description = "Should check if all elements are displayed")
    void assertElements() throws IOException {
        assertTrue(signUpPage.getLogoText().isDisplayed());
        assertTrue(signUpPage.getSignUpText().isDisplayed());
        assertTrue(signUpPage.getFirstName().isDisplayed());
        assertTrue(signUpPage.getLastName().isDisplayed());
        assertTrue(signUpPage.getUsername().isDisplayed());
        assertTrue(signUpPage.getPassword().isDisplayed());
        assertTrue(signUpPage.getConfirmPassword().isDisplayed());
        assertTrue(signUpPage.getSingUpBtn().isEnabled());
        takeScreenShot("SignupDisplayed");
    }

    @Test(description = "Should try to login with all empty fields and assert error messages")
    void emptyFieldsTry() throws IOException {
        signUpPage.getFirstName().click();
        signUpPage.getLastName().click();
        signUpPage.getUsername().click();
        signUpPage.getPassword().click();
        signUpPage.getConfirmPassword().click();
        signUpPage.getLogoText().click();
        assertTrue(signUpPage.getFirstNameHelperText().isDisplayed());
        assertTrue(signUpPage.getLastNameHelperText().isDisplayed());
        assertTrue(signUpPage.getusernameHelperText().isDisplayed());
        assertTrue(signUpPage.getPasswordHelperText().isDisplayed());
        assertTrue(signUpPage.getConfirmPassHelperText().isDisplayed());
        assertFalse(signUpPage.getSingUpBtn().isEnabled());
        takeScreenShot("SignupErrorMsg");
    }
    @Test(description = "Should try to login with wrong credentials and assert error messages")
    void wrongCredentials() throws IOException {
        signUpPage.setFirstname(getNCharacters(3));
        signUpPage.setLastname(getNCharacters(3));
        signUpPage.setUsername(getNCharacters(3));
        signUpPage.setPassword(getNCharacters(2));
        assertTrue(signUpPage.getPasswordHelperText().isDisplayed());
        assertEquals(signUpPage.getPassRequiredHelpText(), "Password must contain at least 4 characters");
        signUpPage.setConfirmPassword(getNCharacters(2));
        //assertFalse(signUpPage.getConfirmPassHelperText().isDisplayed());
        signUpPage.setConfirmPassword(getRandomString(3));
        assertTrue(signUpPage.getConfirmPassHelperText().isDisplayed());
        assertEquals(signUpPage.getPassMatchingHelpText(), "Password does not match");
        assertTrue(signUpPage.getConfirmPassHelperText().isDisplayed());
        assertFalse(signUpPage.getSingUpBtn().isEnabled());
        takeScreenShot("SignupWrongCredentials");
    }

    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
