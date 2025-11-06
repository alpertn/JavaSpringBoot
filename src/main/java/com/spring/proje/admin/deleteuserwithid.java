package com.spring.proje.admin;


import com.spring.proje.admindto.deleteuserwithiddto;
import com.spring.proje.adminrepository.checkadmin;
import com.spring.proje.adminrepository.endpointlogger;
import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.service.AccountService;
import com.spring.proje.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import com.spring.proje.security.clown;
import org.springframework.validation.BindingResult;
import com.spring.proje.httpservletrequest.getip;
import com.spring.proje.security.sqlinjectiontester;
import java.util.Map;

@RestController
@RequestMapping("api/admin")
public class deleteuserwithid {

    private final transactionrepository transactionepositoryi; // kullanabilmek icin bunu yaziyoruz
    private final userrepository userrepository;
    private final endpointlogger endpointlogger;
    private final AccountService accountservice;
    private final getip getip;
    private final TransactionService transactionservice;
    private final checkadmin checkadmin;



    public deleteuserwithid (transactionrepository transactionepositoryi, getip getip, endpointlogger endpointlogger, userrepository userrepository, checkadmin checkadmin, AccountService accountservice, TransactionService transactionservice) { // constructor'a dto sınıfını koyunca hata verıyor dırekt cagırmamız lazım.
        this.transactionepositoryi = transactionepositoryi;
        this.endpointlogger = endpointlogger;
        this.userrepository = userrepository;
        this.getip = getip;
        this.checkadmin = checkadmin;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }

    @PostMapping("/deleteuserwithid")
    public ResponseEntity<?> request(@Valid
                                     @RequestBody
                                         deleteuserwithiddto request,
                                     BindingResult bindingresult,
                                     HttpServletRequest HttpServletRequest
    ){

        if (sqlinjectiontester.check(request.getadminname() + request.getadminpassword()+ request.getid()) == true)
        {

            return ResponseEntity.badRequest().body(clown.json());


        }else{



            if(bindingresult.hasErrors()){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

            }else{
                if (checkadmin.checkadmin(request.getadminname(),request.getadminpassword()) == true){



                    if(userrepository.DeleteUser(Long.valueOf(request.getid())) == true){

                        endpointlogger.log(HttpServletRequest, request.getadminname(),request.getadminpassword(), "/api/admin/deleteuserwithid");
                        return ResponseEntity.ok(Map.of("status","1"));

                    }else{
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));
                    }



                }else{

                    return ResponseEntity.badRequest().body(clown.json());

                }


            }

        }



    }




}
