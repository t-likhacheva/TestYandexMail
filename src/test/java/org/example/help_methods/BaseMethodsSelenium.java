package org.example.help_methods;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

@Slf4j
public class BaseMethodsSelenium extends WebDriverSettings {
//    public static WebDriverWait wait1 = new WebDriverWait(WebDriverSettings.driver, 10); //

    public BaseMethodsSelenium(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openPage(String urlGet) throws Exception {
        WebDriverSettings.driver.get(urlGet);

    }


    public void clickElement(WebElement elementToClick) throws Exception {
        Thread.sleep(200);
        elementToClick.click();
        Thread.sleep(200);
    }
    public void clickElementJavaScript(WebElement elementToClick) throws Exception {
        Thread.sleep(200);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",elementToClick);
        Thread.sleep(200);
    }
    public void actionClickElement(WebElement elementToClick) throws Exception {
        Actions actions = new Actions(driver);
        Thread.sleep(200);
        actions.click(elementToClick).build().perform();
        Thread.sleep(200);
    }
    public void inputText(String textToinput, WebElement inputField) throws Exception {
       for(int i=0;i<20;i++){
           inputField.sendKeys(Keys.BACK_SPACE);
       }
        Thread.sleep(2000);
        inputField.click();
        inputField.sendKeys(textToinput);
    }
}
