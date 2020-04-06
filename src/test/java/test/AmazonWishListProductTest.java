package test;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AmazonWishListProductTest extends BaseTest {

    final static String PAGE_NUMBER="2";
    final static int PRODUCT_NUMBER=3;


    @Test
    public void TestSearchAndWihList(){

        String currentURL;
        String favoritedItemProductId;
        String otp = null;
        WebElement loggedInUser;

        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        SearchPage searchPage = PageFactory.initElements(driver,SearchPage.class);
        WishList wishList = PageFactory.initElements(driver,WishList.class);

        GetOtp getOtp = new GetOtp();
        DeathCaptcha deathCaptcha = new DeathCaptcha();

        loginPage.openLoginPage();
        loginPage.loginAttempt(VALID_USERNAME_DUMMY,VALID_PASSWORD_DUMMY);

        deathCaptcha.Recaptcha(driver);

        // Set otp
       try {
            otp = getOtp.getOtp();
        }
        catch ( Exception e) {

        }
        loginPage.setOtp(otp);

        Assert.assertEquals("Hello, john" , loginPage.checkUserName());
        System.out.println("Login is succesfull!");

        searchPage.searchProduct("samsung");
        Assert.assertTrue(driver.getPageSource().contains("samsung"));

        searchPage.goNthPage(PAGE_NUMBER);
        Assert.assertTrue(driver.getCurrentUrl().contains("page="+PAGE_NUMBER));

        wishList.selectNthProduct(PRODUCT_NUMBER);
        wishList.addToList();

        wishList.goWishList();
        Assert.assertTrue(wishList.isProductExist());

        wishList.deleteProductFromWishList();
        Assert.assertTrue(wishList.isProductDeleted());
    }
}
