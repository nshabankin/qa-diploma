package ru.netology.qa.qadiploma.tests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qa.qadiploma.data.TestData;
import ru.netology.qa.qadiploma.pages.TourPageElements;
import ru.netology.qa.qadiploma.utils.TourPageActions;

/**
 * Класс для тестов страницы покупки тура. Использует данные из класса TestData.
 */
public class TourPageTest {

    private TourPageElements elements;  // Класс, который хранит элементы страницы
    private TourPageActions actions;    // Класс для выполнения действий на странице
    private TestData testData;          // Тестовые данные для покупки

    @BeforeEach
    public void setup() {
        elements = new TourPageElements();  // Инициализация элементов страницы
        actions = new TourPageActions(elements);  // Инициализация действий с элементами

        // Создание объекта с тестовыми данными для покупки по дебетовой карте
        testData = new TestData(
                "4444 4444 4444 4441",
                "12",
                "24",
                "Ivan Ivanov",
                "123"
        );
    }

    @SneakyThrows
    @Test
    public void testSuccessfulDebitCardPurchase() {
        actions.openPage();  // Открываем страницу
        actions.selectDebitCardPayment();  // Выбираем оплату по дебетовой карте

        // Передаем тестовые данные в методы
        actions.enterCardNumber(testData.getCardNumber());  // Вводим номер карты
        actions.enterCardMonth(testData.getCardMonth());  // Вводим месяц
        actions.enterCardYear(testData.getCardYear());  // Вводим год
        actions.enterCardHolderName(testData.getCardHolderName());  // Вводим имя владельца
        actions.enterCardCvv(testData.getCardCvv());  // Вводим CVC/CVV код

        actions.submitPurchase();  // Нажимаем на кнопку "Продолжить"
        Thread.sleep(10000); // Задержка в миллисекундах
        actions.checkSuccessNotification();  // Проверяем успешное завершение покупки
    }
}

