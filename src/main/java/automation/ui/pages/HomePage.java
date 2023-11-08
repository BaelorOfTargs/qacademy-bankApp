package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;
public class HomePage {
    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;

    public HomePage(WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }
     By logoText = By.cssSelector("[data-test='app-name-logo']");
     By newTrnsactionBtn = By.cssSelector("[data-test='nav-top-new-transaction']");
     By notificationsCounter = By.cssSelector("[data-test='nav-top-notifications-link']");
     By hamburgerMenu = By.cssSelector("[data-test='sidenav-toggle']");
     By userFullName = By.cssSelector("[data-test='sidenav-user-full-name']");
     By sidenavUserName = By.cssSelector("[data-test='sidenav-username']");
     By userAccBalance = By.cssSelector("[data-test='sidenav-user-balance']");
     By accountBalanceText = By.xpath("//*[@data-test='sidenav-user-balance']/following-sibling::*");
     By homeTab = By.cssSelector("[data-test='sidenav-home']");
     By myAccountTab = By.cssSelector("[data-test='sidenav-user-settings']");
     By bankAccountsTab = By.cssSelector("[data-test='sidenav-bankaccounts']");
     By notificationsTab = By.cssSelector("[data-test='sidenav-notifications']");
     By logOutTab = By.cssSelector("[data-test='sidenav-signout']");
     By everyoneTab = By.cssSelector("[data-test='nav-public-tab']");
     By friendsTab = By.cssSelector("[data-test='nav-contacts-tab']");
     By mineTab = By.cssSelector("[data-test='nav-personal-tab']");
     By dateRange = By.cssSelector("[data-test='transaction-list-filter-date-range-button']");
     By amountRange = By.cssSelector("[data-test='transaction-list-filter-amount-range-button']");
     //By transactionList = By.cssSelector("[data-test='transaction-item']");
     By transactionList = By.cssSelector("[data-test='transaction-list']");
     By logoQA = By.xpath("//a[@href='https://qacademy.rs']");

    public WebElement getLogotext(){
        return driver.findElement(logoText);
    }
    public WebElement getNewTransactionBtn(){
        return driver.findElement(newTrnsactionBtn);
    }

    public WebElement getNotificationsCounter() {
        return driver.findElement(notificationsCounter);
    }
    public WebElement getHamburgerMenu(){
        return driver.findElement(hamburgerMenu);
    }
    public WebElement getUserFullName(){
        return driver.findElement(userFullName);
    }
    public String getFullNameText(){
        String fullname = driver.findElement(userFullName).getText();
        return fullname;
    }
    public String getFullnameTitle(){
        String fullNameTitle = this.properties.getProperty("FULLNAME");
        return fullNameTitle;
    }

    public WebElement getSidenavUserName(){
        return driver.findElement(sidenavUserName);
    }
    public String getUsernameText(){
        String usernameText = driver.findElement(sidenavUserName).getText();
        return usernameText;
    }
    public String usernameTitle(){
        String title = this.properties.getProperty("USERNAME_TITLE");
        return title;
    }
    public WebElement getUserAccBalance(){
        return driver.findElement(userAccBalance);
    }
    public WebElement getAccountBalanceText(){
        return driver.findElement(accountBalanceText);
    }
    public String accBalanceText(){
        String balanceText = driver.findElement(accountBalanceText).getText();
        return balanceText;
    }
    public WebElement getHomeTab(){
        return driver.findElement(homeTab);
    }
    public WebElement getEveryoneTab(){
        return driver.findElement(everyoneTab);
    }
    public WebElement getMyAccTab(){
        return driver.findElement(myAccountTab);
    }
    public WebElement getBankAccTab(){
        return driver.findElement(bankAccountsTab);
    }
    public WebElement getNotificationsTab(){
        return driver.findElement(notificationsTab);
    }
    public WebElement getLogoutTab(){
        return driver.findElement(logOutTab);
    }

    public void logOut(){
        getLogoutTab().click();
    }
    public WebElement getFriendsTab(){
        return driver.findElement(friendsTab);
    }
    public WebElement getMineTab(){
        return driver.findElement(mineTab);
    }

    public WebElement getDateRange(){
        return driver.findElement(dateRange);
    }
    public WebElement getAmountRange(){
        return driver.findElement(amountRange);
    }
    /*public List<WebElement> getTransactionList(){
        return driver.findElements(transactionList);
    }*/
    public List<WebElement> getTransactionList(){
        return driver.findElements(transactionList);
    }
    public WebElement getLogoQA(){
        return driver.findElement(logoQA);
    }
    public void goToAccountPage(){
        getMyAccTab().click();
    }
    public void goToBankAccountPage(){
        getBankAccTab().click();
    }

}
