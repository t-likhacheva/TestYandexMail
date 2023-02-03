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


public class CreateMessageTest extends WebDriverSettings {
  private static   int ii = 70193;
  private static Message message = new Message("ififi@ya.ru", "Добро пожаловать" + ii, "Приветствую, ififi" + ii);


    @Test
    public void testCreateMessage() {
        login();
        openNewMail();
        fillMessage(message);
        closeMessage();
        goToInput();
        goToDrafts();
        checkInDrafts(message);
        findAndOpenDraft(message);
        compareMessageParams(message);
        logout();
    }


}
