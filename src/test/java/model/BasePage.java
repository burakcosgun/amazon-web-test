package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {


    WebDriver driver;

    public void setText(String id , String text){
        driver.findElement(By.id(id)).sendKeys(text);
    }

    public void click(String id){
        driver.findElement(By.id(id)).click();
    }
}
