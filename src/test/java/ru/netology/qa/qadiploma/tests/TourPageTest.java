package ru.netology.qa.qadiploma.tests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.qa.qadiploma.data.DataGenerator;
import ru.netology.qa.qadiploma.data.TestData;
import ru.netology.qa.qadiploma.pages.TourPageElements;
import ru.netology.qa.qadiploma.utils.TourPageActions;

/**
 * Класс для тестов страницы покупки тура. Использует данные из класса TestData.
 */
public class TourPageTest {

    private static TourPageActions actions;    // Класс для выполнения действий на странице

    private static TestData approvedUserData;  // Тестовые данные для успешной покупки
    private static TestData declinedUserData;  // Тестовые данные для неуспешной покупки

    private static TestData invalidNumberUserData; // Тестовые данные с невалидным номером карты
    private static TestData emptyNumberUserData; // Тестовые данные с пустым значением номера карты

    private static TestData invalidMonthUserData; // Тестовые данные с невалидным значением месяца
    private static TestData expiredMonthUserData; // Тестовые данные с истёкшим значением месяца
    private static TestData emptyMonthUserData; // Тестовые данные с пустым значением месяца

    private static TestData invalidYearUserData; // Тестовые данные с невалидным значением года
    private static TestData expiredYearUserData; // Тестовые данные с истёкшим значением года
    private static TestData emptyYearUserData; // Тестовые данные с пустым значением года

    private static TestData invalidHolderNumbersUserData; // Тестовые данные с невалидным значением имени владельца (содержащие цифры)
    private static TestData invalidHolderCyrillicUserData; // Тестовые данные с невалидным значением имени владельца (содержащие кириллицу)
    private static TestData emptyHolderUserData; // Тестовые данные с пустым значением имени владельца

    private static TestData invalidCvvUserData; // Тестовые данные с невалидным значением CVC/CVV
    private static TestData emptyCvvUserData; // Тестовые данные с пустым значением CVC/CVV

    private final int sleepTime = 20000; // Время задержки

    @BeforeAll
    public static void setup() {

        // Класс, который хранит элементы страницы
        TourPageElements elements = new TourPageElements();  // Инициализация элементов страницы

        actions = new TourPageActions(elements);  // Инициализация действий с элементами

        // Создание объекта с тестовыми данными для успешной покупки
        approvedUserData = DataGenerator.getApprovedUserData();

        // Создание объекта с тестовыми данными для неуспешной покупки
        declinedUserData = DataGenerator.getDeclinedUserData();

        // Создание объекта с тестовыми данными с невалидным номером карты
        invalidNumberUserData = DataGenerator.getInvalidNumberUserData();

        // Создание объекта с тестовыми данными с пустым значением номера карты
        emptyNumberUserData = DataGenerator.getEmptyNumberUserData();

        // Создание объекта с тестовыми данными с невалидным месяцем
        invalidMonthUserData = DataGenerator.getInvalidMonthUserData();
        expiredMonthUserData = DataGenerator.getExpiredMonthUserData();

        // Создание объекта с тестовыми данными с пустым значением месяца
        emptyMonthUserData = DataGenerator.getEmptyMonthUserData();

        // Создание объекта с тестовыми данными с невалидным годом
        invalidYearUserData = DataGenerator.getInvalidYearUserData();
        expiredYearUserData = DataGenerator.getExpiredYearUserData();

        // Создание объекта с тестовыми данными с пустым значением года
        emptyYearUserData = DataGenerator.getEmptyYearUserData();

        // Создание объекта с тестовыми данными с невалидным именем владельца
        invalidHolderNumbersUserData = DataGenerator.getInvalidHolderNumbersUserData();
        invalidHolderCyrillicUserData = DataGenerator.getInvalidHolderCyrillicUserData();

        // Создание объекта с тестовыми данными с пустым значением имени владельца
        emptyHolderUserData = DataGenerator.getEmptyHolderUserData();

        // Создание объекта с тестовыми данными с невалидным CVC/CVV
        invalidCvvUserData = DataGenerator.getInvalidCvcUserData();

        // Создание объекта с тестовыми данными с пустым значением CVC/CVV
        emptyCvvUserData = DataGenerator.getEmptyCvcUserData();
    }

    @Test
    @DisplayName("Tour title element should be visible")
    public void shouldDisplayTourTitle() {
        actions.openPage(); // Открываем страницу

        actions.checkTourTitleVisibility(); // Проверяем видимость заголовка тура
    }

