package com.spring.proje.service;

import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class Iban {



    //Örnek kullanım String iban = Iban.createiban();
    public static String createiban(){

        Random random = new Random();
        StringBuilder stringbuilder = new StringBuilder("TR");

        for(int i = 0; i < 24; i++){

            stringbuilder.append(random.nextInt(10));

        }

        return stringbuilder.toString();

    }



}
