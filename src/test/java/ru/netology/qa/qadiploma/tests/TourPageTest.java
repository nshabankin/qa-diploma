package ru.netology.qa.qadiploma.tests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
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
    private static TestData emptyMonthUserData; // Тестовые данные с пустым значением месяца

    private static TestData invalidYearUserData; // Тестовые данные с невалидным значением года
    private static TestData emptyYearUserData; // Тестовые данные с пустым значением года

    private static TestData invalidHolderUserData; // Тестовые данные с невалидным значением имени владельца
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

        // Создание объекта с тестовыми данными с пустым значением месяца
        emptyMonthUserData = DataGenerator.getEmptyMonthUserData();

        // Создание объекта с тестовыми данными с невалидным годом
        invalidYearUserData = DataGenerator.getInvalidYearUserData();

        // Создание объекта с тестовыми данными с пустым значением года
        emptyYearUserData = DataGenerator.getEmptyYearUserData();

        // Создание объекта с тестовыми данными с невалидным именем владельца
        invalidHolderUserData = DataGenerator.getInvalidHolderUserData();

        // Создание объекта с тестовыми данными с пустым значением имени владельца
        emptyHolderUserData = DataGenerator.getEmptyHolderUserData();

        // Создание объекта с тестовыми данными с невалидным CVC/CVV
        invalidCvvUserData = DataGenerator.getInvalidCvcUserData();

        // Создание объекта с тестовыми данными с пустым значением CVC/CVV
        emptyCvvUserData = DataGenerator.getEmptyCvcUserData();
    }

    @SneakyThrows
    @Test
    public void testSuccessfulDebitCardPurchase() {
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
    public void testUnsuccessfulDebitCardPurchase() {
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
    public void testSuccessfulCreditPurchase() {
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
    public void testUnsuccessfulCreditPurchase() {
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
    public void shouldShowNumberErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowNumberErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowEmptyNumberErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyNumberUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyNumberUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyNumberUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyNumberUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyNumberUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
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
        actions.checkNumberInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowMonthErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowMonthErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowEmptyMonthErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyMonthUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyMonthUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyMonthUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyMonthUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyMonthUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
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
        actions.checkMonthInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowYearErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowYearErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowEmptyYearErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyYearUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyYearUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyYearUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyYearUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyYearUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
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
        actions.checkYearInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowHolderErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidHolderUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidHolderUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidHolderUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidHolderUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidHolderUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowHolderErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidHolderUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidHolderUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidHolderUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidHolderUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidHolderUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowEmptyHolderErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyHolderUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyHolderUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyHolderUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyHolderUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyHolderUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
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
        actions.checkHolderInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowCvvErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowCvvErrorForCreditPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectCreditPayment();  // Выбираем оплату кредитом

        // Передаем тестовые данные в методы
        actions.enterCardNumber(invalidCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(invalidCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(invalidCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(invalidCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(invalidCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
    public void shouldShowEmptyCvvErrorForDebitPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(emptyCvvUserData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(emptyCvvUserData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(emptyCvvUserData.getCardYear());  // Вводим год
        actions.enterCardHolderName(emptyCvvUserData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(emptyCvvUserData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        actions.checkCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }

    @Test
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
        actions.checkCvcInputErrorNotification();  // Проверяем сообщение об ошибке в поле
    }
}

