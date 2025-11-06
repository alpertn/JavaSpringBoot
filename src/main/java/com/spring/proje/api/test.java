package com.spring.proje.api;
import jakarta.validation.Valid;
import com.spring.proje.dto.testdto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class test {

    private static final Logger log = LoggerFactory.getLogger(test.class);

    @PostMapping("/phone")
    public ResponseEntity<?> request(
                @Valid
                @RequestBody
                testdto gelenistek ,
                BindingResult bindingResult


    ){
        if (bindingResult.hasErrors()){

            return ResponseEntity.badRequest().build();

        }else{
            return ResponseEntity.ok(gelenistek);
        }


    }



}
