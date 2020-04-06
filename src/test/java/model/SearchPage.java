package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String product){
        setText("twotabsearchtextbox" , product);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
    }

    public void goNthPage(String pageNumber){
        driver.findElement(By.xpath("//a[.='"+pageNumber+"']")).click();
    }
}
