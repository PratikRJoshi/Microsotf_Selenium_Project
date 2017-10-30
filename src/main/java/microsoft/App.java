package microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        String webPageURL = "https://login.live.com/login.srf";
        String bingURL = "https://www.bing.com/";
        String username = "prateek_fireblast@yahoo.com";
        String password = "Frodobagins";

        String userNameTextBox = "//*[@id=\"i0281\"]/div[1]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/div";
        String nextButton = "//*[@id='idSIButton9']";

        final int COUNTER_LIMIT = 30;

        // Create a new instance of Chrome driver
        WebDriver chromeDriver = new ChromeDriver();
        Actions actions = new Actions(chromeDriver);

        // Launch the Online Store Website
        chromeDriver.get(webPageURL);

        WebElement usernameElement = chromeDriver.findElement(By.xpath(userNameTextBox));
        actions.moveToElement(usernameElement);
        actions.click();
        actions.sendKeys(username);
        actions.build().perform();

        chromeDriver.findElement(By.xpath(nextButton)).click();
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));

        WebElement passwordElement = chromeDriver.findElement(By.className("form-control"));
        actions.moveToElement(passwordElement);
        actions.click();
        actions.sendKeys(password);
        actions.build().perform();

        chromeDriver.findElement(By.xpath(nextButton)).click();
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));

        // Go to Bing homepage
        chromeDriver.get(bingURL);

        WebElement searchBox;

        int counter = 0;
        Random random = new Random();
        try {
            while (++counter <= COUNTER_LIMIT) {
                searchBox = chromeDriver.findElement(By.name("q"));
//                searchBox.sendKeys(UUID.randomUUID().toString() + "\n");
                searchBox.sendKeys((char)(random.nextInt('z' - 'a') + 'a') + "\n");
            }
        } finally {
            chromeDriver.close();

        }
    }
}
