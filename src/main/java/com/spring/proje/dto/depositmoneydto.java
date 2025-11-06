package com.spring.proje.dto;

public class depositmoneydto {

    private String tckn;
    private String password;
    private String balance;

    public String gettckn() {
        return tckn;
    }

    public String getpassword() {
        return password;
    }

    public String getbalance() {
        return balance;
    }

    public void settckn(String tckn) {
        this.tckn = tckn;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setbalance(String balance) {
        this.balance = balance;
    }
}