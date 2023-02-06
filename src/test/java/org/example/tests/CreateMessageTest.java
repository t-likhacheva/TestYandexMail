package org.example.tests;


import org.example.help_methods.WebDriverSettings;
import org.example.models.Message;
import org.testng.annotations.Test;

import static org.example.methods_front.drafts.DraftsMethods.checkInDrafts;
import static org.example.methods_front.drafts.DraftsMethods.findAndOpenDraft;
import static org.example.methods_front.inbox.InboxMethods.*;
import static org.example.methods_front.login.LoginGroupMethods.login;
import static org.example.methods_front.login.LoginGroupMethods.logout;
import static org.example.methods_front.newMessage.OpenedMessageMethods.*;
import static org.example.models.Message.fillObjectRandomParameters;


public class CreateMessageTest extends WebDriverSettings {
    public   Message message ;


    @Test(description = "Проверка создания сообщения и сохранения в черновиках»", groups = {"Base"}, priority = 100)
    public void testCreateMessage()  {
        message= fillObjectRandomParameters();
        login();
        openNewMail();
        fillMessage(message);
        closeMessage();
        goToInput();
        goToDrafts();
        checkInDrafts(message);
        findAndOpenDraft(message);
       // compareMessageParams(message);
        closeMessage();
        logout();
    }
    @Test(description = "Проверка создания 5 сообщений, проставление флага важности 2 письмам, частичное удаление  писем (важность, чекбокс по совпадению)»", groups = {"Base"}, priority = 90)
    public void testComplex()  {
//        message= fillObjectRandomParameters();
        login();
        openNewMail();
//        fillMessage(message);
//        closeMessage();
//        goToInput();
//        goToDrafts();
//        checkInDrafts(message);
//        findAndOpenDraft(message);
        // compareMessageParams(message);
        closeMessage();
        logout();
    }



}
