package org.example.methods_front.inbox;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Методы для работы на главной странице (входящие)
 */
@Slf4j
public class InboxMethods extends WebDriverSettings {

    public static InboxMethods inboxMethods = new InboxMethods(driver);

    public InboxMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Кнопка Написать письмо
     */
    @FindBy(xpath = "//span[contains(text(),'Написать')]/..")
    public static WebElement button_new;

    //a[@aria-describedby="tooltip-0-1"]
    @FindBy(xpath = "//a[@aria-describedby='tooltip-0-1']")
    public static WebElement button_new2;
    /**
     * Ссылка на черновики
     */
    @FindBy(xpath = "//span[contains(text(),'Черновики')]/../..")
    public static WebElement button_drafts;

    /**
     * Открытие нового письма
     */
    public static void openNewMail() {
        try {
            base.clickElement(button_new2);
        } catch (NoSuchElementException e) {
            base.clickElement(button_new);
        }


        log.info("Открыли новое письмо");

    }

    public static void goToDrafts() {
        base.clickElement(button_drafts);
        log.info(" Перешли в черновики");
    }
    /**
     * Ссылка на входящие
     */
    @FindBy(xpath = "//span[contains(text(),'Входящие')]/../..")
    public static WebElement button_input;
    public static void goToInput() {
        base.clickElement(button_input);
        log.info(" Перешли во входящие");
    }
}
