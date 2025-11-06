package com.spring.proje.admin;


import com.spring.proje.admindto.finduserwithibandto;
import com.spring.proje.adminrepository.checkadmin;
import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.service.AccountService;
import com.spring.proje.adminrepository.endpointlogger;
import com.spring.proje.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.spring.proje.security.clown;
import org.springframework.validation.BindingResult;
import com.spring.proje.security.sqlinjectiontester;
import java.util.Map;
import com.spring.proje.httpservletrequest.getip;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/admin")
public class finduserwithiban {

    private final transactionrepository transactionepositoryi; // kullanabilmek icin bunu yaziyoruz
    private final userrepository userrepository;
    private final endpointlogger endpointlogger;
    private final AccountService accountservice;
    private final getip getip;
    private final TransactionService transactionservice;
    private final checkadmin checkadmin;



    public finduserwithiban (getip getip, endpointlogger endpointlogger,transactionrepository transactionepositoryi, userrepository userrepository, checkadmin checkadmin, AccountService accountservice, TransactionService transactionservice) { // constructor'a dto sınıfını koyunca hata verıyor dırekt cagırmamız lazım.
        this.endpointlogger = endpointlogger;
        this.transactionepositoryi = transactionepositoryi;
        this.userrepository = userrepository;
        this.getip = getip;
        this.checkadmin = checkadmin;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }

    @PostMapping("/finduserwithiban")
    public ResponseEntity<?> request(@Valid
                                     @RequestBody
                                     finduserwithibandto request,
                                     BindingResult bindingresult,
                                     HttpServletRequest HttpServletRequest


    ){

        if (sqlinjectiontester.check(request.getadminname() + request.getadminpassword()+ request.getiban()) == true)
        {

            return ResponseEntity.badRequest().body(clown.json());


        }else{



            if(bindingresult.hasErrors()){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

            }else{
                if (checkadmin.checkadmin(request.getadminname(),request.getadminpassword()) == true){

                    var userOpt = userrepository.findbyiban(request.getiban());
                    endpointlogger.log(HttpServletRequest, request.getadminname(),request.getadminpassword(), "/api/admin/finduserwithiban");


                    return ResponseEntity.ok(userOpt.get());



                }else{

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "0", "details", bindingresult.getAllErrors()));

                }


            }

        }



    }




}
