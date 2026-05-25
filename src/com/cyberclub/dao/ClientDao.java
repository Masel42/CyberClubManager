package com.cyberclub.dao;

import com.cyberclub.model.Client;
import com.cyberclub.util.DatabaseConnector;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public void addClient(Client client) {
        String sql = "INSERT INTO clients (nickname, full_name, phone, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getNickname());
            stmt.setString(2, client.getFullName());
            stmt.setString(3, client.getPhone());
            stmt.setBigDecimal(4, client.getBalance());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // В реальном проекте лучше логировать
            throw new RuntimeException("Ошибка при добавлении клиента: " + e.getMessage(), e);
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setNickname(rs.getString("nickname"));
                c.setFullName(rs.getString("full_name"));
                c.setPhone(rs.getString("phone"));
                c.setBalance(rs.getBigDecimal("balance"));
                clients.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении клиентов: " + e.getMessage(), e);
        }
        return clients;
    }
}