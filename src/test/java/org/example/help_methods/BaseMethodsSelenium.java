package org.example.help_methods;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class BaseMethodsSelenium extends WebDriverSettings {
    private final int MILLIS;
    //    public static WebDriverWait wait1 = new WebDriverWait(WebDriverSettings.driver, 10); //

    public BaseMethodsSelenium(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        //таймаут в мс
        MILLIS = 200;
    }

    public void openPage(String urlGet) throws Exception {
        WebDriverSettings.driver.get(urlGet);

    }

    public Boolean checkText(String text) {
        return driver.findElement(By.linkText(text)).isDisplayed();
    }

    public void clickElement(WebElement elementToClick) throws Exception {
        Thread.sleep(MILLIS);
        elementToClick.click();
        Thread.sleep(MILLIS);
    }

    public void clickElementJavaScript(WebElement elementToClick) throws Exception {
        Thread.sleep(MILLIS);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", elementToClick);
        Thread.sleep(MILLIS);
    }

    public void actionClickElement(WebElement elementToClick) throws Exception {
        Actions actions = new Actions(driver);
        Thread.sleep(MILLIS);
        actions.click(elementToClick).build().perform();
        Thread.sleep(MILLIS);
    }

    public void waitPage(Integer millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void inputText(String textToinput, WebElement inputField) throws Exception {
        for (int i = 0; i < 20; i++) {
            inputField.sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(MILLIS);
        inputField.click();
        inputField.sendKeys(textToinput);
    }
}
