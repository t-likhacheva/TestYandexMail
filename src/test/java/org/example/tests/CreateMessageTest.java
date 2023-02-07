package org.example.tests;


import org.example.help_methods.WebDriverSettings;
import org.example.models.Message;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.example.methods_front.drafts.DraftsMethods.*;
import static org.example.methods_front.inbox.InboxMethods.*;
import static org.example.methods_front.login.LoginGroupMethods.login;
import static org.example.methods_front.login.LoginGroupMethods.logout;
import static org.example.methods_front.newMessage.OpenedMessageMethods.closeMessage;
import static org.example.methods_front.newMessage.OpenedMessageMethods.fillMessage;


public class CreateMessageTest extends WebDriverSettings {
    /**
     * Кол-во создаваемых сообщений
     */
    private Integer count = 4;
    /**
     * Массив с индексами для выделения и удаления,максимальный должен быть меньше count
     */
    private Integer[] indexArray = {2, 3};

    @Test(description = "Проверка создания сообщения и сохранения в черновиках»", groups = {"Base"}, priority = 100)
    public void testCreateMessage() {
        Message message = new Message();
        login();
        openNewMail();
        fillMessage(message);
        closeMessage();
        goToInput();
        goToDrafts();
        checkInDrafts(message);
        findAndOpenDraft(message);
        //    compareMessageParams(message);
        closeMessage();
        logout();
    }

    @Test(description = "Проверка создания 5 сообщений, проставление флага важности 2 письмам, частичное удаление  писем (важность, чекбокс по совпадению)»", groups = {"Base"}, priority = 90)
    public void testComplex() {
        login();
        ArrayList<Message> mesList = fillArrayDrafts(count);
        ArrayList<Message> mesListCheked = getPartOfArray(mesList, indexArray);
        goToInput();
        goToDrafts();
        selectCheckBox(mesListCheked);
        deleteSelected();
        logout();
    }

    /**
     * Создание подмассива сообщений - выборка из   mesList с индексами   indexArray
     * @return Возвращает созданный подмассив
     */
    private static ArrayList<Message> getPartOfArray(ArrayList<Message> mesList, Integer[] indexArray) {
        ArrayList<Message> mesListCheked = new ArrayList<Message>();
        for (int i : indexArray) {
            mesListCheked.add(mesList.get(i));
        }
        return mesListCheked;
    }


    /**
     * Создание count писем-черновиков
     *
     * @param count
     * @return созданные письма
     */
    public ArrayList<Message> fillArrayDrafts(Integer count) {
        ArrayList<Message> mesList = new ArrayList<Message>(count);
        for (int i = 0; i < count; i++) {
            Message mes = new Message();
            mesList.add(mes);
            openNewMail();
            fillMessage(mes);
            closeMessage();
        }
        return mesList;
    }


}