    @SneakyThrows
    @Test
    @DisplayName("Should show successful debit card purchase")
    public void shouldShowSuccessfulDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(approvedUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(approvedUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(approvedUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(approvedUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(approvedUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        Thread.sleep(sleepTime); // Задержка в миллисекундах
        actions.checkSuccessNotification();  // Проверяем успешное завершение покупки
    }

    @SneakyThrows
    @Test
    @DisplayName("Should show unsuccessful debit card purchase")
    public void shouldShowUnsuccessfulDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(declinedUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(declinedUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(declinedUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(declinedUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(declinedUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        Thread.sleep(sleepTime); // Задержка в миллисекундах
        actions.checkErrorNotification();  // Проверяем неуспешное завершение покупки
    }

    @SneakyThrows
    @Test
    @DisplayName("Should show successful credit purchase")
    public void shouldShowSuccessfulCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(approvedUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(approvedUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(approvedUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(approvedUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(approvedUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        Thread.sleep(sleepTime); // Задержка в миллисекундах
        actions.checkSuccessNotification();  // Проверяем успешное завершение покупки
    }

    @SneakyThrows
    @Test
    @DisplayName("Should show unsuccessful credit purchase")
    public void shouldShowUnsuccessfulCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(declinedUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(declinedUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(declinedUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(declinedUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(declinedUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        Thread.sleep(sleepTime); // Задержка в миллисекундах
        actions.checkErrorNotification();  // Проверяем неуспешное завершение покупки
    }

    @Test
    @DisplayName("Should show invalid number error for debit card purchase")
    public void shouldShowInvalidNumberErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid number error for credit purchase")
    public void shouldShowInvalidNumberErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty number error for debit card purchase")
    public void shouldShowEmptyNumberErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty number error for credit purchase")
    public void shouldShowEmptyNumberErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid month error for debit card purchase")
    public void shouldShowInvalidMonthErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid month error for credit purchase")
    public void shouldShowInvalidMonthErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show expired month error for debit card purchase")
    public void shouldShowExpiredMonthErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(expiredMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(expiredMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(expiredMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(expiredMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(expiredMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkExpiredMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show expired month error for credit purchase")
    public void shouldShowExpiredMonthErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(expiredMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(expiredMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(expiredMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(expiredMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(expiredMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkExpiredMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty month error for debit card purchase")
    public void shouldShowEmptyMonthErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty month error for credit purchase")
    public void shouldShowEmptyMonthErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid year error for debit card purchase")
    public void shouldShowInvalidYearErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid year error for credit purchase")
    public void shouldShowInvalidYearErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show expired year error for debit card purchase")
    public void shouldShowExpiredYearErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(expiredYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(expiredYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(expiredYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(expiredYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(expiredYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkExpiredYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show expired year error for credit purchase")
    public void shouldShowExpiredYearErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(expiredYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(expiredYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(expiredYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(expiredYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(expiredYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkExpiredYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty year error for debit card purchase")
    public void shouldShowEmptyYearErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty year error for credit purchase")
    public void shouldShowEmptyYearErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show error for invalid holder name containing numbers for debit card purchase")
    public void shouldShowInvalidHolderNumbersErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidHolderNumbersUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidHolderNumbersUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidHolderNumbersUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidHolderNumbersUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidHolderNumbersUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show error for invalid holder name containing cyrillic symbols for debit card purchase")
    public void shouldShowInvalidHolderCyrillicErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidHolderCyrillicUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidHolderCyrillicUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidHolderCyrillicUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidHolderCyrillicUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidHolderCyrillicUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show error for invalid holder name containing numbers for credit purchase")
    public void shouldShowInvalidHolderNumbersErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidHolderNumbersUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidHolderNumbersUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidHolderNumbersUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidHolderNumbersUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidHolderNumbersUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show error for invalid holder name containing cyrillic symbols for credit purchase")
    public void shouldShowInvalidHolderCyrillicErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidHolderCyrillicUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidHolderCyrillicUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidHolderCyrillicUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidHolderCyrillicUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidHolderCyrillicUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty holder name error for debit card purchase")
    public void shouldShowEmptyHolderErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyHolderUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyHolderUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyHolderUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyHolderUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyHolderUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty holder name error for credit purchase")
    public void shouldShowEmptyHolderErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyHolderUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyHolderUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyHolderUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyHolderUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyHolderUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid CVC/CVV error for debit card purchase")
    public void shouldShowInvalidCvvErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show invalid CVC/CVV error for credit purchase")
    public void shouldShowInvalidCvvErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkInvalidCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty CVC/CVV error for debit card purchase")
    public void shouldShowEmptyCvvErrorForDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    @DisplayName("Should show empty CVC/CVV error for credit purchase")
    public void shouldShowEmptyCvvErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkEmptyCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }
}

