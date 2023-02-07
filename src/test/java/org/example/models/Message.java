package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Объект описывающий Сообщение
 */
@Data
@AllArgsConstructor
public class Message {
    public String recipient;
    public String subject;
    public String text;

    /**
     * Конструктор, заполняющий рандомными значениями
     */
    public Message() {
        int i= (int)(Math.random()*1000000);
        this.recipient = "recieverMail"+i+"@ya.ru";
        this.subject = "Тема: Добро пожаловать" + i;
        this.text = "Текст: Приветствую, ififi" + i ;
    }


}
