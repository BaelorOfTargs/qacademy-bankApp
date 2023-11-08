package automation.ui.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class TransactionPage {
    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;

    public static double testMoney = 10;
    public static double bigMoney = 50000;
    public static String actualBalance;
    public static String newBalance;
    public static double expectedRequestMoney;
    public static double new1;
    public static double expectedPayMoney;

    public TransactionPage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
        }
        //By searchField = By.id("user-list-search-input");
        By searchField = By.cssSelector("[data-test='user-list-search-input']");
        By amountField = By.id("amount");
        By amountFieldHelperText = By.id("transaction-create-amount-input-helper-text");
        By addNote = By.id("transaction-create-description-input");
        By noteHelperText = By.id("transaction-create-description-input-helper-text");
        By requestBtn = By.cssSelector("[data-test='transaction-create-submit-request']");
        By payBtn = By.cssSelector("[data-test='transaction-create-submit-payment']");
        By usersList = By.cssSelector("[data-test='users-list']");
        By usersListItems = By.cssSelector("[data-test*='user-list-item']");
        By firstUserInList = By.xpath("//*[@data-test='users-list']/li[1]");
        By gerrardSelector = By.cssSelector("[data-test*='user-list-item-W84Rv_LFU']");
        By mitarSelector = By.cssSelector("[data-test*='user-list-item-VSK2CHA0T']");
        By dodySelector = By.cssSelector("[data-test*='user-list-item-OATFpujFF']");
        By userTitle = By.cssSelector("div:nth-child(2) > h2");
        By returnToTransactionsBtn = By.cssSelector("[data-test='new-transaction-return-to-transactions']");
        By createNewTransactionsBtn = By.cssSelector("[data-test='new-transaction-create-another-transaction']");
        By firstTransaction = By.xpath("//*[@data-test='transaction-list']/div[1]/div[1]/div[1]/li[1]");
        By alertMSG = By.className("MuiAlert-message");
        By acceptRequest = By.xpath("//*[span[text()='Accept Request']]");
        By rejectRequest = By.xpath("//*[span[text()='Reject Request']]");
        By chargedMsg = By.xpath("//*[span[text()=' charged ']]");
        By paidMsg = By.xpath("//*[h2[text()='Paid ']]");
        public String getChargedMsg(){
            String text = driver.findElement(chargedMsg).getText();
            return text;
        }
        public String getPaidMsg(){
            String text = driver.findElement(paidMsg).getText();
            return text;
        }
        public WebElement getAcceptReqBtn(){
            return driver.findElement(acceptRequest);
        }
        public WebElement getRejectRequest(){
            return driver.findElement(rejectRequest);
        }
        public WebElement getFirstTransaction(){
            return driver.findElement(firstTransaction);
        }
        public WebElement getReturnToTransactionsBtn(){
            return driver.findElement(returnToTransactionsBtn);
        }
        public WebElement getCreateAnotherTrnsactionBtn(){
            return driver.findElement(createNewTransactionsBtn);
        }
        public WebElement getStivieG(){
            return driver.findElement(gerrardSelector);
        }
        public WebElement getMitar9(){
            return driver.findElement(mitarSelector);
    }
        public WebElement getDodyL(){
            return driver.findElement(dodySelector);
        }
        public WebElement getUserTitle(){
            return driver.findElement(userTitle);
        }
        public String getTitleText(){
            String text = getUserTitle().getText();
            return text;
        }
        public WebElement getFirstUser(){
            return driver.findElement(firstUserInList);
        }

        public WebElement getSearchField(){
            return driver.findElement(searchField);
        }
        public WebElement getAmountField(){
            return driver.findElement(amountField);
        }
        public WebElement amountHelperText(){
            return driver.findElement(amountFieldHelperText);
        }
        public String getAmountHelperText(){
            String text = amountHelperText().getText();
            return text;
        }
        public WebElement getAddNote(){
            return driver.findElement(addNote);
        }
        public WebElement getHelperTextOfNote(){
            return driver.findElement(noteHelperText);
        }
    public String getNoteHelperText(){
        String text = getHelperTextOfNote().getText();
        return text;
    }
        public WebElement getRequestBtn(){
            return driver.findElement(requestBtn);
        }
        public WebElement getPayBtn(){
            return driver.findElement(payBtn);
        }
        public WebElement getUsersList(){
            return driver.findElement(usersList);
        }
        public List<WebElement> getlUsersItems(){
            return driver.findElements(usersListItems);
        }

        public WebElement alertMessage(){
            return driver.findElement(alertMSG);
        }
        public String getAlertMsg(){

            String text = alertMessage().getText();
            return text;
        }
}
