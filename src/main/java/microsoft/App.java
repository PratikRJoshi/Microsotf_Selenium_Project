package microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Project created by Pratik :)
 */
public class App {

    private static List<AccountDetails> getAccountObject(File file) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<AccountDetails> accountDetailsList = null;
        try {
            accountDetailsList = new ArrayList<>();
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String fileLine = null;
            boolean switchAccountArg = false;

            while ((fileLine = bufferedReader.readLine()) != null) {
                AccountDetails accountDetails = new AccountDetails();
                accountDetails.setUserName(fileLine.split("\\s+")[0]);
                accountDetails.setPassword(fileLine.split("\\s+")[1]);

                accountDetailsList.add(accountDetails);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
            bufferedReader.close();
        }
        return accountDetailsList;

    }

    public static void main(String[] args) throws InterruptedException, IOException {

        String webPageURL = "https://login.live.com/login.srf";
        String bingURL = "https://www.bing.com/";

        final int COUNTER_LIMIT = 30;

        File inputAccountDetailFile = new File("/Users/pratik.joshi/Documents/Projects/Microsoft_Rewards_Selenium/resources/Accounts");
        List<AccountDetails> accountDetailsList = getAccountObject(inputAccountDetailFile);

        for (AccountDetails accountDetail : accountDetailsList) {

            String userNameTextBox = "//*[@id=\"i0281\"]/div[1]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/div";
            String nextButton = "//*[@id='idSIButton9']";


            // Create a new instance of Chrome driver
            WebDriver chromeDriver = new ChromeDriver();
            Actions actions = new Actions(chromeDriver);

            // Launch the Online Store Website
            chromeDriver.get(webPageURL);

            WebElement usernameElement = chromeDriver.findElement(By.xpath(userNameTextBox));
            actions.moveToElement(usernameElement);
            actions.click();
            actions.sendKeys(accountDetail.getUserName());
            actions.build().perform();

            chromeDriver.findElement(By.xpath(nextButton)).click();
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));

            WebElement passwordElement = chromeDriver.findElement(By.className("form-control"));
            actions.moveToElement(passwordElement);
            actions.click();
            actions.sendKeys(accountDetail.getPassword());
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
                    searchBox.sendKeys((char) (random.nextInt('z' - 'a') + 'a') + "\n");
                }
            } finally {
                chromeDriver.close();

            }
        }
    }
}
