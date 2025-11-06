package com.spring.proje.httpservletrequest;

import jakarta.servlet.http.HttpServletRequest;

public class checkuseragent {


    public Boolean checkeadmin(HttpServletRequest request){


        String useragent = request.getHeader("User-Agent");

        if ("admin".equals(useragent)){

            return true;

        }
        else{
            return false;
        }

    }


}
