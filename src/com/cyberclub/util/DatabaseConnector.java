package com.cyberclub.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            InputStream inputStream = DatabaseConnector.class.getClassLoader().getResourceAsStream("config.properties");

            if (inputStream == null) {
                throw new RuntimeException("Файл config.properties не найден в ресурсах приложения!");
            }

            try {
                props.load(inputStream);
            } finally {
                inputStream.close(); // Обязательно закрываем поток
            }

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            if (url == null || user == null || password == null || driver == null) {
                throw new RuntimeException("В файле config.properties отсутствуют обязательные параметры подключения к БД!");
            }

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    // Опционально: метод для закрытия соединения (полезно для тестов или перезапуска)
    public static void closeConnection() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}