package automation.ui.test;

import org.testng.annotations.*;
import automation.ui.pages.*;

import java.io.IOException;

import static org.testng.Assert.*;

public class HomePageTest extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    NotificationsPage notificationsPage;
    SignUpPage signUpPage;
    BankAccountsPage bankAccountPage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, properties, wait);
        notificationsPage = new NotificationsPage(driver, properties);
        signUpPage = new SignUpPage(driver, properties, wait);
        bankAccountPage = new BankAccountsPage(driver, properties, wait);
    }
    @BeforeMethod
    void gotoBaseUrl(){
        getBaseUrl();
        driver.manage().window().maximize();
        loginPage.correctLogin();
    }

    @Test(description = "Should check if all elements are displayed")
    void elementAssertation()throws IOException{
        assertTrue(homePage.getLogotext().isDisplayed());
        assertTrue(homePage.getNewTransactionBtn().isDisplayed());
        assertTrue(homePage.getNotificationsCounter().isDisplayed());
        assertTrue(homePage.getFriendsTab().isDisplayed());
        assertTrue(homePage.getMyAccTab().isDisplayed());
        assertTrue(homePage.getHomeTab().isDisplayed());
        assertTrue(homePage.getUserAccBalance().isDisplayed());
        assertTrue(homePage.getAccountBalanceText().isDisplayed());
        assertTrue(homePage.getUserFullName().isDisplayed());
        assertTrue(homePage.getSidenavUserName().isDisplayed());
        assertTrue(homePage.getAmountRange().isDisplayed());
        assertTrue(homePage.getDateRange().isDisplayed());
        assertTrue(homePage.getEveryoneTab().isDisplayed());
        assertTrue(homePage.getBankAccTab().isDisplayed());
        assertTrue(homePage.getHamburgerMenu().isDisplayed());
        assertTrue(homePage.getLogoQA().isDisplayed());
        assertTrue(homePage.getNotificationsTab().isDisplayed());
        assertTrue(homePage.getMineTab().isDisplayed());
        assertTrue(homePage.getLogoutTab().isDisplayed());
        assertFalse(homePage.getTransactionList().isEmpty());
        assertEquals(homePage.getUsernameText(),homePage.usernameTitle());
        assertEquals(homePage.getFullNameText(),homePage.getFullnameTitle());
        homePage.goToAccountPage();
        assertEquals(driver.getCurrentUrl(), properties.getProperty("MY_ACCOUNT_PAGE"));
        homePage.goToBankAccountPage();
        assertEquals(driver.getCurrentUrl(), properties.getProperty("BANK_ACCOUNT_PAGE"));
        takeScreenShot("Home page assert elements");
    }
    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
