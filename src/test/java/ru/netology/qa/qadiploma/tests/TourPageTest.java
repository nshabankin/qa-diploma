package ru.netology.qa.qadiploma.tests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qa.qadiploma.data.DataGenerator;
import ru.netology.qa.qadiploma.data.TestData;
import ru.netology.qa.qadiploma.pages.TourPageElements;
import ru.netology.qa.qadiploma.utils.TourPageActions;

/**
 * Класс для тестов страницы покупки тура. Использует данные из класса TestData.
 */
public class TourPageTest {

    private TourPageElements elements;  // Класс, который хранит элементы страницы
    private TourPageActions actions;    // Класс для выполнения действий на странице
    private TestData approvedUserData;  // Тестовые данные для успешной покупки
    private TestData declinedUserData;  // Тестовые данные для неуспешной покупки
    private int sleepTime = 15000; // Время задержки

    @BeforeEach
    public void setup() {
        elements = new TourPageElements();  // Инициализация элементов страницы
        actions = new TourPageActions(elements);  // Инициализация действий с элементами

        // Создание объекта с тестовыми данными для успешной покупки
        approvedUserData = DataGenerator.getApprovedUserData();

        // Создание объекта с тестовыми данными для неуспешной покупки
        declinedUserData = DataGenerator.getDeclinedUserData();
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
}

