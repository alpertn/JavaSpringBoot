package com.spring.proje.security;

import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import com.spring.proje.service.AccountService;
import com.spring.proje.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.spring.proje.adminrepository.checkadmin;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/test")
public class checkip {

    private static final long TIME_LIMIT_MILLIS = 60 * 1000;
    private static final int MAX_REQUESTS = 10;

    private static final Map<String, List<Long>> requestHistory = new ConcurrentHashMap<>();

    private final transactionrepository transactionepositoryi;
    private final userrepository userrepository;
    private final AccountService accountservice;
    private final TransactionService transactionservice;
    private final checkadmin checkadmin;


    public checkip (transactionrepository transactionepositoryi, userrepository userrepository, checkadmin checkadmin, AccountService accountservice, TransactionService transactionservice) {
        this.transactionepositoryi = transactionepositoryi;
        this.userrepository = userrepository;
        this.checkadmin = checkadmin;
        this.accountservice = accountservice;
        this.transactionservice = transactionservice;
    }

    public String getip (HttpServletRequest request){

        String xfHeader = request.getHeader("X-Forwarded-For");

        String ip = request.getRemoteAddr();

        if (xfHeader != null && !xfHeader.isEmpty() && !"unknown".equalsIgnoreCase(xfHeader)) {
            return xfHeader.split(",")[0].trim();
        }
        return xfHeader + "normal ip" + ip;

    }



    @PostMapping("/ip")
    public ResponseEntity<?> depositmoney(@Valid
                                          @RequestBody
                                          ipdto request,
                                          BindingResult bindingresult,
                                          HttpServletRequest ip

    ){

        if (sqlinjectiontester.check(request.getip()) == true)
        {

            return ResponseEntity.badRequest().body(clown.json());


        }else{
            if(bindingresult.hasErrors()){

                return ResponseEntity.badRequest().build();

            }else{

                    return ResponseEntity.ok(getip(ip));
                }

            }

        }
}
