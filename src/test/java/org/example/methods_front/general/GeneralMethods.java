package org.example.methods_front.general;

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
public class GeneralMethods extends WebDriverSettings {


    public GeneralMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public static GeneralMethods generalMethods = new GeneralMethods(driver);

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
     * Ссылка на входящие
     */
    @FindBy(xpath = "//span[contains(text(),'Входящие')]/../..")
    public static WebElement button_input;

    /**
     * Ссылка на важные
     */
    @FindBy(xpath = " //*[@href=\"#important\"]")
    public static WebElement button_important;



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

    /**
     * Переход к черновикам
     */
    public static void goToDrafts() {
        base.clickElement(button_drafts);
        log.info(" Перешли в черновики");
    }

    /**
     * Переход к письмам с пометкой Важно
     */
    public static void goToImportant() {
        base.clickElement(button_important);
        log.info(" Перешли в раздел Важные");
    }

    /**
     * Переход к входящим
     */
    public static void goToInput() {
        base.clickElement(button_input);
        log.info(" Перешли во входящие");
    }
}
