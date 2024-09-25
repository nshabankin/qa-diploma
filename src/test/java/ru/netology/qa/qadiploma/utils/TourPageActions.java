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
     * Проверка сообщения об ошибке при невалидном значении номера карты.
     */
    public void checkNumberInvalidInputErrorNotification() {
        elements.getCardNumberInputError().shouldBe(Condition.visible).shouldHave(text("Неверный формат"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении номера карты.
     */
    public void checkNumberEmptyInputErrorNotification() {
        elements.getCardNumberInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном значении месяца.
     */
    public void checkMonthInvalidInputErrorNotification() {
        elements.getCardMonthInputError().shouldBe(Condition.visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении месяца.
     */
    public void checkMonthEmptyInputErrorNotification() {
        elements.getCardMonthInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном значении года.
     */
    public void checkYearInvalidInputErrorNotification() {
        elements.getCardYearInputError().shouldBe(Condition.visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении года.
     */
    public void checkYearEmptyInputErrorNotification() {
        elements.getCardYearInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном имени владельца.
     */
    public void checkHolderInvalidInputErrorNotification() {
        elements.getCardHolderInputError().shouldBe(Condition.visible).shouldHave(text("Неверный формат"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении имени владельца.
     */
    public void checkHolderEmptyInputErrorNotification() {
        elements.getCardHolderInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    /**
     * Проверка сообщения об ошибке при невалидном CVC/CVV.
     */
    public void checkCvcInvalidInputErrorNotification() {
        elements.getCardCvvInputError().shouldBe(Condition.visible).shouldHave(text("Неверный формат"));
    }

    /**
     * Проверка сообщения об ошибке при пустом значении CVC/CVV.
     */
    public void checkCvcEmptyInputErrorNotification() {
        elements.getCardCvvInputError().shouldBe(Condition.visible).shouldHave(text("Поле обязательно для заполнения"));
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
