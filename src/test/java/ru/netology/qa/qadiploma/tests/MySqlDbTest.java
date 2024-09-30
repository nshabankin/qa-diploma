package ru.netology.qa.qadiploma.tests;

import org.junit.jupiter.api.Test;
import ru.netology.qa.qadiploma.data.DataGenerator;
import ru.netology.qa.qadiploma.pages.TourPageElements;
import ru.netology.qa.qadiploma.utils.TourPageActions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlDbTest {

    TourPageElements elements = new TourPageElements();
    TourPageActions actions = new TourPageActions(elements);
    String card1Number = DataGenerator.getApprovedUserData().getCardNumber();
    String card2Number = DataGenerator.getDeclinedUserData().getCardNumber();

    private Connection getMySQLConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/app";
        String username = "app";
        String password = "pass";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Проверка отсутствия номеров карт в записях таблицы payment_entity.
     */
    @Test
    public void testPaymentEntityData() {

        // SQL запрос к таблице payment_entity
        String query = "SELECT id, amount, created, status, transaction_id FROM payment_entity";

        // Открытие страницы и получение отображенной цены тура
        actions.openPage();
        int tourPrice = actions.getTourPrice();

        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Получаем значения из таблицы
                String id = resultSet.getString("id");
                int amount = resultSet.getInt("amount");
                String created = resultSet.getString("created");
                String status = resultSet.getString("status");
                String transactionId = resultSet.getString("transaction_id");


                // Проверяем, что все поля корректны и не содержат чувствительной информации
                assertNotNull(id, "ID не должен быть null");
                assertEquals(tourPrice * 100, amount, "Amount должен соответствовать цене тура в копейках");
                assertNotNull(transactionId, "Transaction ID не должен быть null");
                assertNotNull(created, "Дата создания не должна быть null");
                assertNotNull(status, "Статус не должен быть null");
                assertFalse(id.contains(card1Number), "ID не должен содержать номер карты 1");
                assertFalse(id.contains(card2Number), "ID не должен содержать номер карты 2");
                assertFalse(transactionId.contains(card1Number), "Transaction ID не должен содержать номер карты 1");
                assertFalse(transactionId.contains(card2Number), "Transaction ID не должен содержать номер карты 2");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка отсутствия номеров карт в записях таблицы order_entity.
     */
    @Test
    public void testOrderEntityData() {

        // SQL запрос к таблице order_entity
        String query = "SELECT id, created, credit_id, payment_id FROM order_entity";

        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Получаем значения из таблицы
                String id = resultSet.getString("id");
                String created = resultSet.getString("created");
                String creditId = resultSet.getString("credit_id");
                String paymentId = resultSet.getString("payment_id");


                // Проверяем, что все поля корректны и не содержат чувствительной информации
                assertNotNull(id, "ID не должен быть null");
                assertNotNull(created, "Дата создания не должна быть null");
                assertFalse(id.contains(card1Number), "ID не должен содержать номер карты 1");
                assertFalse(id.contains(card2Number), "ID не должен содержать номер карты 2");
                assertFalse(creditId.contains(card1Number), "Credit ID не должен содержать номер карты 1");
                assertFalse(creditId.contains(card2Number), "Credit ID не должен содержать номер карты 2");
                assertFalse(paymentId.contains(card1Number), "Payment ID не должен содержать номер карты 1");
                assertFalse(paymentId.contains(card2Number), "Payment ID не должен содержать номер карты 2");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка отсутствия номеров карт в записях таблицы credit_request_entity.
     */
    @Test
    public void testCreditRequestEntityData() {

        // SQL запрос к таблице credit_request_entity
        String query = "SELECT id, bank_id, created, status FROM credit_request_entity";

        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Получаем значения из таблицы
                String id = resultSet.getString("id");
                String bankId = resultSet.getString("bank_id");
                String created = resultSet.getString("created");
                String status = resultSet.getString("status");

                // Проверяем, что все поля корректны и не содержат чувствительной информации
                assertNotNull(id, "ID не должен быть null");
                assertNotNull(created, "Дата создания не должна быть null");
                assertNotNull(status, "Статус не должен быть null");
                assertFalse(id.contains(card1Number), "ID не должен содержать номер карты 1");
                assertFalse(id.contains(card2Number), "ID не должен содержать номер карты 2");
                assertFalse(bankId.contains(card1Number), "Bank ID не должен содержать номер карты 1");
                assertFalse(bankId.contains(card2Number), "Bank ID не должен содержать номер карты 2");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
