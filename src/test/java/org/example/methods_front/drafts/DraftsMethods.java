package org.example.methods_front.drafts;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.models.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DraftsMethods extends WebDriverSettings {

    public DraftsMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public static DraftsMethods draftsMethodsraftsMethods = new DraftsMethods(driver);
    /**
     * Xpath для чекбокса относительно ВЕБЭЛЕМЕНТА строки из списка писем
     */
    private static final String xpathCheckBox = ".//*[@data-nb='checkbox']";
    /**
     * Массив  всех писем (веб элемены)
     */
    @FindBy(xpath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div")
    public static List<WebElement> webElementDraftList;
    /**
     * Кнопка удалить письма
     */
    @FindBy(xpath = "//span[contains(text(),'Удалить')]/..")
    public static WebElement button_delete;


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

    /**
     * Получение данных письма из вебэлемента
     */
    private static Message readMessageParamsFromWebElement(WebElement webElement) {
        String subject = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span")).getText();
//        log.info(subject);
        //получаем получателя письма
        String recipient = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-FromText']")).getText();
//        log.info(recipient);
        //получаем текст письма
        String text = webElement.findElement(By.xpath(".//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/span")).getText();
//        log.info(text);

        return new Message(recipient, subject, text);
    }

    /**
     * Ищем письмо и переходим в него
     */
    public static void findAndOpenDraft(Message message) {
        for (WebElement webElement : webElementDraftList) {
            if (message.equals(readMessageParamsFromWebElement(webElement))) {
                base.clickElement(webElement);
                log.info("Открыли письмо");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }

    /**
     * Ищем письмо message в списке черновиков
     */
    public static void checkInDrafts(Message message) {
        ArrayList<Message> listMes = takeMessList();
        int i = 0;
        boolean b = true;
        for (Message mes : listMes) {
            if (mes.equals(message)) {
                log.info("Письмо (тема: " + mes.subject + ", получатель:" + mes.recipient + ", текст: " + mes.text + ") есть в списке черновиков, Номенр по списку " + i);
                return;
            }
            i++;
        }
        log.error("Письма нет в списке черновиков");
        ;
    }


    public static void selectCheckBox(ArrayList<Message> listMesToSelect) {
        Integer i = 0;
        for (WebElement webElement : webElementDraftList) {
            Message mes = readMessageParamsFromWebElement(webElement);
            if (listMesToSelect.contains(mes)) {
                log.info("Нашли письмо в списке черновиков по номером " + i);
                selectCheckBox(webElement);
            }
            i++;
        }
        log.error("Письма нет в списке черновиков, поставить чекбокс не удалось");
        ;
    }


    private static void selectCheckBox(WebElement webElement) {
        if (!webElement.findElement(By.xpath(xpathCheckBox)).isSelected()) {
            webElement.findElement(By.xpath(xpathCheckBox)).click();
            log.info("Проставили чекбокс");
        }
    }

    public static void selectAllCheckBox() {
        for (WebElement webElement : webElementDraftList) {
            selectCheckBox(webElement);
        }
    }

    public static void deleteSelected() {
        base.clickElement(button_delete);
        log.info("Нажали кнопку Удалить");
    }

}


