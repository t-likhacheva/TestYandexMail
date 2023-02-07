package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

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
     * Создание подмассива сообщений - выборка из   mesList с индексами   indexArray
     *
     * @return Возвращает созданный подмассив
     */
    public static ArrayList<Message> getPartOfArray(ArrayList<Message> mesList, Integer[] indexArray) {
        ArrayList<Message> mesListCheked = new ArrayList<Message>();
        for (int i : indexArray) {
            mesListCheked.add(mesList.get(i));
        }
        return mesListCheked;
    }

    @Override
    public String toString() {
        return "Message [recipient=" + recipient
                + ", subject=" + subject
                + "]";
    }
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
