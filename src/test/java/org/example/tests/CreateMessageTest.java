package org.example.tests;


import lombok.extern.slf4j.Slf4j;
import org.example.help_methods.WebDriverSettings;
import org.example.models.Message;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.example.methods_front.drafts.DraftsMethods.*;
import static org.example.methods_front.inbox.InboxMethods.*;
import static org.example.methods_front.login.LoginGroupMethods.login;
import static org.example.methods_front.login.LoginGroupMethods.logout;
import static org.example.methods_front.newMessage.OpenedMessageMethods.*;

@Slf4j
public class CreateMessageTest extends WebDriverSettings {
    /**
     * Кол-во создаваемых сообщений
     */
    private Integer count = 3;
    /**
     * Массив с индексами для выделения и удаления,максимальный должен быть меньше count
     */
    private Integer[] indexArrayToDel = {1};
    private Integer[] indexArrayToImportant = {2};

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

    @Test(description = "Проверка создания 5 сообщений, проставление флага важности 2 письмам, частичное удаление  писем (важность, чекбокс по совпадению)»", groups = {"Base"}, priority = 90)
    public void testComplex() {
        login();
        ArrayList<Message> mesList = fillArrayDrafts(count);
        ArrayList<Message> mesListCheked = Message.getPartOfArray(mesList, indexArrayToDel);
        goToInput();
        goToDrafts();
        selectCheckBox(mesListCheked);
        deleteSelected();
        logout();
    }

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
        for (Message mes :
                mesListChekedToImportant) {
            Assert.assertTrue(checkInImportant(mes));
        }
          selectAllCheckBox();
        deleteSelected();
        goToDrafts();
        for (Message mes : mesList) {
            if (mesListChekedToDelete.contains(mes) || mesListChekedToImportant.contains(mes)) {
                Assert.assertFalse(checkInDrafts(mes));
            } else Assert.assertTrue(checkInDrafts(mes));
        }
        ;
        logout();
    }




}
