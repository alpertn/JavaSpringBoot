package com.spring.proje;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class springexample {

    public static void main(String[] args) {
        SpringApplication.run(springexample.class, args);
    }



}

// tum mysql kodu
// ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';  FLUSH PRIVILEGES;CREATE DATABASE ATM; USE ATM; CREATE TABLE admins (id BIGINT PRIMARY KEY AUTO_INCREMENT, adminname VARCHAR(100) NOT NULL, adminpassword VARCHAR(100) NOT NULL, ip_address VARCHAR(45) NOT NULL DEFAULT '0.0.0.0', lastendpoint VARCHAR(255) NOT NULL DEFAULT '', endpointusage INT DEFAULT 1, UNIQUE KEY unique_adminname (adminname)); INSERT INTO admins (adminname, adminpassword) VALUES ('root','root'); CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, ad VARCHAR(100) NOT NULL, soyad VARCHAR(100) NOT NULL, tckimlikno VARCHAR(11) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL, dogumtarihi DATE NOT NULL, iban VARCHAR(34) UNIQUE NOT NULL, balance DECIMAL(40,2) DEFAULT 0.00);
