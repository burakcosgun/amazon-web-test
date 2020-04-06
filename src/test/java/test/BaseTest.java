package test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest
{
    protected static WebDriver driver;
    protected  ChromeOptions chromeOptions;

    final static String MAAIN_PAGE_URL="https://www.amazon.com/";
    final static String VALID_USERNAME_DUMMY= "johndoedummyperson@gmail.com";
    final static String VALID_PASSWORD_DUMMY= "dummypassword1";

    @Before
    public void setUpBeforeTestCase() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("disable-automatic-password-saving");
        chromeOptions.addArguments("allow-silent-push");
        chromeOptions.addArguments("disable-infobars");

        driver = new ChromeDriver(chromeOptions);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(MAAIN_PAGE_URL);
    }

    @After
    public void tearDownAfterTestCase() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
