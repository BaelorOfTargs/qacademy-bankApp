package automation.ui.test;


import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import automation.ui.pages.*;

import java.io.IOException;

import static org.testng.Assert.*;

public class NotificationsAssert extends BaseTest {
    NotificationsPage notificationsPage;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, properties, wait);
        notificationsPage = new NotificationsPage(driver, properties);
    }
    @BeforeMethod
    void gotoBaseUrl(){
        getBaseUrl();
        driver.manage().window().maximize();
        loginPage.correctLogin();
        homePage.getNotificationsTab().click();
    }

    @Test(description = "should assert notifications elements on the page")
    void assertElements() throws IOException {
        assertTrue(notificationsPage.getNotificationsTitle().isDisplayed());
        assertFalse(notificationsPage.getNotificationList().isEmpty());
        assertTrue(notificationsPage.getNotificationsCounter().isDisplayed());
        assertEquals(notificationsPage.getNotificationValue(),notificationsPage.getNotifListSize());
        takeScreenShot("notifications assert elements");
    }

    @AfterTest
    void clearAll() {
        this.tearDown();
    }
}
