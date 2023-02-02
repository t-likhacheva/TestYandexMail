package org.example.tests;


import org.example.help_methods.WebDriverSettings;
import org.testng.annotations.Test;

import static org.example.methods_front.drafts.DraftsMethods.checkInDrafts;
import static org.example.methods_front.inbox.InboxMethods.*;
import static org.example.methods_front.login.LoginGroupMethods.*;
import static org.example.methods_front.newMessage.NewMessageMethods.*;


public class CreateMessageTest extends WebDriverSettings {

    @Test
    public void testCreateMessage() {
        login();
        openNewMail();
        Message message = new Message("Добро пожаловать", "Приветствие","ififi@ya.ru");
        fillMessage(message);
        goToDrafts();
        checkInDrafts(message);
        logout();
    }




}
