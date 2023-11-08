package automation.ui.test;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import automation.ui.pages.*;

import java.io.IOException;

import static automation.ui.pages.TransactionPage.*;
import static org.testng.Assert.*;


public class TransactionTest extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    NotificationsPage notificationsPage;
    SignUpPage signUpPage;
    TransactionPage transactionPage;
    @BeforeTest
    void setupBrowser() throws IOException, NumberFormatException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, properties, wait);
        notificationsPage = new NotificationsPage(driver, properties);
        signUpPage = new SignUpPage(driver, properties, wait);
        transactionPage = new TransactionPage(driver, properties, wait);
        getBaseUrl();
        loginPage.testLogin();

    }
    @BeforeMethod
    void goToHomePage(){
        homePage.getHomeTab();
    }

    @Test(priority = 1)
    void assertElements() throws IOException {
        assertFalse(homePage.getTransactionList().isEmpty());
        homePage.getNewTransactionBtn().click();
        assertTrue(transactionPage.getSearchField().isDisplayed());
        assertTrue(transactionPage.getUsersList().isDisplayed());
        assertFalse(transactionPage.getlUsersItems().isEmpty());
        takeScreenShot("Transactions page assert elements");
    }
    @Test(priority = 2)
    void tryEmptyFields() throws IOException {
        homePage.getNewTransactionBtn().click();
        actualBalance = homePage.getUserAccBalance().getText();
        transactionPage.getSearchField().sendKeys("gerrard8");
        homePage.getUserAccBalance().click();
        transactionPage.getStivieG().click();
        assertTrue(transactionPage.getAmountField().isDisplayed());
        assertTrue(transactionPage.getAddNote().isDisplayed());
        assertFalse(transactionPage.getRequestBtn().isEnabled());
        assertFalse(transactionPage.getPayBtn().isEnabled());
        assertTrue(transactionPage.getUserTitle().isDisplayed());
        assertEquals(transactionPage.getTitleText(),"Steven Gerrard");
        transactionPage.getAmountField().click();
        transactionPage.getAddNote().click();
        assertTrue(transactionPage.amountHelperText().isDisplayed());
        assertEquals(transactionPage.getAmountHelperText(), "Please enter a valid amount");
        transactionPage.getAmountField().click();
        transactionPage.getAmountField().sendKeys(" ");
        assertTrue(transactionPage.getHelperTextOfNote().isDisplayed());
        assertEquals(transactionPage.getNoteHelperText(),"Please enter a note");
        transactionPage.getAddNote().sendKeys("  ");
        assertTrue(transactionPage.amountHelperText().isDisplayed());
        assertFalse(transactionPage.getPayBtn().isEnabled());
        assertFalse(transactionPage.getRequestBtn().isEnabled());
        takeScreenShot("transaction with empty fields");
    }
    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
