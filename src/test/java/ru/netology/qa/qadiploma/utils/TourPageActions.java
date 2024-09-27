package ru.netology.qa.qadiploma.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.AllArgsConstructor;
import ru.netology.qa.qadiploma.pages.TourPageElements;

import static com.codeborne.selenide.Condition.text;

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
        elements.getSuccessNotification().shouldBe(Condition.visible).shouldHave(text("Успешно"));
    }

    /**
     * Проверка сообщения об ошибке при покупке.
     */
    public void checkErrorNotification() {
        elements.getErrorNotification().shouldBe(Condition.visible).shouldHave(text("Ошибка"));
    }

    /**
     * Открытие страницы.
     */
    public void openPage() {
        Selenide.open("http://localhost:8082/");
    }

    /**
     * Проверка сообщения об ошибке при невалидном значении номера карты.
     */
    public void checkInvalidNumberInputErrorNotification() {
        elements.getCardNumberInputError().shouldBe(Condition.visible).shouldHave(text("Неверный формат"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении номера карты.
     */
    public void checkEmptyNumberInputErrorNotification() {
        elements.getCardNumberInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном значении месяца.
     */
    public void checkInvalidMonthInputErrorNotification() {
        elements.getCardMonthInputError().shouldBe(Condition.visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    /**
     * Проверка сообщения об ошибке при истёкшем значении месяца.
     */
    public void checkExpiredMonthInputErrorNotification() {
        elements.getCardMonthInputError().shouldBe(Condition.visible).shouldHave(text("Истёк срок действия карты"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении месяца.
     */
    public void checkEmptyMonthInputErrorNotification() {
        elements.getCardMonthInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном значении года.
     */
    public void checkInvalidYearInputErrorNotification() {
        elements.getCardYearInputError().shouldBe(Condition.visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    /**
     * Проверка сообщения об ошибке при истёкшем значении года.
     */
    public void checkExpiredYearInputErrorNotification() {
        elements.getCardYearInputError().shouldBe(Condition.visible).shouldHave(text("Истёк срок действия карты"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении года.
     */
    public void checkEmptyYearInputErrorNotification() {
        elements.getCardYearInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном имени владельца.
     */
    public void checkInvalidHolderInputErrorNotification() {
        elements.getCardHolderInputError().shouldBe(Condition.visible).shouldHave(text("Неверный формат"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении имени владельца.
     */
    public void checkEmptyHolderInputErrorNotification() {
        elements.getCardHolderInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном CVC/CVV.
     */
    public void checkInvalidCvcInputErrorNotification() {
        elements.getCardCvvInputError().shouldBe(Condition.visible).shouldHave(text("Неверный формат"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении CVC/CVV.
     */
    public void checkEmptyCvcInputErrorNotification() {
        elements.getCardCvvInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка видимости заголовка тура.
     */
    public void checkTourTitleVisibility() {
        elements.getTourTitle().shouldBe(Condition.visible);
    }

    /**
     * Извлечение цены тура из элементов страницы.
     */
    public int getTourPrice() {

        // Извлекаем текст цены и очищаем строку от лишних символов, оставляем только цифры
        String priceText = elements.getPriceElement().getText().replaceAll("[^0-9]", "");

        // Возвращаем int из String
        return Integer.parseInt(priceText);
    }
}
