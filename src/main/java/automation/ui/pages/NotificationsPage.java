package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class NotificationsPage {
    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;

    public NotificationsPage(WebDriver driver, Properties properties){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }

    By notificationsTitle = By.xpath("//*[text()='Notifications']");
    By notificationItems = By.cssSelector("[data-test*='notification-list-item']");
    By notificationsCounter = By.cssSelector("[class*='anchorOriginTopRightRectangle']");
    By notificationsList = By.cssSelector("[data-test*='notification-list-item']");

    public WebElement getNotificationsTitle(){
        return driver.findElement(notificationsTitle);
    }
    public List<WebElement> getNotificationList(){
        return driver.findElements(notificationItems);
    }
    public List<WebElement> getNotificationItems(){
        return driver.findElements(notificationsList);
    }
    public int getNotifListSize(){
        int listSize = getNotificationItems().size();
        return listSize;
    }

    public WebElement getNotificationsCounter() {
        return driver.findElement(notificationsCounter);
    }

    public int getNotificationValue(){
        int counter = Integer.parseInt(driver.findElement(notificationsCounter).getText());
        return counter;
    }
}
