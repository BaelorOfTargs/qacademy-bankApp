package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

import static automation.ui.pages.BaseTest.getRandomString;

public class BankAccountsPage {
    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;

    public BankAccountsPage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }
    By bankAccountTitle = By.xpath("//*[text()='Bank Accounts']");
    By createBankAccountTitle = By.xpath("//*[text()='Create Bank Account']");
    By createNewAccBtn = By.cssSelector("[data-test='bankaccount-new']");
    By bankAccList = By.cssSelector("[data-test='bankaccount-list']");
    By bankAccListItems = By.cssSelector("[data-test*='bankaccount-list-item']");
    By lastitemText = By.xpath("//*[@data-test='bankaccount-list']/li[last()]/div/div/p");
    By deleteLastBankAcc = By.xpath("//*[@data-test='bankaccount-list']/li[last()]/div/div[2]/button");
    By inputBankName = By.name("bankName");
    By routingNumber = By.id("bankaccount-routingNumber-input");
    By accountNumber = By.id("bankaccount-accountNumber-input");
    By saveNewAccountBtn = By.cssSelector("[data-test='bankaccount-submit']");
    By bankPagePopUp = By.xpath("//*[@data-test='bankaccount-submit']/span[2]");
    By bankNameHelperText = By.id("bankaccount-bankName-input-helper-text");
    By routingNumberHelperText = By.id("bankaccount-routingNumber-input-helper-text");
    By accountNumberHelperText = By.id("bankaccount-accountNumber-input-helper-text");
    By finishedAccDialog = By.cssSelector("[data-test='user-onboarding-dialog-content']");
    By doneBankAccBtn = By.cssSelector("[data-test='user-onboarding-next']");
    By lastItemOfBankAccList = By.xpath("//*[@data-test='bankaccount-list']/li[last()]");
    By lastItemDeleted = By.xpath("//*[@data-test='bankaccount-list']/li[last()]/div/div/p/text()[last()]");
    public WebElement getFinishedDialog(){
        return driver.findElement(finishedAccDialog);
    }
    public WebElement getDoneBankAccBtn(){
        return driver.findElement(doneBankAccBtn);
    }

    public WebElement getBankNameHelperText(){
        return driver.findElement(bankNameHelperText);
    }
    public String getBankNameWarningText(){
        String text = getBankNameHelperText().getText();
        return text;
    }

    public String getAccNumberWarningText(){
        String text = getAccountNumberHelperText().getText();
        return text;
    }
    public WebElement getRoutingNumberHelperText(){
        return driver.findElement(routingNumberHelperText);
    }
    public String getRoutNumberWarningText(){
        String text = getRoutingNumberHelperText().getText();
        return text;
    }
    public WebElement getAccountNumberHelperText(){
        return driver.findElement(accountNumberHelperText);
    }
    public WebElement getBankPagePopUp(){
        return driver.findElement(bankPagePopUp);
    }
    public WebElement getSaveBtn(){
        return driver.findElement(saveNewAccountBtn);
    }

    public  WebElement getBankAccTitle(){
        return driver.findElement(bankAccountTitle);
    }
    public String getBankAccTitleText(){
        String text = getBankAccTitle().getText();
        return text;
    }
    public WebElement getCreateBankAccountTitle() {
        return driver.findElement(createBankAccountTitle);
    }
    public String getCreateBankAccountTitleText(){
        String createBAccTitleText = getCreateBankAccountTitle().getText();
        return createBAccTitleText;
    }

    public WebElement getCreateNewAccBtn(){
        return driver.findElement(createNewAccBtn);
    }
    public WebElement deleteLastAccBtn(){
        return driver.findElement(deleteLastBankAcc);
    }
    public WebElement deletedLastItem(){
        return driver.findElement(lastItemDeleted);
    }
    public String deleted(){
        String text = deletedLastItem().getText();
        return text;
    }
    public WebElement getBankAccList(){
        return driver.findElement(bankAccList);
    }
    public List<WebElement> getBankAccListItems(){
        return driver.findElements(bankAccListItems);
    }
    public WebElement getLastItem(){
        return getBankAccListItems().get(getBankAccListItems().size()-1);
    }
    public WebElement getLastListAccItem(){
        return driver.findElement(lastItemOfBankAccList);
    }

    public WebElement getInputBankName(){
        return driver.findElement(inputBankName);
    }
    public WebElement getInputRoutinNumber(){
        return driver.findElement(routingNumber);
    }
    public WebElement getInputAccNumber(){
        return driver.findElement(accountNumber);
    }
    public void createTestBankAcc(){
        getCreateNewAccBtn().click();
        getInputBankName().sendKeys("Banka za Test"+getRandomString(3));
        getInputRoutinNumber().sendKeys("120123120");
        getInputAccNumber().sendKeys("230230230");
        getSaveBtn().click();
    }

}
