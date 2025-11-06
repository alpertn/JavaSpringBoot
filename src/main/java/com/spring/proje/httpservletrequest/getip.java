package com.spring.proje.httpservletrequest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class getip {

    public String ip(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For"); // genellikle bu kullanilir cloudflareden yada https kullaniyorsa boyle dondurursek gercek ipsini aliriz

        if (isunknown(ip)) { // == 1 veya true yazmama gerek yok
            ip = request.getHeader("Proxy-Client-IP");

        }
        if (isunknown(ip)){

            ip = request.getHeader("WL-Proxy-Client-IP");

        }
        if (isunknown(ip)){

            ip = request.getHeader("HTTP_CLIENT_IP");

        }
        if (isunknown(ip)){

            ip = request.getHeader("HTTP_X_FORWARDED_FOR");

        }
        if (isunknown(ip)){

            ip = request.getRemoteAddr();  // proxy yoksa direkt burdan da yapabviiriz

        }

        return ip;
    }

    public Boolean isunknown(String ip){

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
            return true;

        }else{
            return false;
        }

    }
}