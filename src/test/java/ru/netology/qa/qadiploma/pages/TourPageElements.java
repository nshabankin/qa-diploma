package ru.netology.qa.qadiploma.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class TourPageElements {

    // Элемент заголовка тура
    private final SelenideElement tourTitle = $(".Order_cardHeading__2PyrG");

    // Кнопка переключения на оплату по дебетовой карте
    private final SelenideElement buyWithDebitCardButton = $$(".button__text").findBy(text("Купить"));

    // Кнопка переключения на покупку в кредит
    private final SelenideElement buyOnCreditButton = $$(".button__text").findBy(text("Купить в кредит"));

    // Поле для ввода номера карты
    private final SelenideElement cardNumberInput = $$(".input__top").findBy(text("Номер карты"))
        .closest(".input__inner").$(".input__control");

    // Поле для ввода месяца
    private final SelenideElement cardMonthInput = $$(".input__top").findBy(text("Месяц"))
        .closest(".input__inner").$(".input__control");

    // Поле для ввода года
    private final SelenideElement cardYearInput = $$(".input__top").findBy(text("Год"))
        .closest(".input__inner").$(".input__control");

    // Поле для ввода имени владельца карты
    private final SelenideElement cardHolderInput = $$(".input__top").findBy(text("Владелец"))
        .closest(".input__inner").$(".input__control");

    // Поле для ввода CVC/CVV кода
    private final SelenideElement cardCvvInput = $$(".input__top").findBy(text("CVC/CVV"))
        .closest(".input__inner").$(".input__control");

    // Кнопка "Продолжить" для завершения покупки
    private final SelenideElement submitButton = $$(".button__text").findBy(text("Продолжить"));

    // Сообщение об успешной оплате
    private final SelenideElement successNotification = $$(".notification__title").findBy(text("Успешно"));

    // Сообщение об ошибке
    private final SelenideElement errorNotification = $$(".notification__title").findBy(text("Ошибка"));
}
