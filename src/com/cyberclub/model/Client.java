package com.cyberclub.model;

import java.math.BigDecimal;

public class Client {
    private int id;
    private String nickname;
    private String fullName;
    private String phone;
    private BigDecimal balance;

    public Client() {}

    public Client(String nickname, String fullName, String phone, BigDecimal balance) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.phone = phone;
        this.balance = balance;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}