package automation.ui.test;

import automation.ui.pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static automation.ui.pages.TransactionPage.*;
import static org.testng.Assert.*;

public class TransactionFlowTest extends BaseTest {
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
    void createRequestTransaction() throws IOException {
        homePage.getNewTransactionBtn().click();
        transactionPage.getSearchField().sendKeys("gerrard8");
        homePage.getUserAccBalance().click();
        transactionPage.getStivieG().click();
        assertTrue(transactionPage.getAmountField().isDisplayed());
        assertTrue(transactionPage.getAddNote().isDisplayed());
        assertFalse(transactionPage.getRequestBtn().isEnabled());
        assertFalse(transactionPage.getPayBtn().isEnabled());
        assertTrue(transactionPage.getUserTitle().isDisplayed());
        assertEquals(transactionPage.getTitleText(),"Steven Gerrard");
        transactionPage.getAmountField().sendKeys(String.valueOf(testMoney));
        transactionPage.getAddNote().sendKeys("test pare");
        assertTrue(transactionPage.getPayBtn().isEnabled());
        assertTrue(transactionPage.getRequestBtn().isEnabled());
        transactionPage.getRequestBtn().click();
        assertTrue(transactionPage.alertMessage().isDisplayed());
        assertEquals(transactionPage.getAlertMsg(),"Transaction Submitted!");
        homePage.logOut();
        loginPage.gerrardLogin();
        homePage.getMineTab().click();
        transactionPage.getFirstTransaction().click();
        transactionPage.getAcceptReqBtn().click();
        assertTrue(transactionPage.getChargedMsg().contains("charged"));
        homePage.logOut();
        loginPage.testLogin();
        newBalance = homePage.getUserAccBalance().getText().replace("$","");
        newBalance.replaceAll(",","");
        new1 = Double.parseDouble(newBalance.replaceAll(",",""));
        actualBalance = homePage.getUserAccBalance().getText();
        actualBalance.replaceAll("$","");
        actualBalance.replaceAll(",", "");
        expectedRequestMoney = new1-testMoney;
        assertEquals(homePage.getUserAccBalance().getText().replace("$",""),newBalance);
        double transactionRequestDone = expectedRequestMoney+testMoney;
        assertEquals(transactionRequestDone,new1);
        takeScreenShot("Create request transaction");
    }
    @Test(priority = 2)
    void createPayTransaction() throws IOException {
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
        transactionPage.getAmountField().sendKeys(String.valueOf(testMoney));
        transactionPage.getAddNote().sendKeys("test pare");
        assertTrue(transactionPage.getPayBtn().isEnabled());
        assertTrue(transactionPage.getRequestBtn().isEnabled());
        transactionPage.getPayBtn().click();
        assertTrue(transactionPage.getPaidMsg().contains("Paid"));
        assertTrue(transactionPage.getReturnToTransactionsBtn().isDisplayed());
        assertTrue(transactionPage.getCreateAnotherTrnsactionBtn().isDisplayed());
        homePage.logOut();
        loginPage.gerrardLogin();
        newBalance = homePage.getUserAccBalance().getText().replace("$","");
        new1 = Double.parseDouble(newBalance.replaceAll(",",""));
        actualBalance = homePage.getUserAccBalance().getText();
        actualBalance.replaceAll("$","");
        actualBalance.replaceAll(",", "");
        expectedPayMoney = new1-testMoney;
        assertEquals(homePage.getUserAccBalance().getText().replace("$",""),newBalance);
        double transactionPayDone = expectedPayMoney+testMoney;
        assertEquals(transactionPayDone,new1);
        takeScreenShot("Create pay transaction");
    }
    @Test(priority = 3)
    void amountBiggerThanAccountBalance() throws IOException {
        homePage.logOut();
        loginPage.testLogin();
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
        transactionPage.getAmountField().sendKeys(String.valueOf(bigMoney));
        transactionPage.getAddNote().sendKeys("test pare");
        assertTrue(transactionPage.getPayBtn().isEnabled());
        assertTrue(transactionPage.getRequestBtn().isEnabled());
        transactionPage.getPayBtn().click();
        assertTrue(transactionPage.getPaidMsg().contains("Paid"));
        assertTrue(transactionPage.getReturnToTransactionsBtn().isDisplayed());
        assertTrue(transactionPage.getCreateAnotherTrnsactionBtn().isDisplayed());
        homePage.getHomeTab();
        newBalance = homePage.getUserAccBalance().getText().replace("$","");
        new1 = Double.parseDouble(newBalance.replaceAll(",",""));
        actualBalance = homePage.getUserAccBalance().getText();
        actualBalance.replaceAll("$","");
        actualBalance.replaceAll(",", "");
        expectedPayMoney = new1-testMoney;
        assertEquals(homePage.getUserAccBalance().getText().replace("$",""),newBalance);
        double transactionPayDone = expectedPayMoney+testMoney;
        assertEquals(transactionPayDone,new1);
        homePage.logOut();
        loginPage.gerrardLogin();
        newBalance = homePage.getUserAccBalance().getText().replace("$","");
        new1 = Double.parseDouble(newBalance.replaceAll(",",""));
        actualBalance = homePage.getUserAccBalance().getText();
        actualBalance.replaceAll("$","");
        actualBalance.replaceAll(",", "");
        expectedPayMoney = new1-bigMoney;
        assertEquals(homePage.getUserAccBalance().getText().replace("$",""),newBalance);
        transactionPayDone = expectedPayMoney+bigMoney;
        assertEquals(transactionPayDone,new1);
        homePage.getNewTransactionBtn().click();
        transactionPage.getSearchField().sendKeys("lukaku9");
        homePage.getUserAccBalance().click();
        transactionPage.getDodyL().click();
        transactionPage.getAmountField().sendKeys(String.valueOf(5000));
        transactionPage.getAddNote().sendKeys("test pare");
        transactionPage.getPayBtn().click();
        transactionPage.getCreateAnotherTrnsactionBtn().click();
        transactionPage.getSearchField().sendKeys("mitar9");
        homePage.getUserAccBalance().click();
        transactionPage.getMitar9().click();
        transactionPage.getAmountField().sendKeys(String.valueOf(45000));
        transactionPage.getAddNote().sendKeys("test pare");
        transactionPage.getPayBtn().click();
        takeScreenShot("AmountBiggerThanAccountBalance");

    }
    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
