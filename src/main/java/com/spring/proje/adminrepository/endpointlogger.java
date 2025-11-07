package com.spring.proje.adminrepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.spring.proje.httpservletrequest.getip;
import jakarta.servlet.http.HttpServletRequest;

@Repository
public class endpointlogger {

    private  final  JdbcTemplate jdbc;

    private final getip getip;

    public endpointlogger(JdbcTemplate jdbctemplate, getip getip){

        this.jdbc = jdbctemplate;
        this.getip = getip;

    }


    public Boolean log(HttpServletRequest httpServletRequest,String admin,String adminpassword,String endpoint){

        String ip = getip.ip(httpServletRequest);

        String sql= """
    INSERT INTO admins
    (adminname, adminpassword, ip_address, lastendpoint, endpointusage)
    VALUES (?, ?, ?, ?, 1)
    ON DUPLICATE KEY UPDATE
    ip_address = VALUES(ip_address),
    endpointusage = endpointusage + 1,
    lastendpoint = IF(lastendpoint <> VALUES(lastendpoint), VALUES(lastendpoint), lastendpoint);
    """;

        // sql kodu ip adresi ve endpoint fakrkliysa guncelliyor farkl idegilse ayni birakiyor hata vermemesi icin onemli

        return jdbc.update(sql,admin,adminpassword,ip,endpoint) > 0; //

    }

}
//CREATE TABLE admins (
//        id BIGINT PRIMARY KEY AUTO_INCREMENT,
//        adminname VARCHAR(100) NOT NULL,
//adminpassword VARCHAR(100) NOT NULL,
//ip_address VARCHAR(45) NOT NULL UNIQUE,
//lastendpoint VARCHAR(255) NOT NULL DEFAULT '',
//usage INT DEFAULT 1
//        );
