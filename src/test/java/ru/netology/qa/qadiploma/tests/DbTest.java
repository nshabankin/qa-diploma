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

public class DbTest {

    TourPageElements elements = new TourPageElements();
    TourPageActions actions = new TourPageActions(elements);

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/app";
        String username = "app";
        String password = "pass";
        return DriverManager.getConnection(url, username, password);
    }

    @Test
    public void testPaymentEntityData() {
        String query = "SELECT id, amount, created, status, transaction_id FROM payment_entity";

        actions.openPage();
        int tourPrice = actions.getTourPrice();
        String card1Number = DataGenerator.getApprovedUserData().getCardNumber();
        String card2Number = DataGenerator.getDeclinedUserData().getCardNumber();

        try (Connection connection = getConnection();
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
}
