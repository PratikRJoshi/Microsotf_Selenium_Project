package microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        String searchTextFile = "/Users/pratik.joshi/Documents/Projects/Microsoft_Rewards_Selenium/resources/SearchTexts";

        String webPageURL = "https://login.live.com/login.srf";
        String bingURL = "https://www.bing.com/";
        String username = "prateek_fireblast@yahoo.com";
        String password = "Frodobagins";

        String userNameTextBox = "//*[@id=\"i0281\"]/div[1]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/div";
        String nextButton = "//*[@id='idSIButton9']";
        String signInButton = "//*[@id='i0118']";

        // Create file reader to read the search texts from a file
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        // Create a new instance of Firefox driver
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
        Thread.sleep(2000);

        WebElement passwordElement = chromeDriver.findElement(By.className("form-control"));
        actions.moveToElement(passwordElement);
        actions.click();
        actions.sendKeys(password);
        actions.build().perform();

        chromeDriver.findElement(By.xpath(nextButton)).click();
        Thread.sleep(2000);

        // Go to Bing homepage
        chromeDriver.get(bingURL);

        final WebElement searchButton = chromeDriver.findElement(By.name("q"));
//        searchButton.sendKeys("Rose\n");

        try (Stream<String> lineStream = Files.lines(Paths.get(searchTextFile))) {
            lineStream.forEach(line -> searchButton.sendKeys(line+"\n"));
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
