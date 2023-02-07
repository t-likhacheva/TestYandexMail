package org.example.tests;


import org.example.help_methods.WebDriverSettings;
import org.example.methods_front.important.ImportantPageMethods;
import org.example.models.Message;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.example.methods_front.drafts.DraftsMethods.*;
import static org.example.methods_front.general.GeneralMethods.*;
import static org.example.methods_front.login.LoginGroupMethods.login;
import static org.example.methods_front.login.LoginGroupMethods.logout;
import static org.example.methods_front.newMessage.OpenedMessageMethods.*;

public class CreateMessageTest extends WebDriverSettings {
    /**
     * Кол-во создаваемых сообщений
     */
    private Integer count = 5;
    /**
     * Массивы с индексами для выделения и удаления,максимальный должен быть  строго меньше count
     */
    private Integer[] indexArrayToDel = {1, 2};
    private Integer[] indexArrayToImportant = {0, 4};

    @Test(description = "Проверка создания сообщения и сохранения в черновиках»", groups = {"Base"}, priority = 100)
    public void testCreateMessage() {
        Message message = new Message();
        login();
        openNewMail();
        fillMessage(message);
        closeMessage();
        goToInput();
        goToDrafts();
        Assert.assertTrue(checkInDrafts(message));
        findAndOpenDraft(message);
        compareMessageParams(message);
        closeMessage();
        logout();
    }

//    @Test(description = "Проверка создания 5 сообщений, проставление флага важности 2 письмам, частичное удаление  писем (важность, чекбокс по совпадению)»", groups = {"Base"}, priority = 90)
//    public void testComplex() {
//        login();
//        ArrayList<Message> mesList = fillArrayDrafts(count);
//        ArrayList<Message> mesListCheked = Message.getPartOfArray(mesList, indexArrayToDel);
//        goToInput();
//        goToDrafts();
//        logout();
//    }

    @Test(description = "Проверка создания 5 сообщений, проставление флага важности 2 письмам|через общее выдление, частичное удаление  писем (важность, чекбокс по совпадению)»", groups = {"Base"}, priority = 90)
    public void testComplex1() {
        login();
        ArrayList<Message> mesList = fillArrayDrafts(count);
        ArrayList<Message> mesListChekedToDelete = Message.getPartOfArray(mesList, indexArrayToDel);
        ArrayList<Message> mesListChekedToImportant = Message.getPartOfArray(mesList, indexArrayToImportant);
        goToInput();
        goToDrafts();
        selectCheckBox(mesListChekedToDelete);
        deleteSelected();
        selectCheckBox(mesListChekedToImportant);
        clickImportantFlagWhenSelected();
        goToImportant();
        //проверка наличия писем, которые были отмечены флагом важные на предыдущем шаге
        for (Message mes :
                mesListChekedToImportant) {
            Assert.assertTrue(ImportantPageMethods.checkInImportant(mes));
        }
        selectAllCheckBox();
        deleteSelected();
        goToDrafts();
        //проверка, что удаленные письма и удаленные важные письма отсутвуют, а оставшиеся присутсвуют в черновиках
        for (Message mes : mesList) {
            if (mesListChekedToDelete.contains(mes) || mesListChekedToImportant.contains(mes)) {
                Assert.assertFalse(checkInDrafts(mes));
            } else Assert.assertTrue(checkInDrafts(mes));
        }
        ;
        logout();
    }


}
