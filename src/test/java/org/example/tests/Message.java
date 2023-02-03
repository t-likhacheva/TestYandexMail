package org.example.tests;

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



}
