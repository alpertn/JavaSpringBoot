package com.spring.proje.service;

import com.spring.proje.models.User;
import com.spring.proje.repository.transactionrepository;
import com.spring.proje.repository.userrepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AccountService {

    private final transactionrepository transactionrepository;
    private final userrepository userrepository;
    private final Iban iban;

    public AccountService(transactionrepository transactionrepository,userrepository userrepository,Iban iban) {
        this.transactionrepository=transactionrepository;
        this.userrepository=userrepository;
        this.iban= iban;
    }

    @Transactional
    public User createUser(String ad, String surname, String tckimlikno, String sifre ,LocalDate birthdate){

        String iban = Iban.createiban();

        User user = new User();
        user.setbalance('0');
        user.setpassword(sifre);
        user.setid(userrepository.findid() + 1);
        user.setname(ad);
        user.settckn(tckimlikno);
        user.setsurname(surname);
        user.setbirthdate(birthdate);
        user.setiban(iban);

        return userrepository.save(user);

    }

    public User getuserbyiban(String iban){
        return userrepository.findaccountbyiban(iban)
                .orElseThrow(() -> new RuntimeException("Bu ibana kayitli bir hesap bulunamadi.")); // if ile yaptıgımda hata verdı oyuzden bunu kullandım. hem daha az kod yazıyoruz hem daha estetık gozukuyor.


    }




}
