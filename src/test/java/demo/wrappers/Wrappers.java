package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static boolean clickOnElement(ChromeDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement webelem = wait.until(ExpectedConditions.elementToBeClickable(locator));
            webelem.click();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public static void selectTab(ChromeDriver driver, String tabName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sections']")));
        List<WebElement> tabElements = driver
                .findElements(By.xpath("//a[@role='link']//yt-formatted-string[contains(@class, 'title')]"));
        for (WebElement tabElement : tabElements) {
            if (tabElement.getText().equals(tabName)) {
                tabElement.click();
            }
        }
    }

    public static WebElement getParentSection(ChromeDriver driver, String sectionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String xpath = "//span[contains(text(), '" + sectionName + "')]//ancestor::div[@id='dismissible']";
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    public static WebElement getParentMusicSection(ChromeDriver driver, String sectionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String xpath = String.format("//span[text()=\"%s\"]//ancestor::div[@id='dismissible']", sectionName);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    public static void enterText(ChromeDriver driver, By locator, String text) {
        try {
            WebElement elem = driver.findElement(locator);
            elem.clear();
            elem.sendKeys(text);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static long formatNumbers(String str) {
        char lastChar = str.charAt(str.length() - 1);
        double floatValue = Double.parseDouble(str.substring(0, str.length() - 1));

        if(lastChar == 'K') {
            return (long) floatValue*1000;
        }
        if(lastChar == 'M') {
            return (long) floatValue*1000000;
        }
        return (long) floatValue;
    }
}
