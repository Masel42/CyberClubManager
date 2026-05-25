package com.cyberclub.ui;

import com.cyberclub.dao.ClientDao;
import com.cyberclub.model.Client;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainApp extends JFrame {
    private JTextArea textArea;

    public MainApp() {
        setTitle("Cyber Club Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JButton loadButton = new JButton("Загрузить клиентов");
        loadButton.addActionListener(e -> loadClients());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(loadButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void loadClients() {
        try {
            ClientDao dao = new ClientDao();
            List<Client> clients = dao.getAllClients();
            StringBuilder sb = new StringBuilder();
            for (Client c : clients) {
                sb.append(String.format("ID: %d | Ник: %s | ФИО: %s | Телефон: %s | Баланс: %.2f\n",
                        c.getId(), c.getNickname(), c.getFullName(), c.getPhone(), c.getBalance()));
            }
            textArea.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}