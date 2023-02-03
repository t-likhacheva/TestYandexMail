package org.example.methods_front.newMessage;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.BaseMethodsSelenium;
import org.example.help_methods.WebDriverSettings;
import org.example.methods_front.inbox.InboxMethods;
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
        log.info("ввели получателя");
        input_theme.sendKeys(message.theme);
        log.info("ввели тему");
        input_text.sendKeys(message.text);
        log.info("ввели текст");
        baseMethods.waitPage(1000);
        log.info("немного подождали для автосохранения");
    }

    public static void closeMessage() {

    }


}
