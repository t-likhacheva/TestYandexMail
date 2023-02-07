package org.example.methods_front.important;

import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.methods_front.drafts.DraftsMethods;
import org.example.models.Message;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
@Slf4j
public class ImportantPageMethods extends WebDriverSettings {
    public ImportantPageMethods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }



    public static boolean checkInImportant(Message message) {
        ArrayList<Message> listMes = DraftsMethods.takeMessList();
        int i = 0;
        for (Message mes : listMes) {
            if (mes.equalsWithoutRecipient(message)) {
                log.info(message.toString() + " есть в списке Важных, Номер по списку " + i);
                return true;
            }
            i++;
        }
        log.error(message.toString() +" нет в списке черновиков");
        return false;
    }
}
