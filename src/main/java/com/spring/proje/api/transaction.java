package com.spring.proje.api;


import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.service.AccountService;
import com.spring.proje.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.spring.proje.dto.transactiondto;
import com.spring.proje.security.clown;
import org.springframework.validation.BindingResult;
import java.util.Map;
import com.spring.proje.security.sqlinjectiontester;


@RestController
@RequestMapping("api/transaction")
public class transaction {

    private final transactionrepository transactionepositoryi;
    private final userrepository userrepository;
    private final AccountService accountservice;
    private final TransactionService transactionservice;


    public transaction (transactionrepository transactionepositoryi, userrepository userrepository, AccountService accountservice, TransactionService transactionservice) { // constructor'a dto sınıfını koyunca hata verıyor dırekt cagırmamız lazım.
        this.transactionepositoryi = transactionepositoryi;
        this.userrepository = userrepository;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }
    @PostMapping("/sendmoney")
    public ResponseEntity<?> createuser(@Valid

                                        @RequestBody
                                        transactiondto request,
                                        BindingResult bindingresult

    ){

        if (sqlinjectiontester.check(request.getbalance() + request.getiban() + request.gettckn() +  request.getpassword() +request.gettckn()) == true)
        {

            return ResponseEntity.badRequest().body(clown.json());


        }else{
            if(transactionepositoryi.tckndogrula(request.gettckn(),request.getpassword()) == true){


                if (transactionepositoryi.findibanwithtckn(request.gettckn()) != null){


                    if(bindingresult.hasErrors()){

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

                    }else{

                        String iban = transactionepositoryi.findibanwithtckn(request.gettckn());
                                try{
                                   Float balance = Float.parseFloat(request.getbalance() );



                                   if(transactionservice.transfer(iban,request.getiban(),balance) == true){


                                       return ResponseEntity.ok(Map.of("Status","1"));

                                   }else{

                                       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));
                                   }


                                }catch (NumberFormatException e){

                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));
                                }


                    }

                }


            }else{

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

            }


        }


         return ResponseEntity.badRequest().body(Map.of("status","0"));
    }


}
