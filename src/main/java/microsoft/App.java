package microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the firefox executable
/*        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        // Create a new instance of Firefox driver
        WebDriver firefoxWebdriver = new FirefoxDriver();
        // Launch the Online Store Website
        firefoxWebdriver.get("http://www.store.demoqa.com");*/

        // Create a new instance of Firefox driver
        WebDriver chromeDriver = new ChromeDriver();
        // Launch the Online Store Website
        chromeDriver.get("http://www.bing.com");

        WebElement webElement = chromeDriver.findElement(By.name("q"));
        webElement.sendKeys("Hey there!\n");
        webElement.submit();

        // Close the firefox browser
//        firefoxWebdriver.quit();
//        chromeDriver.quit();
    }
}
