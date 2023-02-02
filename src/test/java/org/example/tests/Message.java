package org.example.tests;

/**
 * Объект описывающий Сообщение
 */
public class Message {
    public String text;
    public String Theme;
    public String Reciever;

    public Message(String text, String theme, String reciever) {
        this.text = text;
        Theme = theme;
        Reciever = reciever;
    }
}
