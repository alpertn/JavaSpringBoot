package com.spring.proje.admin;

import com.spring.proje.admindto.findidwithibandto;
import com.spring.proje.adminrepository.checkadmin;
import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.adminrepository.endpointlogger;
import com.spring.proje.service.AccountService;
import com.spring.proje.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.spring.proje.security.sqlinjectiontester;
import com.spring.proje.security.clown;
import com.spring.proje.httpservletrequest.getip;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/admin")
public class findidwithiban {

    private final transactionrepository transactionepositoryi;
    private final userrepository userrepository;
    private final endpointlogger endpointlogger;
    private final AccountService accountservice;
    private final getip getip;
    private final TransactionService transactionservice;
    private final checkadmin checkadmin;


    public findidwithiban (getip getip, endpointlogger endpointlogger,transactionrepository transactionepositoryi, userrepository userrepository, checkadmin checkadmin, AccountService accountservice, TransactionService transactionservice) {
        this.endpointlogger = endpointlogger;
        this.transactionepositoryi = transactionepositoryi;
        this.userrepository = userrepository;
        this.checkadmin = checkadmin;
        this.getip = getip;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }


    @PostMapping("/findidwithiban")
    public ResponseEntity<?>  requesthandler(
            @Valid
            @RequestBody
            findidwithibandto request,
            BindingResult bindingresult,
            HttpServletRequest HttpServletRequest

    ){


        if(sqlinjectiontester.check(request.getiban() + request.getadminpassword()  + request.getadminname()) == true){


            return ResponseEntity.badRequest().body(clown.json());

        }else{


            if(checkadmin.checkadmin(request.getadminname() , request.getadminpassword()) == true){

                String id  = userrepository.findbyiban2(request.getiban());
                endpointlogger.log(HttpServletRequest, request.getadminname(),request.getadminpassword(), "/api/admin/findidwithiban");


                return ResponseEntity.ok(Map.of("id" , id));


            }else{

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

            }


        }


    }


}
