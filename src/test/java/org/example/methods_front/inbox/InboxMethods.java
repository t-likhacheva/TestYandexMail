package org.example.methods_front.inbox;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.BaseMethodsSelenium;
import org.example.help_methods.WebDriverSettings;
import org.example.methods_front.login.LoginGroupMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Методы для работы на главной странице (входящие)
 */
@Slf4j
public class InboxMethods extends WebDriverSettings {
    public static BaseMethodsSelenium baseMethods;
    public static InboxMethods inboxMethods;

    public InboxMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * Кнопка Написать письмо
     */
    @FindBy(xpath = "//span[contains(text(),'Написать')]/..")
    public static WebElement button_new;

    /**
     * Ссылка на черновики
     */
    @FindBy(xpath = "//span[contains(text(),'Черновики')]/../..")
    public static WebElement button_drafts;
    /**
     * Открытие нового письма
     */
    public static void openNewMail() {
        baseMethods = new BaseMethodsSelenium(driver);
        inboxMethods = new InboxMethods(driver);
        button_new.click();
        log.info("Открыли новое письмо");


    }

}
