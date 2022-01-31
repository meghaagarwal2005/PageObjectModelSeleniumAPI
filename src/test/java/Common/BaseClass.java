package Common;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
    public static Logger log = LoggerManager.getLogger();
    public static WebDriver driver;
    public KeyValReader hiveProp = new KeyValReader("Configs/theHive.properties");
    final static int DEFAULT_WAIT_TIME = 30;


    public static WebDriver clickOn(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
            wait.ignoring(ElementNotInteractableException.class, ElementNotSelectableException.class).until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            log.info("Clicked on :" + webElement);
        } catch (Exception e) {
            log.error("Unable to Find : " + webElement);
            Assert.fail(e.getMessage());
        }
        return driver;
    }

    public static void selectDropdownByValue(WebElement webElement, String option) {
        waitForElementVisible(webElement);
        Select selector = new Select(webElement);
        selector.selectByValue(option);
    }
    public static WebDriver waitForElementVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
            log.info(webElement + ": is Visible");
        } catch (Exception e) {
            log.error("The element : " + webElement + " " + "is not visible");
            Assert.fail(e.getMessage());
        }
        return driver;
    }

    public static void sendKeys(WebElement webElement, String value) {
        try {

            waitForElementVisible(webElement);
            webElement.clear();
            webElement.sendKeys(value);
            log.info(value + " entered on :" + webElement);
        } catch (Exception e) {
            log.error("Unable to Find : " + webElement);
            Assert.fail(e.getMessage());
        }
    }

    public static void mouseOverClick(WebElement webElement) {
        Actions actions = new Actions(driver);
        waitForElementVisible(webElement);
        actions.moveToElement(webElement).click().build().perform();
    }

    public static void assertContainsText(WebElement webElement, String value) {

        waitForElementVisible(webElement);
        log.info("Actual value equals " + webElement.getText());
        Assert.assertTrue(webElement.getText().trim().contains(value.trim()));

    }

}
