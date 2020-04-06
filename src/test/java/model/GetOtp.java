package model;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetOtp {
	
	public String getOtp() throws InterruptedException, Exception, IOException {
		String gUserID = "johndoedummyperson@gmail.com";
		String gPass = "dummypassword1";


		WebDriver driver2 = new ChromeDriver();
		driver2.manage().deleteAllCookies();
		driver2.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver2.get("https://accounts.google.com/signin/v2/sl/pwd?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AddSession&cid=0&navigationDirection=forward");
		driver2.findElement(By.id("identifierId")).sendKeys(gUserID); //userid

		driver2.findElement(By.xpath(".//span[@class='RveJvd snByac']")).click();
		Thread.sleep(5000);
		driver2.findElement(By.name("password")).sendKeys(gPass); //password
		driver2.findElement(By.xpath(".//span[@class='RveJvd snByac']")).click();
		Thread.sleep(5000);

		String text = ((ChromeDriver) driver2).findElementByXPath("//span[contains(.,'To authenticate')]").getText();

		String otp = null;
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(text);
		while (m.find()) {
			otp = (m.group(0));
		}
		Thread.sleep(3000);
		((ChromeDriver) driver2).findElementByXPath("//span[contains(.,'To authenticate')]//..//..").click();
		Thread.sleep(5000);

		driver2.close();
		return otp;
	}
}