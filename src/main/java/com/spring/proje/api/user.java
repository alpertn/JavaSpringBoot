package com.spring.proje.api;


import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.service.AccountService;
import com.spring.proje.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.spring.proje.dto.userdto;
import com.spring.proje.security.clown;
import org.springframework.validation.BindingResult;

import com.spring.proje.security.sqlinjectiontester;

import java.util.Map;


@RestController
@RequestMapping("api/user")
public class user {

    private final transactionrepository transactionepositoryi; // kullanabilmek icin bunu yaziyoruz
    private final userrepository userrepository;
    private final AccountService accountservice;
    private final TransactionService transactionservice;


    public user (transactionrepository transactionepositoryi, userrepository userrepository, AccountService accountservice, TransactionService transactionservice) { // constructor'a dto sınıfını koyunca hata verıyor dırekt cagırmamız lazım.
        this.transactionepositoryi = transactionepositoryi;
        this.userrepository = userrepository;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }
    @PostMapping("/createuser")
    public ResponseEntity<?> createuser(@Valid
                                        @RequestBody
                                        userdto request,
                                        BindingResult bindingresult
                                        
    ){

        if (sqlinjectiontester.check(request.getname() + request.getsurname() + request.getbirthdate() +  request.getpassword() +request.gettckn()) == true)
        {

            return ResponseEntity.badRequest().body(clown.json());


        }else{
;
            if(bindingresult.hasErrors()){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid input data", "details", bindingresult.getAllErrors()));

            }else{

                accountservice.createUser(request.getname(),request.getsurname(),request.gettckn(),request.getpassword(),request.getbirthdate());
                return ResponseEntity.status(HttpStatus.OK).body(Map.of("status","1"));// bırsey gerı dondurmek ıstersem build yazmıyorum ıstemıyorsam build yazıyorum status code donuyor sadece

            }

        }






    }

    // KOD calisiyor
    // Hata ayıklama eklenecek
    // örnek request
    // {
    //	"name": "alper",
    //    "surname": "karakus",
    //    "tckn": "51182934422",
    //    "password" : "alperkarakus07",
    //    "birthdate": "2007-05-25"
    //}






}
