package automation.ui.test;

import automation.ui.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.io.IOException;

public class BankAccountTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    NotificationsPage notificationsPage;
    SignUpPage signUpPage;
    BankAccountsPage bankAccountsPage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, properties, wait);
        notificationsPage = new NotificationsPage(driver, properties);
        signUpPage = new SignUpPage(driver, properties, wait);
        bankAccountsPage = new BankAccountsPage(driver, properties, wait);
        getBaseUrl();
        loginPage.testLogin();
    }
    @BeforeMethod
    void gotoBaseUrl(){
        homePage.goToBankAccountPage();
        driver.manage().window().maximize();
    }
    @Step("Test case 01, Bank account")
    @Test(priority = 1, description = "Should check if all elements are displayed")
    void elementAssertation(){
        assertTrue(bankAccountsPage.getBankAccTitle().isDisplayed());
        assertEquals(bankAccountsPage.getBankAccTitleText(),"Bank Accounts");
        assertTrue(bankAccountsPage.getCreateNewAccBtn().isDisplayed());
        assertTrue(bankAccountsPage.getBankAccList().isDisplayed());
        assertFalse(bankAccountsPage.getBankAccListItems().isEmpty());
    }
    @Step("Test case 02, Bank account")
    @Test(priority = 2, description = "Should try to create bank account with empty fields")
    void emptyFieldsTest() throws IOException {
        bankAccountsPage.getCreateNewAccBtn().click();
        assertTrue(bankAccountsPage.getSaveBtn().isEnabled());
        assertTrue(bankAccountsPage.getInputBankName().isDisplayed());
        assertTrue(bankAccountsPage.getInputRoutinNumber().isDisplayed());
        assertTrue(bankAccountsPage.getInputAccNumber().isDisplayed());
        bankAccountsPage.getSaveBtn().click();
        assertTrue(bankAccountsPage.getBankPagePopUp().isDisplayed());
        bankAccountsPage.getInputBankName().click();
        bankAccountsPage.getInputRoutinNumber().click();
        assertTrue(bankAccountsPage.getBankNameHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getBankNameWarningText(),"Enter a bank name");
        bankAccountsPage.getInputAccNumber().click();
        assertTrue(bankAccountsPage.getRoutingNumberHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getRoutNumberWarningText(),"Enter a valid bank routing number");
        bankAccountsPage.getInputBankName().click();
        assertTrue(bankAccountsPage.getAccountNumberHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getAccNumberWarningText(),"Enter a valid bank account number");
        assertFalse(bankAccountsPage.getSaveBtn().isEnabled());
        takeScreenShot("Create account with empty fields");
    }
    @Step("Test case 03, Bank account")
    @Test(priority = 3, description = "Should try to create bank account with wrong credentials")
    void wrongCredentialsTest() throws IOException {
        bankAccountsPage.getCreateNewAccBtn().click();
        bankAccountsPage.getInputBankName().sendKeys(getRandomString(2));
        assertTrue(bankAccountsPage.getBankNameHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getBankNameWarningText(),"Must contain at least 5 characters");
        bankAccountsPage.getInputRoutinNumber().sendKeys(getRandomString(2));
        assertTrue(bankAccountsPage.getRoutingNumberHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getRoutNumberWarningText(),"Must contain a valid routing number");
        bankAccountsPage.getInputRoutinNumber().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        bankAccountsPage.getInputRoutinNumber().sendKeys(getRandomString(10));
        assertTrue(bankAccountsPage.getRoutingNumberHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getRoutNumberWarningText(),"Must contain a valid routing number");
        bankAccountsPage.getInputAccNumber().sendKeys(getRandomString(2));
        assertTrue(bankAccountsPage.getAccountNumberHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getAccNumberWarningText(),"Must contain at least 9 digits");
        bankAccountsPage.getInputAccNumber().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        bankAccountsPage.getInputAccNumber().sendKeys(getRandomString(13));
        assertTrue(bankAccountsPage.getAccountNumberHelperText().isDisplayed());
        assertEquals(bankAccountsPage.getAccNumberWarningText(),"Must contain no more than 12 digits");
        assertFalse(bankAccountsPage.getSaveBtn().isEnabled());
        takeScreenShot("wrong credentials test");

    }
    @Step("Test case 04, Bank account")
    @Test(priority = 4, description = "Try to save new account with all blank spaces")
    //passed, but should be prevented
    void allBlankSpacesTest() throws IOException {
        bankAccountsPage.getCreateNewAccBtn().click();
        bankAccountsPage.getInputBankName().sendKeys(getNSpaces(5));
        bankAccountsPage.getInputRoutinNumber().sendKeys(getNSpaces(9));
        bankAccountsPage.getInputAccNumber().sendKeys(getNSpaces(9));
        assertTrue(bankAccountsPage.getSaveBtn().isEnabled());
        //bankAccountsPage.getSaveBtn().click();
        //this bellow appears only when first bank account is created
        //assertTrue(bankAccountsPage.getFinishedDialog().isDisplayed());
        //assertTrue(bankAccountsPage.getDoneBankAccBtn().isDisplayed());
        //bankAccountsPage.getDoneBankAccBtn().click();
        //assertTrue(bankAccountsPage.getBankAccList().isDisplayed());
        takeScreenShot("Create bank account with all blank fields");
    }
    @Step("Test case 05, Bank account")
    @Test(priority = 5, description = "Should create new account with valid data")
    void createValidBankAccount() throws IOException {
        bankAccountsPage.getCreateNewAccBtn().click();
        bankAccountsPage.getInputBankName().sendKeys("Banka za Test"+getRandomString(3));
        bankAccountsPage.getInputRoutinNumber().sendKeys("120123120");
        bankAccountsPage.getInputAccNumber().sendKeys("230230230");
        bankAccountsPage.getSaveBtn().click();
        assertTrue(bankAccountsPage.getBankAccList().isDisplayed());
        assertTrue(bankAccountsPage.getLastItem().getText().contains("Banka za Test"));
        takeScreenShot("Create bank account with valid data");
    }
    @Step("Test case 06, Bank account")
    @Test(priority = 6, description = "Should delete bank account")
    void deleteBankAccount() throws IOException {
        bankAccountsPage.createTestBankAcc();
        assertTrue(bankAccountsPage.getBankAccList().isDisplayed());
        assertTrue(bankAccountsPage.getLastItem().getText().contains("Banka za Test"));
        bankAccountsPage.deleteLastAccBtn().click();
        assertTrue(bankAccountsPage.getLastItem().isDisplayed());
        takeScreenShot("Delete bank account");
    }
    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
