package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishList extends BasePage {

    private String productInfo = null;

    public WishList(WebDriver driver) {
        this.driver = driver;
    }

    //ürün indexleri 0dan başladığı için product number - 1 yapıldı
    public void selectNthProduct(int productNumber){
        productInfo = driver.findElement(By.xpath("//*[@id=\"search\"]//div[@data-index='"+(productNumber-1)+"']//h2/a/span")).getText();
        driver.findElement(By.xpath("//*[@id=\"search\"]//div[@data-index='"+(productNumber-1)+"']//h2/a/span")).click();
    }

    public void addToList(){
        click("add-to-wishlist-button-submit");
    }

    public void selectWishList(){
        driver.findElement(By.xpath("//input[@id='WLNEW_list_type_WL']/..//i")).click();
    }

    public void createWishList(){
        click("WLNEW_create");
    }

    public void goWishList(){
        driver.findElement(By.id("WLHUC_result_listName")).click();
    }

    public boolean isProductExist(){
            return driver.findElement(By.xpath("//div[@id='item-page-wrapper']//a[@title='"+productInfo+"']")).isDisplayed();
    }

    public void deleteProductFromWishList(){
        driver.findElement(By.xpath("//div[@id='item-page-wrapper']//a[@title='"+productInfo+"']/../../../../../..//span[@data-action='reg-item-delete']")).click();
    }
    public boolean isProductDeleted(){
        WebElement element;
        driver.navigate().refresh();
        try{
           element = driver.findElement(By.xpath("//div[@id='item-page-wrapper']//a[@title='"+productInfo+"']"));
            return false;
        }
        catch (Exception e){
            return true;
    }
    }
}
