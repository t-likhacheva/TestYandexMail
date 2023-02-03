package org.example.methods_front.newMessage;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.tests.Message;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Классы помошники для страниц логина
 */
@Slf4j
public class NewMessageMethods extends WebDriverSettings {

    public static NewMessageMethods newMessageMethods = new NewMessageMethods(driver);

    public NewMessageMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Поле Кому
     */
    @FindBy(xpath = "//div[@title=\"Кому\"]")
    public static WebElement input_recipient;
    /**
     * Поле Копия
     */
    @FindBy(xpath = "//div[@title=\"Копия\"]")
    public static WebElement input_copyTo;
    /**
     * Поле Тема
     */
    @FindBy(id = "compose-field-subject-4")
    public static WebElement input_theme;
    /**
     * Поле текст письма
     */
    @FindBy(xpath = "//div[@title=\"Напишите что-нибудь\"]")
    public static WebElement input_text;

    /**
     * Заполнение сообщения
     */
    public static void fillMessage(Message message) {
        input_recipient.sendKeys(message.recipient);
        // System.out.printf("ввели получателя: %s", message.recipient);
        log.info("ввели получателя: " + message.recipient);
        input_theme.sendKeys(message.subject);
        log.info("ввели тему: " + message.subject);
        input_text.sendKeys(message.text);
        log.info("ввели текст: " + message.text);
        baseMethods.waitPage(1000);
        log.info("немного подождали для автосохранения");
    }


    @FindBy(xpath = "//div[@class='ComposePopup-Body']//button[@aria-label='Закрыть']")
    public static WebElement close;

    public static void closeMessage() {
        close.click();
        log.info("закрыли окно с письмом");
    }


}
