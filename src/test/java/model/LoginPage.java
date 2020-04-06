package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends BasePage{

    Actions builder;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage(){
        click("nav-link-accountList");
    }

    public void loginAttempt(String userName,String password) {
        setText("ap_email", userName);
        click("continue");
        setText("ap_password", password);
        click("signInSubmit");

        String alert = null;
        DeathCaptcha deathCaptcha = new DeathCaptcha();
        try {
            if( driver.findElement(By.id("auth-warning-message-box")).isDisplayed()) {
                alert = driver.findElement(By.id("auth-warning-message-box")).getText();
                setText("ap_password", password);
                deathCaptcha.Recaptcha(driver);
                try {
                    Thread.sleep(3300);
                }
                catch (Exception e){

                }
                click("signInSubmit");
            }

        }
        catch (Exception e){

        }


        click("continue");
    }

    public void setOtp(String otp){
        driver.findElement(By.cssSelector(".cvf-widget-input-code")).sendKeys(otp);
        driver.findElement(By.cssSelector(".cvf-widget-input-code")).sendKeys(Keys.ENTER);
    }

    public String checkUserName(){
        return driver.findElement(By.xpath("//a[@id='nav-link-accountList']//span")).getText();
    }
}
