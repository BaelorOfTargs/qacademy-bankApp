package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class SignUpPage extends BaseTest{
    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;
    private By logoText = By.xpath("//a[@href='https://qacademy.rs']");
    private By signUpText = By.xpath("//*[h1]");
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By confirmPassword = By.id("confirmPassword");
    private By singUpBtn = By.cssSelector("data-test='signup-submit'");
    private By haveAccount = By.linkText("Have an account? Sign In");
    private By logoQA = By.xpath("//a[@href='https://qacademy.rs']");

    public SignUpPage(WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }

    public WebElement getSignUpText(){
        return driver.findElement(signUpText);
    }
    public WebElement getLogoText() {
        return driver.findElement(logoText);
    }
    public WebElement getFirstName(){
        return driver.findElement(firstName);
    }
    public void setFirstname(String firstname) {
        driver.findElement(firstName).sendKeys(firstname);
    }
    public WebElement getLastName() {
        return driver.findElement(lastName);
    }
    public void setLastname(String lastname) {
        driver.findElement(lastName).sendKeys(lastname);
    }
    public WebElement getUsername(){
        return driver.findElement(usernameField);
    }
    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }
    public WebElement getPassword(){
        return driver.findElement(passwordField);
    }
    public void setPassword(String password) {
        WebElement passwordField = this.getPassword();
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public WebElement getConfirmPassword(){
        return driver.findElement(confirmPassword);
    }
    public void setConfirmPassword(String confirmPass) {
        WebElement confirmPasswordField = this.getConfirmPassword();
        confirmPasswordField.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        confirmPasswordField.sendKeys(confirmPass);
    }


    public WebElement getSingUpBtn() {
        return driver.findElement(By.xpath("//button"));
    }
    public WebElement getFirstNameHelperText(){
        return driver.findElement(By.id("firstName-helper-text"));
    }
    public WebElement getLastNameHelperText(){
        return driver.findElement(By.id("lastName-helper-text"));
    }
    public WebElement getusernameHelperText(){
        return driver.findElement(By.id("username-helper-text"));
    }
    public WebElement getPasswordHelperText(){
        return driver.findElement(By.id("password-helper-text"));
    }
    public String getPassRequiredHelpText(){
        String passRequiredText = getPasswordHelperText().getText();
        return passRequiredText;
    }
    public String getPassMatchingHelpText(){
        String passMatchingText = getConfirmPassHelperText().getText();
        return passMatchingText;
    }

    public WebElement getConfirmPassHelperText(){
        return driver.findElement(By.id("confirmPassword-helper-text"));
    }

    public void clickSignUpBtn() {
        driver.findElement(singUpBtn).click();
    }

    public void createNewUser(){
        getFirstName().sendKeys(getRandomString(7));
        getLastName().sendKeys(getRandomString(7));
        getUsername().sendKeys(getRandomString(7));
        getPassword().sendKeys(getNCharacters(6));
        getConfirmPassword().sendKeys(getNCharacters(6));
        getSingUpBtn().click();
    }
}
