package com.spring.proje.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class testdto {

    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

}
