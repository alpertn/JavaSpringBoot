package com.spring.proje.admindto;

public class finduserwithibandto {

    private String iban;

    private String adminname;
    private String adminpassword;

    public String getiban() {
        return iban;
    }

    public void setiban(String iban) {
        this.iban = iban;
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
