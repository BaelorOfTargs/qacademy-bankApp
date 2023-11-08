package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class LoginPage{
    protected WebDriver driver;
    protected Properties properties;
    private By logoText = By.xpath("//a[@href='https://qacademy.rs']");
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("[data-test='signin-submit']");
    private By realWorldLogo = By.xpath("//*[@class='jss3']");
    private By signInText = By.xpath("//*[h1]");
    private By rememberCB = By.cssSelector("[data-test=\"signin-remember-me\"]");
    private By signUp = By.cssSelector("[data-test='signup']");
    private By logoQA = By.xpath("//a[@href='https://qacademy.rs']");
    private By userNameHelpText = By.id("username-helper-text");
    private By passHelperText = By.id("password-helper-text");
    private By wrongCredentialsAlert = By.cssSelector("[data-test='signin-error']");

    public WebElement getSignUp(){
        return driver.findElement(signUp);
    }
    public LoginPage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }
    public WebElement getUsername(){
        return driver.findElement(usernameField);
    }
    public void setUsername(String username) {
        WebElement userNameField = this.getUsername();
        userNameField.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        userNameField.sendKeys(username);
    }
    public WebElement getPassword(){
        return driver.findElement(passwordField);
    }

    public void setPassword(String password) {
        WebElement passwordField = this.getPassword();
        passwordField.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        passwordField.sendKeys(password);
    }

    public WebElement getLogoText() {
        return driver.findElement(logoText);
    }
    public WebElement getRealWorldLogo(){
        return driver.findElement(realWorldLogo);
    }
    public WebElement getSignInText(){
        return driver.findElement(signInText);
    }
    public WebElement getRememberCB(){
        return driver.findElement(rememberCB);
    }
    public WebElement getLogoQA(){
        return driver.findElement(logoQA);
    }
    public void clickLoginBtn() {
        driver.findElement(loginButton).click();
    }
    public WebElement getLoginBtn() {
        return driver.findElement(loginButton);
    }
    public WebElement getUsernameHelperText(){
        return driver.findElement(userNameHelpText);
    }
    public WebElement getPasswordHelperText(){
        return driver.findElement(passHelperText);
    }
    public WebElement getWrongCredentialAlert(){
        return driver.findElement(wrongCredentialsAlert);
    }
    public String getUsNameHelperText() {
        String text = getUsernameHelperText().getText();
        return text;
    }
    public String getPassHelpText(){
        String text = getPasswordHelperText().getText();
        return text;
    }
    public String getWrongCredentialAlertText(){
        String text = getWrongCredentialAlert().getText();
        return text;
    }

    public void correctLogin() {
        driver.manage().window().maximize();
        this.setUsername(properties.getProperty("USERNAME"));
        this.setPassword(properties.getProperty("PASSWORD"));
        this.clickLoginBtn();
    }
    public void testLogin() {
        driver.manage().window().maximize();
        this.setUsername(properties.getProperty("TEST_USERNAME"));
        this.setPassword(properties.getProperty("TEST_PASSWORD"));
        this.clickLoginBtn();
    }
    public void gerrardLogin() {
        driver.manage().window().maximize();
        this.setUsername(properties.getProperty("GERRARD_USERNAME"));
        this.setPassword(properties.getProperty("GERRARD_PASSWORD"));
        this.clickLoginBtn();
    }

}
