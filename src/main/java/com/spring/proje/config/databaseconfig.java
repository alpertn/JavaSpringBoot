//package com.bank.atm.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//import java.sql.*;
//
//@Configuration
//public class databaseconfig {
//
//    private String reset = "\u001B[0m";
//    private String green = "\u001B[32m";
//    private  String red = "\u001B[31m";
//
//    @Bean
//    public DataSource DataSource() throws SQLException { // QLException hata vermemesı ıcın
//
//        DriverManagerDataSource datasource = new DriverManagerDataSource();
//        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        datasource.setUrl("jdbc:mysql://localhost:3331/atm?useSSL=false");
//        datasource.setUsername("root");
//        datasource.setPassword("root");
//
//
//        try (Connection connection = datasource.getConnection()) {
//            System.out.println(green + "---- Mysql Bağlantısı Başarılı! ----> "+ connection.getMetaData().getURL() + reset);
//        } catch (SQLException e) {
//            System.out.println(red + "MySql Bağlantısı Başarısız Oldu. Detaylar: " +  reset + e.getMessage());
//        }
//
//
//
//        return datasource;
//    }
//
//}
