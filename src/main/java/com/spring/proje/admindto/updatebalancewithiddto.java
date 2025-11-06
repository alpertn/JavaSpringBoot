package com.spring.proje.admindto;

public class updatebalancewithiddto {

    private String id;
    private Float balance;

    private String adminname;
    private String adminpassword;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public Float getbalance() {
        return balance;
    }

    public void setbalance(String balance) {
        this.balance = Float.parseFloat(balance);
    }

    public String getadminpassword() {
        return adminpassword;
    }

    public void setadminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getadminname() {
        return adminname;
    }

    public void setadminname(String adminname) {
        this.adminname = adminname;
    }
}
