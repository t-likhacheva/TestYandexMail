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


    public static  Message fillObjectRandomParameters() {

        int i= (int)(Math.random()*1000000);
        return new Message("ififi"+i+"@ya.ru", "Добро пожаловать" + i, "Приветствую, ififi" + i);
    }
}
