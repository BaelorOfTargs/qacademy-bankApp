package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
public class MyAccountPage {
    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;

    public MyAccountPage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }

    By usersettingsTitle = By.xpath("//*[text()='User Settings']");
    By userSettingsForm = By.cssSelector("[data-test='user-settings-form']");
    By firstNameInput = By.id("user-settings-firstName-input");
    By lastNameInput = By.id("user-settings-lastName-input");
    By emailInput = By.id("user-settings-email-input");
    By phoneNumberInput = By.id("user-settings-phoneNumber-input");
    By saveAccountBtn = By.cssSelector("[data-test='user-settings-submit']");

    public WebElement getUserSettingsTitle(){
        return driver.findElement(usersettingsTitle);
    }
    public String getUserSettingsTitleText(){
        String titleText = driver.findElement(usersettingsTitle).getText();
        return titleText;
    }
    public String getMyAccTitleText(){
        String realMyAccText = this.properties.getProperty("MY_ACC_TITLE_TEXT");
        return realMyAccText;
    }
    public WebElement getUserSettingsForm(){
        return driver.findElement(userSettingsForm);
    }
    public WebElement getFirstNameInput(){
        return driver.findElement(firstNameInput);
    }
    public String inputFirstnameValue(){
        String value = getFirstNameInput().getAttribute("value");
        return value;
    }
    public WebElement getLastNameInput(){
        return driver.findElement(lastNameInput);
    }
    public String inputLastnameValue(){
        String value = getLastNameInput().getAttribute("value");
        return value;
    }
    public WebElement getEmailInput(){
        return driver.findElement(emailInput);
    }
    public String inputEmailValue(){
        String value = getEmailInput().getAttribute("value");
        return value;
    }
    public WebElement getPhoneNumberInput(){
        return driver.findElement(phoneNumberInput);
    }
    public String inputPhoneNumberValue(){
        String value = getPhoneNumberInput().getAttribute("value");
        return value;
    }
    public WebElement getSaveBtn(){
        return driver.findElement(saveAccountBtn);
    }

}
