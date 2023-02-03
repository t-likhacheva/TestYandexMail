package org.example.methods_front.drafts;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.tests.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DraftsMethods extends WebDriverSettings {
    public static DraftsMethods draftsMethodsraftsMethods = new DraftsMethods(driver);

    public DraftsMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Массив  всех писем (веб элемены)
     */
    @FindBy(xpath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div")
    public static List<WebElement> webElementDraftList;

    /**
     * Получение массива всех писем (данные)
     */
    public static ArrayList<Message> takeMessList() {
        ArrayList<Message> list = new ArrayList<Message>();
        //проходимся по списку писем и сохраняем данные в массив объектов типа Message
        for (WebElement webElement : webElementDraftList) {
            //получаем тему
            list.add(readMessageParamsFromWebElement(webElement));

        }
        return list;

    }

    private static Message readMessageParamsFromWebElement(WebElement webElement) {
        String subject = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span")).getText();
        //получаем текст письма
        String text = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/span")).getText();
        //получаем получателя письма
        String recipient = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-FromText']")).getText();
        return new Message(recipient, subject, text);
    }

    public static void findAndOpenDraft(Message message) {
//        for (WebElement webElement : webElementDraftList) {
//
//            //получаем тему
//            String subject = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span")).getText();
//            //получаем текст письма
//            String text = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/span")).getText();
//            //получаем получателя письма
//            String recipient = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-FromText']")).getText();


    }


    public static void checkInDrafts(Message message) {
        ArrayList<Message> listMes = takeMessList();
        int i =0;
        for (Message mes : listMes) {
            if (mes.equals(message)) {
                log.info("Письмо (тема: " + mes.subject + ", получатель:" + mes.recipient + ", текст: " + mes.text + ") есть в списке черновиков, Номенр по списку "+i);
                return;
            }
            i++;
        }
        log.info("Письма нет в списке черновиков");
        ;
    }
}


