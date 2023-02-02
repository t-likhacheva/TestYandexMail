package org.example.tests;

/**
 * Объект описывающий Сообщение
 */
public class Message {
    public String text;
    public String theme;
    public String recipient;

    public Message(String text, String theme, String recipient) {
        this.text = text;
        this.theme = theme;
        this.recipient = recipient;
    }
}
