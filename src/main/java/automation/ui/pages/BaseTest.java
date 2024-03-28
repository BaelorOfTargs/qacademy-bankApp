package automation.ui.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;

    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        properties = new Properties();
        FileInputStream configFile = new FileInputStream("src/main/config.properties");
        properties.load(configFile);
    }
    public void getSignUpUrl(){
        driver.get(properties.getProperty("SIGN_UP_URL"));
    }
    public void getBaseUrl() {
        driver.get(properties.getProperty("BASE_URL") + "/signin");
    }
    public void getBanAccURL(){driver.get(properties.getProperty("BANK_ACCOUNT_PAGE"));}

    public void tearDown() {
        driver.quit();
    }
    public static String getNCharacters(int n) {
        StringBuffer outputBuffer = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            outputBuffer.append("a");
        }
        return outputBuffer.toString();
    }
    public static String getNSpaces(int n) {
        StringBuffer outputBuffer = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }
    public static String getRandomString(int n){
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        return r.toString();
    }
    public void takeScreenShot(String screenShotName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/main/java/pom/" + screenShotName + ".png"));
    }
}
