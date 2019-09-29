package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

class BasePage {

    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Click by left mouse's button
     *
     * @param elementXpath - element's xpath for a click
     */
    void clickLMB(String elementXpath) {
        driver
                .findElement(xpath(elementXpath))
                .click();
    }

    /**
     * Click by JS
     *
     * @param elementXpath - element's xpath for a click
     */
    void clickByJS(String elementXpath) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", driver.findElement(xpath(elementXpath)));
    }

    /**
     * Setting any text in any field
     *
     * @param fieldXpath - field's xpath
     * @param text       - text for insert
     */
    void setTextInField(String fieldXpath, String text) {
        WebElement textField = driver.findElement(xpath(fieldXpath));
        textField.clear();
        textField.sendKeys(text);
    }

    /**
     * Select item from dropdown by text
     *
     * @param dropDownXpath - dropdown's xpath
     * @param text          - text of item for a choice
     */
    void selectDropDownByText(String dropDownXpath, String text) {
        Select dropdown = new Select(driver.findElement(xpath(dropDownXpath)));
        dropdown.selectByVisibleText(text);
    }

    /**
     * Select item from dropdown by value
     *
     * @param dropDownXpath - dropdown's xpath
     * @param value         - value of item for a choice
     */
    void selectDropDownByValue(String dropDownXpath, String value) {
        Select dropdown = new Select(driver.findElement(xpath(dropDownXpath)));
        dropdown.selectByValue(value);
    }

    /**
     * Waiting for any element
     *
     * @param elementXpath - element's xpath
     * @param seconds      - count of seconds for waiting for
     */
    void waitForAnyElement(String elementXpath, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(presenceOfElementLocated(xpath(elementXpath)));
    }

    /**
     * Waiting for any element disappear
     *
     * @param elementXpath - element's xpath
     * @param seconds      - count of seconds for waiting for
     */
    void waitForAnyElementDisappear(String elementXpath, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(invisibilityOf(driver.findElement(xpath(elementXpath))));
        } catch (NoSuchElementException e) {
            //go on
        }
    }

    /**
     * Waiting for any element has attribute value
     *
     * @param elementXpath - element's xpath
     */
    void waitForAnyElementHasNotAttribute(String elementXpath, String attribute, String attributeValue) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(not(attributeToBe(xpath(elementXpath), attribute, attributeValue)));
    }

    /**
     * Is element displayed
     *
     * @param elementXpath - element's xpath
     */
    boolean isElementDisplayed(String elementXpath) {
        return driver.findElement(By.xpath(elementXpath)).isDisplayed();
    }

    /**
     * Get element text
     *
     * @param elementXpath - element's xpath
     */
    String getElementText(String elementXpath) {
        return driver.findElement(By.xpath(elementXpath)).getText();
    }

    /**
     * Get list of elements
     *
     * @param elementXpath - element's xpath
     */
    List<WebElement> getElementList(String elementXpath) {
        return driver.findElements(By.xpath(elementXpath));
    }

    /**
     * Scroll to element
     *
     * @param elementXpath - element's xpath
     */
    void scrollToElement(String elementXpath) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        driver.findElement(xpath(elementXpath)));
    }
}
