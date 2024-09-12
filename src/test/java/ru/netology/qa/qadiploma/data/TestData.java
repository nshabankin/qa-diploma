package ru.netology.qa.qadiploma.data;

import lombok.Value;

/**
 * Этот класс предназначен для хранения тестовых данных,
 * которые будут использоваться в тестах на покупку тура.
 * Аннотация @Value от Lombok делает класс иммутабельным,
 * автоматически генерируя геттеры, конструктор для всех полей и
 * метод toString().
 */

@Value
public class TestData {

    // Номер карты
    String cardNumber;

    // Месяц окончания срока действия карты
    String cardMonth;

    // Год окончания срока действия карты
    String cardYear;

    // Имя владельца карты
    String cardHolderName;

    // CVC/CVV код карты
    String cardCvv;
}
