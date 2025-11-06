package com.spring.proje.dto;

public class transactiondto {

    private String tckn;
    private String password;
    private String iban;
    private String balance;

    public String gettckn() {
        return tckn;
    }

    public String getpassword() {
        return password;
    }

    public String getiban() {
        return iban;
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

    public void setiban(String iban) {
        this.iban = iban;
    }

    public void setbalance(String balance) {
        this.balance = balance;
    }
}