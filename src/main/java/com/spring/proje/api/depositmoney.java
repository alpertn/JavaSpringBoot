package com.spring.proje.api;


import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.service.AccountService;
import com.spring.proje.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.spring.proje.dto.depositmoneydto;
import com.spring.proje.security.clown;
import org.springframework.validation.BindingResult;

import com.spring.proje.security.sqlinjectiontester;

import java.util.Map;


@RestController
@RequestMapping("api/user")
public class depositmoney {

    private final transactionrepository transactionepositoryi;
    private final userrepository userrepository;
    private final AccountService accountservice;
    private final TransactionService transactionservice;


    public depositmoney (transactionrepository transactionepositoryi, userrepository userrepository, AccountService accountservice, TransactionService transactionservice) {
        this.transactionepositoryi = transactionepositoryi;
        this.userrepository = userrepository;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }
    @PostMapping("/depositmoney")
    public ResponseEntity<?> depositmoney(@Valid
                                        @RequestBody
                                        depositmoneydto request,
                                        BindingResult bindingresult

    ){

        if (sqlinjectiontester.check(request.gettckn() + request.getpassword() + request.getbalance()) == true)
        {

            return ResponseEntity.badRequest().body(clown.json());


        }else{
            if(bindingresult.hasErrors()){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

            }else{

                try{

                    float balance = Float.valueOf(request.getbalance());

                    if (transactionepositoryi.parayatirmatckn(balance,request.gettckn()) == true){

                        return ResponseEntity.ok(Map.of("Status","1"));

                    }else{

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));
                    }


                }catch(NumberFormatException e){

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));
                }

            }

        }






    }







}
