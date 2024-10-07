package ru.netology.qa.qadiploma.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbActions {

    private final String url = System.getProperty("dbUrl");
    private final String username = System.getProperty("dbUsername");
    private final String password = System.getProperty("dbPassword");

    // Получение соединения с БД
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}