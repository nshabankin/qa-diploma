package ru.netology.qa.qadiploma.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.AllArgsConstructor;
import ru.netology.qa.qadiploma.pages.TourPageElements;

@AllArgsConstructor
public class TourPageActions {

    private final TourPageElements elements;

    /**
     * Переключение на оплату по дебетовой карте.
     */
    public void selectDebitCardPayment() {
        elements.getBuyWithDebitCardButton().click();
    }

    /**
     * Переключение на оплату в кредит.
     */
    public void selectCreditPayment() {
        elements.getBuyOnCreditButton().click();
    }

    /**
     * Ввод номера карты.
     *
     * @param cardNumber Номер карты.
     */
    public void enterCardNumber(String cardNumber) {
        elements.getCardNumberInput().setValue(cardNumber);
    }

    /**
     * Ввод месяца.
     *
     * @param month Месяц.
     */
    public void enterCardMonth(String month) {
        elements.getCardMonthInput().setValue(month);
    }

    /**
     * Ввод года.
     *
     * @param year Год.
     */
    public void enterCardYear(String year) {
        elements.getCardYearInput().setValue(year);
    }

    /**
     * Ввод имени владельца карты.
     *
     * @param cardHolderName Имя владельца карты.
     */
    public void enterCardHolderName(String cardHolderName) {
        elements.getCardHolderInput().setValue(cardHolderName);
    }

    /**
     * Ввод CVC/CVV кода.
     *
     * @param cvv Код CVC/CVV.
     */
    public void enterCardCvv(String cvv) {
        elements.getCardCvvInput().setValue(cvv);
    }

    /**
     * Завершение покупки, нажимая на кнопку "Продолжить".
     */
    public void submitPurchase() {
        elements.getSubmitButton().click();
    }

    /**
     * Проверка успешного завершения покупки.
     */
    public void checkSuccessNotification() {
        elements.getSuccessNotification().shouldBe(Condition.visible);
    }

    /**
     * Проверка сообщения об ошибке при покупке.
     */
    public void checkErrorNotification() {
        elements.getErrorNotification().shouldBe(Condition.visible);
    }

    /**
     * Открытие страницы.
     */
    public void openPage() {
        Selenide.open("http://localhost:8080/");
    }

    /**
     * Проверка сообщения об ошибке ввода номера карты.
     */
    public void checkNumberInputErrorNotification() {
        elements.getCardNumberInputError().shouldBe(Condition.visible);
    }

    /**
     * Проверка сообщения об ошибке ввода месяца.
     */
    public void checkMonthInputErrorNotification() {
        elements.getCardMonthInputError().shouldBe(Condition.visible);
    }

    /**
     * Проверка сообщения об ошибке ввода года.
     */
    public void checkYearInputErrorNotification() {
        elements.getCardYearInputError().shouldBe(Condition.visible);
    }

    /**
     * Проверка сообщения об ошибке ввода имени владельца.
     */
    public void checkHolderInputErrorNotification() {
        elements.getCardHolderInputError().shouldBe(Condition.visible);
    }

    /**
     * Проверка сообщения об ошибке ввода CVC/CVV.
     */
    public void checkCvcInputErrorNotification() {
        elements.getCardCvvInput().shouldBe(Condition.visible);
    }
}
