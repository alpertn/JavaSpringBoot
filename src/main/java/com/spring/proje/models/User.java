package com.spring.proje.models;

import java.time.LocalDate;


public class User {

    private Long id;
    private String ad;
    private String soyad;
    private String TCKN;
    private String iban;
    private String sifre;
    private LocalDate dogumtarihi;
    private float bakiye;

    public User() {}

    public User(Long id, String ad, String surname, String tckimlikno, LocalDate birthdate, String iban, String sifre, float newbalance) {
        this.id = id;
        this.ad = ad;
        this.soyad = surname;
        this.TCKN = tckimlikno;
        this.dogumtarihi = birthdate;
        this.iban = iban;
        this.sifre = sifre;
        this.bakiye = newbalance;
    }

    public Long cekid(){

        return id;
    }
    public void setid(Long yeniid){
        this.id = yeniid;
    }
    public String getname(){
        return ad;
    }
    public void setname(String name){
        this.ad = name;
    }
    public String getsurname(){
        return soyad;
    }
    public void setsurname(String surname){
        this.soyad = surname;

    }
    public String gettckn(){

        return TCKN;
    }
    public void settckn(String tckimlikno){

        this.TCKN = tckimlikno;
    }
    public LocalDate getbirthdate(){
        return dogumtarihi;
    }
    public void setbirthdate(LocalDate birthdate){
        this.dogumtarihi = birthdate;
    }
    public String getiban(){
        return iban;
    }
    public void setiban(String iban){
        this.iban = iban;
    }
    public String getpassword(){
        return sifre;
    }
    public void setpassword(String yenisifre){
        sifre = yenisifre;
    }
    public float getbalance(){

        return bakiye;
    }
    public void setbalance(float newbalance){
        bakiye = newbalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", iban='" + iban + '\'' +
                ", tckimlikno='" + TCKN + '\'' +
                ", dogumtarihi='" + dogumtarihi + '\'' +
                ", balance=" + bakiye +
                '}';
    }
}
