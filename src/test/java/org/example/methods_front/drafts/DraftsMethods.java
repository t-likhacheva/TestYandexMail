package org.example.methods_front.drafts;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.tests.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class DraftsMethods extends WebDriverSettings {
    public DraftsMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Список всех писем
     */
    @FindBy(xpath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div")
    public static List<WebElement> drafts;

    public static List<Message> takeMessList() {
        List<Message> list = null;
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']"));
        for (WebElement webElement : webElementList) {
            log.info("Корневой Элемент subject есть");
            try {
                WebElement subj = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span"));
            } catch (Exception e) {
                log.info("Элемент subject не найден");
                return null;
            }


            log.info("Элемент subject есть");
            String subject = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span")).getText();
            log.info(subject);
            String text = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/span")).getText();
            String recipient = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-FromText']")).getText();
            list.add(new Message(recipient, subject, text));

        }
        log.info(list.toString());
        return list;

    }

    public static void checkInDrafts(Message message) {
        List<Message> list = takeMessList();

    }
}
