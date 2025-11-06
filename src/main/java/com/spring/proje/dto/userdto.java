package com.spring.proje.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
public class userdto {

    private String name;
    private String surname;
    private String tckn;
    private String password;


    @JsonFormat(pattern = "yyyy-MM-dd") // jsondaki bir zaman bilgisini javaya gecirmeyi bilmiyordum geminiye sordum bunu
    private LocalDate birthdate;

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name = name;
    }

    public String getsurname(){
        return surname;
    }

    public void setsurname(String surname){
        this.surname = surname;
    }

    public String getpassword(){
        return password;
    }

    public void setpassword(String password){
        this.password = password;
    }

    public String gettckn(){
        return tckn;
    }

    public void settckn(String tckn){
        this.tckn = tckn;
    }

    public LocalDate getbirthdate(){
        return birthdate;
    }

    public void setbirthdate(LocalDate birthdate){
        this.birthdate = birthdate;
    }
}
