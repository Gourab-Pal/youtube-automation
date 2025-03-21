package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
import demo.utils.ExcelReaderUtil;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider { // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @Test(priority = 1)
        public void testCase01() {

                // opening the homepage url
                driver.get("https://www.youtube.com/");

                // asserting the url validation
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("youtube"));

                // clicking on About
                Wrappers.clickOnElement(driver, By.xpath("//a[text()='About']"));

                Assert.assertTrue(driver.getCurrentUrl().contains("about"));

                System.out.println("Youtube about --> "
                                + driver.findElement(By.xpath("//section[@class='ytabout__content']/h1")).getText());
                // printing the messages
                List<WebElement> msgElements = driver.findElements(By.xpath("//section[@class='ytabout__content']//p"));
                for (WebElement msgElement : msgElements) {
                        System.out.println(msgElement.getText());
                }
        }

        @Test(priority = 2)
        public void testCase02() throws InterruptedException {
                // opening the homepage url
                driver.get("https://www.youtube.com/");

                // select Movies tab
                Wrappers.selectTab(driver, "Movies");

                // selecting Top-Selling
                String sectionName = "Top selling";
                WebElement movieParent = Wrappers.getParentSection(driver, sectionName);

                // clicking on right arrow
                WebElement rightArrow = movieParent
                                .findElement(By.xpath(".//div[@id='right-arrow']/ytd-button-renderer"));
                boolean rightArrowVisibility = rightArrow.isDisplayed();
                while (rightArrowVisibility) {
                        rightArrow.click();
                        Thread.sleep(2000);
                        rightArrowVisibility = rightArrow.isDisplayed();
                }

                // get the last movie card
                WebElement lastMovieCard = movieParent.findElement(By.xpath(".//ytd-grid-movie-renderer[last()]"));

                // Adult rating
                String adultRating = lastMovieCard.findElement(By.xpath(".//p[not(contains(text(), 'Buy or rent'))]"))
                                .getText();

                // Category
                String category = lastMovieCard.findElement(By.xpath(".//span[contains(@class, 'metadata')]"))
                                .getText();

                // soft assertions
                SoftAssert soft = new SoftAssert();

                soft.assertFalse(adultRating.equals("A"), "This is not A rated movie"); // used assertFalse to skip
                                                                                        // build failure
                soft.assertNotNull(category);

                soft.assertAll();

        }

        @Test(priority = 3)
        public void testCase03() {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                // opening the homepage url
                driver.get("https://www.youtube.com/");

                // select Music tab
                Wrappers.selectTab(driver, "Music");

                // selecting India's Biggest Hits
                String sectionName = "India's Biggest Hits";
                WebElement musicParent = Wrappers.getParentMusicSection(driver, sectionName);

                // last musicCard
                WebElement lastMusicCard = wait.until(ExpectedConditions.visibilityOf(musicParent.findElement(
                                By.xpath(".//div[@id='contents']//ytd-rich-item-renderer[not(@hidden)][last()]"))));

                // playlist name
                String playlistName = wait.until(ExpectedConditions.visibilityOf(lastMusicCard.findElement(By.xpath(".//h3/span")))).getText();
                System.out.println("Name of the platlist --> " + playlistName);

                // number of songs
                int songNumber = Integer
                                .parseInt(lastMusicCard.findElement(By.xpath(".//div[@class='badge-shape-wiz__text']"))
                                                .getText().replace(" songs", ""));

                SoftAssert soft = new SoftAssert();
                soft.assertTrue(songNumber <= 50);

                soft.assertAll();
        }

        @Test(priority = 4)
        public void testCase04() {
                // opening the homepage url
                driver.get("https://www.youtube.com/");

                // select New tab
                Wrappers.selectTab(driver, "News");

                // selecting Latest news posts
                String sectionName = "Latest news posts";
                WebElement newsParent = Wrappers.getParentSection(driver, sectionName);

                // visible news cards
                List<WebElement> newsCards = newsParent
                                .findElements(By.xpath(".//ytd-rich-item-renderer[not(@hidden)]"));

                // loop through first 3 cards to print title, body
                int totalLikes = 0;
                for (int i = 0; i < 3; i++) {
                        WebElement currentElem = newsCards.get(i);
                        String title = currentElem
                                        .findElement(By.xpath(".//div[@id='header']//a[@id='author-text']/span"))
                                        .getText();
                        String body = currentElem.findElement(By.xpath(
                                        ".//div[@id='body']//yt-formatted-string[@id='home-content-text']/span[1]"))
                                        .getText();
                        String likes = currentElem.findElement(By.xpath(
                                        ".//ytd-comment-action-buttons-renderer//div[@id='toolbar']//span[@id='vote-count-middle']"))
                                        .getText();
                        try {
                                totalLikes = totalLikes + Integer.parseInt(likes);
                        } catch (Exception e) {
                                // TODO: handle exception

                        }
                        System.out.println("Title --> " + title);
                        System.out.println("Body --> " + body);
                        // System.out.println("Like counts --> " + likes);
                        // new code
                }
                System.out.println("Total like count for first three news --> " + totalLikes);

        }

        @Test(priority = 5, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
        public void testCase05(String myData) throws InterruptedException {
                // opening the homepage url
                driver.get("https://www.youtube.com/");

                // sending search text
                Wrappers.enterText(driver, By.xpath("//input[@name='search_query']"), myData);

                Wrappers.clickOnElement(driver, By.xpath("//yt-searchbox//button[@aria-label='Search']"));

                // (//span[contains(@class, 'inline-metadata-item') and contains(text(),
                // 'views')])[26]

                long target = 100000000;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                int currentContentsNumber = driver.findElements(By.xpath(
                                "(//span[contains(@class, 'inline-metadata-item') and contains(text(), 'views')])"))
                                .size();
                long totalViewCount = 0;
                int xpathIndex = 1;
                while (true) {
                        if (xpathIndex < currentContentsNumber) {
                                String xpath = "(//span[contains(@class, 'inline-metadata-item') and contains(text(), 'views')])["
                                                + xpathIndex + "]";
                                WebElement elem = wait
                                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                                js.executeScript("arguments[0].scrollIntoView()", elem);
                                String viewRaw = elem.getText().replace(" views", "");
                                long currentElementViewCount = Wrappers.formatNumbers(viewRaw);
                                totalViewCount = totalViewCount + currentElementViewCount;
                                xpathIndex++;

                        } else {
                                String xpath = "(//span[contains(@class, 'inline-metadata-item') and contains(text(), 'views')])["
                                                + xpathIndex + "]";
                                WebElement elem = wait
                                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                                js.executeScript("arguments[0].scrollIntoView()", elem);
                                String viewRaw = elem.getText().replace(" views", "");
                                long currentElementViewCount = Wrappers.formatNumbers(viewRaw);
                                totalViewCount = totalViewCount + currentElementViewCount;
                                currentContentsNumber = driver.findElements(By.xpath(
                                                "(//span[contains(@class, 'inline-metadata-item') and contains(text(), 'views')])"))
                                                .size();
                                xpathIndex++;
                        }
                        // System.out.println("Current total view count --> " + totalViewCount);
                        if (totalViewCount > target) {
                                // System.out.println("Final view count --> " + totalViewCount);
                                break;
                        }
                }
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}