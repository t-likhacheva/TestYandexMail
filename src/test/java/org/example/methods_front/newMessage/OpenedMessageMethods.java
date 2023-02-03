package org.example.methods_front.newMessage;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.models.Message;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Классы помошники для страниц логина
 */
@Slf4j
public class OpenedMessageMethods extends WebDriverSettings {

    public static OpenedMessageMethods openedMessageMethods = new OpenedMessageMethods(driver);

    public OpenedMessageMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Поле Кому
     */
    @FindBy(xpath = "//div[@title='Кому']")
    public static WebElement input_recipient;
    /**
     * Первое значение в поле Кому
     */
    @FindBy(xpath = "//div[@title='Кому']//div[@class='ComposeYabble-Text']")
    public static WebElement input_recipient_value;


    /**
     * Поле Копия
     */
    @FindBy(xpath = "//div[@title=\"Копия\"]")
    public static WebElement input_copyTo;
    /**
     * Поле Тема (для самого перового открытого окна))
     * !!! Для корректной работы должны быть закрыты все окна редактирования писем, кроме искомого
     */
    @FindBy(xpath = "//div[@data-popper-placement='top']//span[contains(text(),'Тема')]/../..//input")
    public static WebElement input_subject;
    @FindBy(xpath = "//span[@class='composeHeader-Title']/span")
    public static WebElement input_subject_value;

    /**
     * Поле текст письма
     */
    @FindBy(xpath = "//div[@title='Напишите что-нибудь']")
    public static WebElement input_text;
    /**
     * Значение в поле текст письма
     */
    @FindBy(xpath = "//div[@title='Напишите что-нибудь']/div")
    public static WebElement input_text_value;

    /**
     * Заполнение сообщения
     */
    public static void fillMessage(Message message) {
        base.inputText(message.recipient, input_recipient);
        // System.out.printf("ввели получателя: %s", message.recipient);
        log.info("ввели получателя: " + message.recipient);
        base.inputText(message.subject, input_subject);
        log.info("ввели тему: " + message.subject);
        base.inputText(message.text, input_text);
        log.info("ввели текст: " + message.text);
        base.waitPage(1000);
        log.info("немного подождали для автосохранения");
    }


    @FindBy(xpath = "//div[@class='ComposePopup-Body']//button[@aria-label='Закрыть']")
    public static WebElement close;

    public static void closeMessage() {
        base.clickElement(close);
        log.info("закрыли окно с письмом");
    }

    public static Message getMessageParams() {
        return new Message(input_recipient_value.getText(), input_subject_value.getText(), input_text_value.getText());
    }

    public static void compareMessageParams(Message message) {
        Message mess = getMessageParams();
        Assert.assertEquals(mess, message);
        log.info("Данные открытого письма совпадают с письмом (тема: " + message.subject
                + ", получатель:" + message.recipient
                + ", текст: " + message.text + ")");


    }

}
