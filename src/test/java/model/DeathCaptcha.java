package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.Captcha;
import model.Client;
import model.SocketClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class DeathCaptcha {
    String captchaURL = null;
    Captcha captchaVal=null;
    int counter1 = 0;

    public void Recaptcha(WebDriver driver){


        String errText = driver.findElement(By.xpath(".//body")).getAttribute("innerHTML");

        if (errText.contains("captcha")) {
            do {
                System.out.println("Inside 1st capcha screen");
                if (driver.findElement(By.xpath(".//body")).getAttribute("innerHTML").contains("Hear the challenge")) {
                    captchaURL = driver.findElement(By.xpath(".//img[contains(@src,'captcha')]")).getAttribute("src");
                    try {
                        captchaVal = ExampleNewRecaptchaToken(captchaURL);
                    } catch (Exception e) {

                    }
                    if (captchaVal != null) {
                        driver.findElement(By.id("auth-captcha-guess")).sendKeys(captchaVal.toString());
                    } else {
                        break;
                    }
                }

                counter1++;
                if (counter1 > 5) {
                    break;
                }
            } while (errText.contains("There was a problem"));
            int counter2 = 0;
            errText = driver.findElement(By.xpath(".//body")).getAttribute("innerHTML");
            if (errText.contains("Anti-Automation Challenge")) {
                do {
                    System.out.println("inside page Anti-Automation Challenge ");
                    captchaURL = driver.findElement(By.xpath(".//img[contains(@src,'captcha')]")).getAttribute("src");
                    try {
                        captchaVal = ExampleNewRecaptchaToken(captchaURL);
                    } catch (Exception e) {

                    }
                    if (captchaVal != null) {
                        driver.findElement(By.xpath(".//input[@name='cvf_captcha_input']")).sendKeys(captchaVal.toString());
                    } else {
                        break;
                    }

                    driver.findElement(By.xpath(".//input[@name='cvf_captcha_captcha_action']")).click();
                    errText = driver.findElement(By.xpath(".//body")).getAttribute("innerHTML");
                    counter2++;
                    if (counter2 > 5) {
                        break;
                    }
                } while (errText.contains("Anti-Automation Challenge"));
            }
        }
          }

    public static Captcha ExampleNewRecaptchaToken(String captchaURL) throws IOException, Exception, InterruptedException {
        /*
         * Put your DeathByCaptcha account username and password here. Use HttpClient
         * for HTTP API.
         */

        Client client = (Client) (new SocketClient("allinonerc@gmail.com", "Abi2015@"));
        double balance = client.getBalance();
        String fileName=null;
        Captcha captcha = null;
        fileName = "digital_image_processing.jpg";
        System.out.println("Downloading File From: " + captchaURL);

        URL url = new URL(captchaURL);
        InputStream inputStream = url.openStream();
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] buffer = new byte[2048];

        int length = 0;
        int counter=0;

        while ((length = inputStream.read(buffer)) != -1) {
            System.out.println("Buffer Read of length: " + length);
            outputStream.write(buffer, 0, length);
            counter++;
            if(counter>5) {
                break;
            }
        }

        inputStream.close();
        outputStream.close();
        if(counter<=5) {
            captcha = client.decode(System.getProperty("user.dir") + "/"+fileName, 2, 0);
            if (null != captcha) {
                /*
                 * The CAPTCHA was solved; captcha.id property holds its numeric ID, and
                 * captcha.text holds its text.
                 */
                System.out.println("CAPTCHA " + captcha.id + " solved: " + captcha.text);

                if (client.report(captcha)) {
                    System.out.println("Reported as incorrectly solved");
                } else {
                    System.out.println("Failed reporting incorrectly solved CAPTCHA");
                }
            }
        }else {
            captcha=null;
        }


        return captcha;

    }
}
