package org.example.tests;


import org.example.help_methods.WebDriverSettings;
import org.testng.annotations.Test;

import static org.example.methods_front.drafts.DraftsMethods.checkInDrafts;
import static org.example.methods_front.drafts.DraftsMethods.findAndOpenDraft;
import static org.example.methods_front.inbox.InboxMethods.*;
import static org.example.methods_front.login.LoginGroupMethods.login;
import static org.example.methods_front.login.LoginGroupMethods.logout;
import static org.example.methods_front.newMessage.NewMessageMethods.closeMessage;
import static org.example.methods_front.newMessage.NewMessageMethods.fillMessage;


public class CreateMessageTest extends WebDriverSettings {

    @Test
    public void testCreateMessage() {
        login();
        int ii = 70190;
        openNewMail();
        Message message = new Message("ififi@ya.ru", "Добро пожаловать" + ii, "Приветствую, ififi" + ii);
        fillMessage(message);
        closeMessage();
        goToInput();
        goToDrafts();
        checkInDrafts(message);
        findAndOpenDraft(message);


        logout();
    }


}
